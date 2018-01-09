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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
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

import eu.openminted.registry.domain.ComponentDistributionFormEnum;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ComponentLoc;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.ResourceIdentifier;
import eu.openminted.registry.domain.ResourceIdentifierSchemeNameEnum;
import eu.openminted.share.annotations.util.XmlUtil;
import eu.openminted.share.annotations.util.analyzer.AnalyzerException;
import eu.openminted.share.annotations.util.analyzer.MavenProjectAnalyzer;
import eu.openminted.share.annotations.util.analyzer.OmtdAnalyzer;
import eu.openminted.share.annotations.util.scanner.DescriptorSet;
import eu.openminted.share.annotations.util.scanner.GateComponentScanner;
import eu.openminted.share.annotations.util.scanner.UimaComponentScanner;

/**
 * Generate OpenMinTeD-SHARE descriptor files from any supported component types
 * discovered within the source tree of the project. Currently this includes
 * components built for both the UIMA and GATE frameworks.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.PROCESS_CLASSES, requiresDependencyResolution = ResolutionScope.COMPILE, requiresDependencyCollection = ResolutionScope.COMPILE)
public class GenerateDescriptorsMojo
    extends AbstractMojo
{
    public static enum DescriptorLocation {
        withClasses,
        inMetaInf
    }
    
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
     * File extension for the generated descriptor files.
     */
    @Parameter(defaultValue = ".omtds.xml", required = true)
    private String fileExtension;

   /**
    * Store the descriptors in the OpenMinTeD META-INF folder rather than
    * alongside the classes. This defaults to true as that ensures that the
    * filenames are the component IDs which best matches the OpenMinTeD
    * requirements and eases interoperability.
    */
    @Parameter(defaultValue = "inMetaInf", required = true)
    private DescriptorLocation descriptorLocation;

    /**
     * Source file encoding.
     */
    @Parameter(defaultValue = "${project.build.sourceEncoding}", required = true)
    private String encoding;

    /**
     * Specifies the form of distribution of the component (e.g. as source code, executable
     * program etc.).
     */
    @Parameter(defaultValue = "EXECUTABLE_CODE", required = true)
    private ComponentDistributionFormEnum componentDistributionForm;

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
        
        File descriptorsDir = Paths.get(outputDirectory.getAbsolutePath(), "META-INF", "eu.openminted.share").toFile();
        descriptorsDir.mkdirs();
        
        File descriptorsFile = new File(descriptorsDir, "descriptors.txt");

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
            
            String descriptorPath;
            switch (descriptorLocation) {
            case inMetaInf:
                descriptorPath = ds.getImplementationName() + fileExtension;
                break;
            case withClasses:
                descriptorPath = "../../" + ds.getImplementationName().replace(".", "/")
                        + fileExtension;
                break;
            default:
                throw new IllegalStateException(
                        "Unknown descriptor location [" + descriptorLocation + "]");
            }
            
            eu.openminted.registry.domain.Component omtdShareDescriptor = ds.getOmtdShareDescriptor();
            ComponentInfo componentInfo = omtdShareDescriptor.getComponentInfo();
            if (componentInfo != null) {
                List<ComponentDistributionInfo> distInfos = componentInfo.getDistributionInfos();
                if (distInfos != null) {
                    // If there already is a distribution info, then update it instead of
                    // creating a new one.
                    if (!componentInfo.getDistributionInfos().isEmpty()) {
                        ComponentDistributionInfo distributionInfo = componentInfo
                                .getDistributionInfos().get(0);
                        
                        ComponentLoc componentLoc = new ComponentLoc();
                        
                        // Set the componentDistributionForm
                        componentLoc.setComponentDistributionForm(componentDistributionForm);
                        
                        // If there is a MAVEN resource identifier, then we use its URI as the
                        // distribution URL
                        IdentificationInfo identificationInfo = componentInfo.getIdentificationInfo();
                        if (identificationInfo != null) {
                            for (ResourceIdentifier resourceIdentifier : identificationInfo
                                    .getResourceIdentifiers()) {
                                if (ResourceIdentifierSchemeNameEnum.MAVEN.equals(
                                        resourceIdentifier.getResourceIdentifierSchemeName())) {
                                    componentLoc.setDistributionLocation(resourceIdentifier.getValue());
                                    break;
                                }
                            }
                        }
                        
                        distributionInfo.setComponentLoc(componentLoc);
                    }
                }
            }
            
            try {
                this.toXML(ds.getOmtdShareDescriptor(), new File(descriptorsDir, descriptorPath));
                descriptorsManifest.append(new URI(null, null, descriptorPath, null).getRawPath()).append("\n");
                ++countGenerated;
            }
            catch (IOException | XMLStreamException | JAXBException | URISyntaxException e) {
                throw new MojoExecutionException("Unable to generate descriptor", e);
            }
        }

        getLog().info(
                "Generated " + countGenerated + " descriptor" + (countGenerated != 1 ? "s." : "."));

        // Write META-INF/eu.openminted.share/descriptors.txt unless there are no components to describe
        if (descriptorsManifest.length() > 0) {
            try {
                FileUtils.fileWrite(descriptorsFile.getPath(), encoding, descriptorsManifest.toString());
            }
            catch (IOException e) {
                throw new MojoExecutionException("Cannot write descriptors manifest to [" + descriptorsFile
                        + "]" + ExceptionUtils.getRootCauseMessage(e), e);
            }
        }
    }

    /**
     * Save descriptor XML to file system.
     */
    private void toXML(eu.openminted.registry.domain.Component aComponent, File out)
        throws IOException, XMLStreamException, JAXBException
    {
        getLog().debug("Writing descriptor to: " + out);
        try (OutputStream os = new FileOutputStream(out)) {
            XmlUtil.write(aComponent, os);
        }
    }
}
