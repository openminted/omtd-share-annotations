/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package eu.openminted.share.annotations.maven;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.FileUtils;
import org.sonatype.plexus.build.incremental.BuildContext;
import eu.openminted.share.annotations.util.XmlUtil;
import eu.openminted.share.annotations.util.analyzer.AnalyzerException;
import eu.openminted.share.annotations.util.analyzer.MavenProjectAnalyzer;
import eu.openminted.share.annotations.util.analyzer.OmtdAnalyzer;
import eu.openminted.share.annotations.util.scanner.DescriptorSet;
import eu.openminted.share.annotations.util.scanner.GateComponentScanner;
import eu.openminted.share.annotations.util.scanner.UimaComponentScanner;

/**
 * Generate descriptor files for uimaFIT-based UIMA components.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.PROCESS_CLASSES, requiresDependencyResolution = ResolutionScope.COMPILE, requiresDependencyCollection = ResolutionScope.COMPILE)
public class GenerateDescriptorsMojo
    extends AbstractMojo
{
    @Component
    private MavenProject project;

    @Component
    private BuildContext buildContext;

    private ClassLoader componentLoader;

    /**
     * Path where the generated resources are written.
     */
    @Parameter(defaultValue = "${project.build.directory}/classes", required = true)
    private File outputDirectory;

    /**
     * Skip generation of META-INF/eu.openminted.share/descriptors.txt
     */
    @Parameter(defaultValue = "false", required = true)
    private boolean skipDescriptorsManifest;

    /**
     * Source file encoding.
     */
    @Parameter(defaultValue = "${project.build.sourceEncoding}", required = true)
    private String encoding;

    @Override
    public void execute()
        throws MojoExecutionException
    {
        // add the generated sources to the build
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
            buildContext.refresh(outputDirectory);
        }

        componentLoader = Util.getClassloader(project, getLog());

        // Get the compiled classes from this project
        String[] files = FileUtils.getFilesFromExtension(project.getBuild().getOutputDirectory(),
                new String[] { "class" });

        // List of components that is later written to META-INF/eu.openminted.share/descriptors.txt
        StringBuilder descriptorsManifest = new StringBuilder();

        // Use scanners to find native component descriptors and convert them to OMTD-SHARE
        // Scan for UIMA
        List<DescriptorSet> descriptorSets = new ArrayList<>();
        try {
            UimaComponentScanner uimaComponentScanner = new UimaComponentScanner();
            String[] xmlFiles = FileUtils.getFilesFromExtension(
                    project.getBuild().getOutputDirectory(), new String[] { "xml" });
            xmlFiles = asList(xmlFiles).stream().map(url -> "file:" + url).toArray(String[]::new);
            uimaComponentScanner.scan(xmlFiles);
            descriptorSets.addAll(uimaComponentScanner.getComponents());
        }
        catch (IOException e) {
            throw new MojoExecutionException("Unable to scan UIMA descriptor files", e);
        }

        // Scan for GATE
        try {
            GateComponentScanner gateComponentScanner = new GateComponentScanner();
            File creoleXmlFile = new File(project.getBuild().getOutputDirectory(),
                    "META-INF/gate/creole.xml");
            if (creoleXmlFile.exists()) {
                gateComponentScanner.scan(creoleXmlFile.toURI().toURL().toString());
                descriptorSets.addAll(gateComponentScanner.getComponents());
            }
        }
        catch (IOException e) {
            throw new MojoExecutionException("Unable to scan descriptor files", e);
        }

        int countGenerated = 0;
        for (DescriptorSet ds : descriptorSets) {
            // Scan for OMTD-SHARE annotations in the classes referred to by the descriptors and
            // add the corresponding information
            OmtdAnalyzer omtdAnalyzer = new OmtdAnalyzer();
            try {
                Class clazz = componentLoader.loadClass(ds.getImplementationName());
                omtdAnalyzer.analyze(ds.getOmtdShareDescriptor(), clazz);
            }
            catch (ClassNotFoundException e) {
                throw new MojoExecutionException("Unable to load implementation class", e);
            }

            // Augment descriptors with information from the Maven POM
            MavenProjectAnalyzer pomAnalyzer = new MavenProjectAnalyzer();
            try {
                pomAnalyzer.analyze(ds.getOmtdShareDescriptor(), project);
            }
            catch (AnalyzerException e) {
                throw new MojoExecutionException("Unable to augment descriptor", e);
            }
            
            String descriptorPath = ds.getImplementationName().replace(".", "/");
            
            try {
                toXML(ds.getOmtdShareDescriptor(),
                        project.getBuild().getOutputDirectory() + "/" + descriptorPath + ".omtds");
            }
            catch (IOException | XMLStreamException | JAXBException e) {
                throw new MojoExecutionException("Unable to generate descriptor", e);
            }
            
            // Remember component
            descriptorsManifest.append("classpath*:").append(descriptorPath+".omtds").append('\n');
            countGenerated++;
        }

        getLog().info(
                "Generated " + countGenerated + " descriptor" + (countGenerated != 1 ? "s." : "."));

        // Write META-INF/org.apache.uima.fit/components.txt unless skipped and unless there are no
        // components
        if (!skipDescriptorsManifest && descriptorsManifest.length() > 0) {
            File path = new File(outputDirectory, "META-INF/eu.openminted.share/descriptors.txt");
            FileUtils.mkdir(path.getParent());
            try {
                FileUtils.fileWrite(path.getPath(), encoding, descriptorsManifest.toString());
            }
            catch (IOException e) {
                throw new MojoExecutionException("Cannot write descriptors manifest to [" + path
                        + "]" + ExceptionUtils.getRootCauseMessage(e), e);
            }
        }
    }

    /**
     * Save descriptor XML to file system.
     */
    private void toXML(eu.openminted.registry.domain.Component aComponent, String aFilename)
        throws IOException, XMLStreamException, JAXBException
    {
        File out = new File(aFilename);
        getLog().debug("Writing descriptor to: " + out);
        try (OutputStream os = new FileOutputStream(out)) {
            XmlUtil.write(aComponent, os);
        }
    }
}
