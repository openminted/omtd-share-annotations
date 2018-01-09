package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createDescription;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createGroupName;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createResourceName;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.resource.ResourceCreationSpecifier;
import org.apache.uima.resource.metadata.Capability;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.ProcessingResourceMetaData;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentCreationInfo;
import eu.openminted.registry.domain.ComponentDistributionFormEnum;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ComponentLoc;
import eu.openminted.registry.domain.ComponentTypeEnum;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.registry.domain.CopyrightStatement;
import eu.openminted.registry.domain.DataFormatInfo;
import eu.openminted.registry.domain.FrameworkEnum;
import eu.openminted.registry.domain.GroupInfo;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.Language;
import eu.openminted.registry.domain.MimeTypeEnum;
import eu.openminted.registry.domain.OperatingSystemEnum;
import eu.openminted.registry.domain.ParameterInfo;
import eu.openminted.registry.domain.ParameterTypeEnum;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.registry.domain.ResourceTypeEnum;
import eu.openminted.registry.domain.VersionInfo;

public class UimaDescriptorAnalyzer
    implements Analyzer<ResourceCreationSpecifier>
{
    @Override
    public void analyze(Component aDescriptor, ResourceCreationSpecifier aSpecifier)
    {
        ComponentInfo componentInfo = aDescriptor.getComponentInfo();
        if (componentInfo == null) {
            componentInfo = new ComponentInfo();
            aDescriptor.setComponentInfo(componentInfo);
        }
        
        componentInfo.setResourceType(ResourceTypeEnum.COMPONENT);
        
        ComponentCreationInfo componentCreationInfo = componentInfo.getComponentCreationInfo();
        if (componentCreationInfo == null) {
            componentCreationInfo = new ComponentCreationInfo();
            componentInfo.setComponentCreationInfo(componentCreationInfo);
        }
        componentCreationInfo.setFramework(FrameworkEnum.UIMA);
        componentCreationInfo.setImplementationLanguage("Java");
        
        ComponentLoc componentLoc = new ComponentLoc();
        componentLoc.setCommand(aSpecifier.getImplementationName());
        componentLoc.setComponentDistributionForm(ComponentDistributionFormEnum.EXECUTABLE_CODE);
        
        ComponentDistributionInfo distributionInfo = new ComponentDistributionInfo();
        distributionInfo.setComponentLoc(componentLoc);
        distributionInfo.setOperatingSystems(asList(OperatingSystemEnum.OS_INDEPENDENT));
        componentInfo.getDistributionInfos().add(distributionInfo);
        
        if (aSpecifier instanceof AnalysisEngineDescription) {
            analyzeEngine(componentInfo, (AnalysisEngineDescription) aSpecifier);
        }
        
        if (aSpecifier instanceof CollectionReaderDescription) {
            componentInfo.setComponentType(ComponentTypeEnum.READER);
            analyzeReader(componentInfo, (CollectionReaderDescription) aSpecifier);
        }
    }

    private void analyzeEngine(ComponentInfo aDescriptor, AnalysisEngineDescription aSpecifier)
    {
        analyzeMetadata(aDescriptor, aSpecifier.getAnalysisEngineMetaData(), false);
    }

    private void analyzeReader(ComponentInfo aDescriptor, CollectionReaderDescription aSpecifier)
    {
        analyzeMetadata(aDescriptor, aSpecifier.getCollectionReaderMetaData(), false);
    }
    
    private void analyzeMetadata(ComponentInfo aDescriptor, ProcessingResourceMetaData aSpecifier,
            boolean aCapabilitiesOnInput)
    {
        String copyright = aSpecifier.getCopyright();
        if (isNotBlank(copyright)) {
            // If there already is a distribution info, then update it instead of creating a new
            // one.
            ComponentDistributionInfo distributionInfo;
            if (!aDescriptor.getDistributionInfos().isEmpty()) {
                distributionInfo = aDescriptor.getDistributionInfos().get(0);
            }
            else {
                distributionInfo = new ComponentDistributionInfo();
                aDescriptor.getDistributionInfos().add(distributionInfo);
            }
            
            CopyrightStatement copyrightStatement = new CopyrightStatement();
            copyrightStatement.setValue(copyright);
            distributionInfo.getCopyrightStatements().add(copyrightStatement);
        }
        
        String description = aSpecifier.getDescription();
        String name = aSpecifier.getName();
        if (isNotBlank(description) || isNotBlank(name)) {

            IdentificationInfo identificationInfo = aDescriptor.getIdentificationInfo();
            if (identificationInfo == null) {
                identificationInfo = new IdentificationInfo();
                aDescriptor.setIdentificationInfo(identificationInfo);
            }

            if (isNotBlank(description)) {
                identificationInfo.getDescriptions().add(createDescription(description));
            }

            if (isNotBlank(name)) {
                identificationInfo.getResourceNames().add(createResourceName(name));
            }
        }
        
        // Not sure if/where to add this. Might not be sensible to add anywhere.
        // String uuid = aSpecifier.getUUID();
        
        String vendor = aSpecifier.getVendor();
        if (isNotBlank(vendor)) {
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.getGroupNames().add(createGroupName(vendor));

            ContactInfo contactInfo = aDescriptor.getContactInfo();
            if (contactInfo == null) {
                contactInfo = new ContactInfo();
                aDescriptor.setContactInfo(contactInfo);
            }

            contactInfo.getContactGroups().add(groupInfo);
        }
        
        String version = aSpecifier.getVersion();
        if (isNotBlank(version)) {
            VersionInfo versionInfo = aDescriptor.getVersionInfo();
            if (versionInfo == null) {
                versionInfo = new VersionInfo();
                aDescriptor.setVersionInfo(versionInfo);
            }
            versionInfo.setVersion(version);
        }
        
        ConfigurationParameter[] parameters = aSpecifier.getConfigurationParameterDeclarations()
                .getConfigurationParameters();
        if (parameters.length > 0) {
            ProcessingResourceInfo processingResourceInfo = aDescriptor
                    .getInputContentResourceInfo();
            if (processingResourceInfo == null) {
                processingResourceInfo = new ProcessingResourceInfo();
                aDescriptor.setInputContentResourceInfo(processingResourceInfo);
            }
            
            for (ConfigurationParameter param : parameters) {
    
                ParameterInfo parameterInfo = new ParameterInfo();
                parameterInfo.setParameterName(param.getName());
                parameterInfo.setParameterLabel(param.getName());
                parameterInfo.setParameterDescription(param.getDescription());
                parameterInfo.setMultiValue(param.isMultiValued());
                parameterInfo.setOptional(!param.isMandatory());
                
                switch (param.getType()) {
                case ConfigurationParameter.TYPE_BOOLEAN:
                    parameterInfo.setParameterType(ParameterTypeEnum.BOOLEAN);
                    break;
                case ConfigurationParameter.TYPE_FLOAT:
                    parameterInfo.setParameterType(ParameterTypeEnum.FLOAT);
                    break;
                case ConfigurationParameter.TYPE_INTEGER:
                    parameterInfo.setParameterType(ParameterTypeEnum.INTEGER);
                    break;
                case ConfigurationParameter.TYPE_STRING:
                    parameterInfo.setParameterType(ParameterTypeEnum.STRING);
                    break;
                }
                
                Object value = aSpecifier.getConfigurationParameterSettings()
                        .getParameterValue(param.getName());
                
                if (value != null) {
                    if (param.isMultiValued()) {
                        for (Object v : (Object[]) value) {
                            parameterInfo.getDefaultValue().add(String.valueOf(v));
                        }
                    }
                    else {
                        parameterInfo.getDefaultValue().add(String.valueOf(value));
                    }
                }
                
                aDescriptor.getInputContentResourceInfo().getParameterInfos().add(parameterInfo);
            }
        }
        
        // Language capabilities
        if (aSpecifier.getCapabilities() != null) {
            for (Capability capability : aSpecifier.getCapabilities()) {
                ProcessingResourceInfo procInfo = aDescriptor.getInputContentResourceInfo();
                if (procInfo == null) {
                    procInfo = new ProcessingResourceInfo();
                    aDescriptor.setInputContentResourceInfo(procInfo);
                }

                if (capability.getLanguagesSupported() != null) {
                    for (String lang : capability.getLanguagesSupported()) {
                        Language language = new Language();
                        language.setLanguageTag(lang);
                        procInfo.getLanguages().add(language);
                    }
                }
            }
        }
        
        // Mime type capabilities
        if (aSpecifier.getCapabilities() != null) {
            for (Capability capability : aSpecifier.getCapabilities()) {
                ProcessingResourceInfo procInfo;
                // For readers, we should attach the mime type capabilites on input, for other
                // components (which are then likely writers) on output.
                if (aCapabilitiesOnInput) {
                    procInfo = aDescriptor.getInputContentResourceInfo();
                    if (procInfo == null) {
                        procInfo = new ProcessingResourceInfo();
                        aDescriptor.setInputContentResourceInfo(procInfo);
                    }
                }
                else {
                    procInfo = aDescriptor.getOutputResourceInfo();
                    if (procInfo == null) {
                        procInfo = new ProcessingResourceInfo();
                        aDescriptor.setOutputResourceInfo(procInfo);
                    }
                }

                if (capability.getMimeTypesSupported() != null) {
                    for (String mimeType : capability.getMimeTypesSupported()) {
                        try {
                            DataFormatInfo dataFormatInfo = new DataFormatInfo();
                            dataFormatInfo.setMimeType(MimeTypeEnum.fromValue(mimeType));
                            procInfo.getDataFormats().add(dataFormatInfo);
                        }
                        catch (IllegalArgumentException e) {
                            System.err.println("Unsupported mime type : [" + mimeType + "]");
                        }
                    }
                }
            }
        }
    }
}
