/*
 * Licensed to the OpenMinTeD Consortium under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The OpenMinTeD Consortium 
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.openminted.share.annotations.maven;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.collections4.EnumerationUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.FileUtils;
import org.sonatype.plexus.build.incremental.BuildContext;
import org.xml.sax.SAXException;

import eu.openminted.registry.domain.ComponentDistributionFormEnum;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.ResourceIdentifier;
import eu.openminted.registry.domain.ResourceIdentifierSchemeNameEnum;
import eu.openminted.share.annotations.util.XmlUtil;
import eu.openminted.share.annotations.util.analyzer.AnalyzerException;
import eu.openminted.share.annotations.util.analyzer.MavenProjectAnalyzer;
import eu.openminted.share.annotations.util.analyzer.OmtdAnalyzer;
import eu.openminted.share.annotations.util.analyzer.UimaDescriptorAnalyzer;
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
    @Parameter(defaultValue = "${project.build.directory}/generated-sources/omtd-share", required = true)
    private File outputDirectory;
    
    /**
     * File extension for the generated descriptor files.
     */
    @Parameter(defaultValue = ".omtds.xml", required = true)
    private String fileExtension;

    /**
     * Whether to validate the generated XML descriptor against the XSD schema.
     */
     @Parameter(defaultValue = "true", required = true)
     private boolean validateXml;

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

    /**
     * Location of a properties file which contains mappings from UIMA types to OMTD-SHARE
     * annotation types.
     */
    @Parameter(required = false)
    private List<String> uimaTypeMappings;

    /**
     * Location of a properties file which contains mappings from mime-types to OMTD-SHARE
     * data formats.
     */
    @Parameter(required = false)
    private List<String> mimeTypeMappings;
    private Map<String, String> loadedMimeTypeMappings = Collections.emptyMap();

    /**
     * Include filters on the UIMA descriptors. Default is **\/\*.xml. Note that non-UIMA XML 
     * files are automatically detected and skipped.
     */
    @Parameter(required = false, defaultValue="**/*.xml")
    private List<String> uimaDescriptorIncludes;

    /**
     * Exclude filters on the UIMA descriptors.
     */
    @Parameter(required = false)
    private List<String> uimaDescriptorExcludes;
    
    @Override
    public void execute()
        throws MojoExecutionException
    {
        // add the generated sources to the build
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
            buildContext.refresh(outputDirectory);
        }
        project.addCompileSourceRoot(this.outputDirectory.getPath());
        
        componentLoader = Util.getClassloader(project, getLog());

        try {
            if (mimeTypeMappings != null) {
                loadedMimeTypeMappings = loadMappings(componentLoader, mimeTypeMappings,
                        "Loading mime type mapping: ");
            }
        }
        catch (IOException e) {
            throw new MojoExecutionException("Unable to load mime type mappings", e);
        }
        
        // List of components that is later written to META-INF/eu.openminted.share/descriptors.txt
        StringBuilder descriptorsManifest = new StringBuilder();

        // Use scanners to find native component descriptors and convert them to OMTD-SHARE
        // Scan for UIMA
        List<DescriptorSet> descriptorSets = new ArrayList<>();
        try {
            UimaDescriptorAnalyzer analyzer = new UimaDescriptorAnalyzer();
            if (uimaTypeMappings != null) {
                Map<String, String> mappings = loadMappings(componentLoader, uimaTypeMappings,
                        "Loading UIMA type mapping: ");
                analyzer.setUimaTypeToAnnotationTypeMappings(mappings);
                analyzer.setMimeTypeToDataFormatMappings(loadedMimeTypeMappings);
            }
            
            UimaComponentScanner uimaComponentScanner = new UimaComponentScanner();
            uimaComponentScanner.setAnalyzer(analyzer);
            
            DirectoryScanner ds = new DirectoryScanner();
            ds.setBasedir(project.getBuild().getOutputDirectory());
            if (uimaDescriptorIncludes != null) {
                ds.setIncludes(
                        uimaDescriptorIncludes.toArray(new String[uimaDescriptorIncludes.size()]));
            }
            if (uimaDescriptorExcludes != null) {
                ds.setExcludes(
                        uimaDescriptorExcludes.toArray(new String[uimaDescriptorExcludes.size()]));
            }
            ds.scan();
            String[] xmlFiles = ds.getIncludedFiles();
            
            xmlFiles = asList(xmlFiles).stream().map(path -> {
                try {
                    return new File(project.getBuild().getOutputDirectory(), path).toURI().toURL()
                            .toString();
                }
                catch (MalformedURLException e) {
                    throw new IllegalStateException(e);
                }
            }).toArray(String[]::new);
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
        // In this map we remember how many descriptors have been generated for a given class
        // so we can give them unique names.
        Map<String, Integer> disambiguationMap = new HashMap<String, Integer>();
        
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
                descriptorPath = ds.getImplementationName();
                break;
            case withClasses:
                descriptorPath = "../../" + ds.getImplementationName().replace(".", "/");
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
                        
                        
                        // Set the componentDistributionForm
                        distributionInfo.setComponentDistributionForm(componentDistributionForm);
                        
                        // If there is a MAVEN resource identifier, then we use its URI as the
                        // distribution URL
                        IdentificationInfo identificationInfo = componentInfo.getIdentificationInfo();
                        if (identificationInfo != null) {
                            for (ResourceIdentifier resourceIdentifier : identificationInfo
                                    .getResourceIdentifiers()) {
                                if (ResourceIdentifierSchemeNameEnum.MAVEN.equals(
                                        resourceIdentifier.getResourceIdentifierSchemeName())) {
                                    distributionInfo.setDistributionLocation(resourceIdentifier.getValue());
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            String disambiguatedDescriptorPath = descriptorPath;
            int count = disambiguationMap.getOrDefault(descriptorPath, 0);
            disambiguationMap.put(descriptorPath, count + 1);
            if (count > 0) {
                disambiguatedDescriptorPath += "-" + count;
            }
            
            File outFile = new File(descriptorsDir, disambiguatedDescriptorPath+fileExtension);
            try {
                this.toXML(ds.getOmtdShareDescriptor(), outFile);
                descriptorsManifest.append(
                        new URI(null, null, disambiguatedDescriptorPath + fileExtension, null)
                                .getRawPath())
                        .append("\n");
                countGenerated++;
            }
            catch (IOException | XMLStreamException | JAXBException | URISyntaxException e) {
                throw new MojoExecutionException("Unable to generate descriptor", e);
            }
            
            if (validateXml) {
                try {
                    getLog().debug("Validating descriptor: " + outFile);
                    validate(getClass().getResource("/parsed/OMTD-SHARE-Component.xsd"), outFile);
                }
                catch (IOException | SAXException e) {
                    throw new MojoExecutionException("Unable to validate descriptor", e);
                }
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
        out.getParentFile().mkdirs();
        try (OutputStream os = new FileOutputStream(out)) {
            XmlUtil.write(aComponent, os);
        }
    }
    
    private Map<String, String> loadMappings(ClassLoader aLoader, List<String> aMappings,
            String aMsg)
        throws IOException
    {
        Map<String, String> mappings = new HashMap<>();
        for (String mapping : aMappings) {
            getLog().info(aMsg + mapping);
            Enumeration<URL> mapUrls = aLoader.getResources(mapping);
            for (URL mapUrl : EnumerationUtils.toList(mapUrls)) {
                try (InputStream is = mapUrl.openStream()) {
                    Properties map = new Properties();
                    map.load(is);
                    for (String key : map.stringPropertyNames()) {
                        mappings.put(key, map.getProperty(key));
                    }
                }
            }
        }
        return mappings;
    }
    
    public static  void validate(URL aSchemaUrl, File aFile) throws SAXException, IOException
    {
        Source xmlFile = new StreamSource(aFile);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(aSchemaUrl);
        Validator validator = schema.newValidator();
        validator.validate(xmlFile);
    }
}
