package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createDescription;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createGroupName;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createResourceName;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.resource.ResourceCreationSpecifier;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.ResourceMetaData;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ComponentTypeEnum;
import eu.openminted.registry.domain.CopyrightStatement;
import eu.openminted.registry.domain.FrameworkEnum;
import eu.openminted.registry.domain.GroupInfo;
import eu.openminted.registry.domain.ParameterInfo;
import eu.openminted.registry.domain.ParameterTypeEnum;
import eu.openminted.registry.domain.ResourceTypeEnum;

public class UimaDescriptorAnalyzer
    implements Analyzer<ResourceCreationSpecifier>
{
    @Override
    public void analyze(Component aDescriptor, ResourceCreationSpecifier aSpecifier)
    {
        ComponentInfo componentInfo = aDescriptor.getComponentInfo();
        
        componentInfo.setResourceType(ResourceTypeEnum.COMPONENT);
        componentInfo.getComponentCreationInfo().setFramework(FrameworkEnum.UIMA);
        componentInfo.getComponentCreationInfo().setImplementationLanguage("Java");
        
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
        analyzeMetadata(aDescriptor, aSpecifier.getAnalysisEngineMetaData());
    }

    private void analyzeReader(ComponentInfo aDescriptor, CollectionReaderDescription aSpecifier)
    {
        analyzeMetadata(aDescriptor, aSpecifier.getCollectionReaderMetaData());
    }
    
    private void analyzeMetadata(ComponentInfo aDescriptor, ResourceMetaData aSpecifier)
    {
        String copyright = aSpecifier.getCopyright();
        if (isNotBlank(copyright)) {
            ComponentDistributionInfo distributionInfo = new ComponentDistributionInfo();
            CopyrightStatement copyrightStatement = new CopyrightStatement();
            copyrightStatement.setValue(copyright);
            distributionInfo.getCopyrightStatements().add(copyrightStatement);
            aDescriptor.getDistributionInfos().add(distributionInfo);
        }
        
        String description = aSpecifier.getDescription();
        if (isNotBlank(description)) {
            aDescriptor.getIdentificationInfo().getDescriptions()
                    .add(createDescription(description));
        }
        
        String name = aSpecifier.getName();
        if (isNotBlank(name)) {
            aDescriptor.getIdentificationInfo().getResourceNames().add(createResourceName(name));
        }
        
        String uuid = aSpecifier.getUUID();
        
        String vendor = aSpecifier.getVendor();
        if (isNotBlank(vendor)) {
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.getGroupNames().add(createGroupName(vendor));
            aDescriptor.getContactInfo().getContactGroups().add(groupInfo);
        }
        
        String version = aSpecifier.getVersion();
        if (isNotBlank(version)) {
            aDescriptor.getVersionInfo().setVersion(version);
        }
        
        for (ConfigurationParameter param : aSpecifier.getConfigurationParameterDeclarations()
                .getConfigurationParameters()) {

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
            
            // FIXME this obviously breaks for multi-valued parameters atm!
            parameterInfo.getDefaultValue().add(String.valueOf(value));
            if (param.isMultiValued()) {
                throw new UnsupportedOperationException("Support for multi-valued params missing");
            }
            
            aDescriptor.getInputContentResourceInfo().getParameterInfos().add(parameterInfo);
        }
    }
}
