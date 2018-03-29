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
package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.*;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createGroupName;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createResourceName;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.TypeOrFeature;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.resource.ResourceCreationSpecifier;
import org.apache.uima.resource.metadata.Capability;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.ProcessingResourceMetaData;

import eu.openminted.registry.domain.AnnotationTypeInfo;
import eu.openminted.registry.domain.AnnotationTypeType;
import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentCreationInfo;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.registry.domain.DataFormatInfo;
import eu.openminted.registry.domain.DataFormatType;
import eu.openminted.registry.domain.FrameworkEnum;
import eu.openminted.registry.domain.FunctionInfo;
import eu.openminted.registry.domain.GroupInfo;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.OperatingSystemEnum;
import eu.openminted.registry.domain.OperationType;
import eu.openminted.registry.domain.ParameterInfo;
import eu.openminted.registry.domain.ParameterTypeEnum;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.registry.domain.ProcessingResourceTypeEnum;
import eu.openminted.registry.domain.ResourceTypeEnum;
import eu.openminted.registry.domain.RightsInfo;
import eu.openminted.registry.domain.VersionInfo;
import eu.openminted.share.annotations.util.ComponentDescriptorFactory;

public class UimaDescriptorAnalyzer
    implements Analyzer<ResourceCreationSpecifier>
{
    private final Log log = LogFactory.getLog(getClass());
    
    private Map<String, String> uimaTypeToAnnotationTypeMapping = Collections.emptyMap();
    private Map<String, String> mimeTypeToDataFormatMapping = Collections.emptyMap();
 
    public void setUimaTypeToAnnotationTypeMappings(
            Map<String, String> aUimaTypeToAnnotationTypeMapping)
    {
        if (aUimaTypeToAnnotationTypeMapping == null) {
            uimaTypeToAnnotationTypeMapping = Collections.emptyMap();
        }
        else {
            uimaTypeToAnnotationTypeMapping = Collections.unmodifiableMap(
                    new HashMap<>(aUimaTypeToAnnotationTypeMapping));
        }
    }
    
    public void setMimeTypeToDataFormatMappings(Map<String, String> aMimeTypeMappings)
    {
        if (aMimeTypeMappings == null) {
            mimeTypeToDataFormatMapping = Collections.emptyMap();
        }
        else {
            mimeTypeToDataFormatMapping = Collections.unmodifiableMap(
                    new HashMap<>(aMimeTypeMappings));
        }
    }
    
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
        
        ComponentDistributionInfo distributionInfo = new ComponentDistributionInfo();
        //distributionInfo.setCommand(aSpecifier.getImplementationName());
        distributionInfo.setOperatingSystems(asList(OperatingSystemEnum.OS_INDEPENDENT));
        componentInfo.getDistributionInfos().add(distributionInfo);
        
        if (aSpecifier instanceof AnalysisEngineDescription) {
            analyzeEngine(componentInfo, (AnalysisEngineDescription) aSpecifier);
        }
        
        if (aSpecifier instanceof CollectionReaderDescription) {
            FunctionInfo functioninfo = new FunctionInfo();
            functioninfo.setFunction(OperationType.HTTP___W3ID_ORG_META_SHARE_OMTD_SHARE_READER);
        	componentInfo.setFunctionInfo(functioninfo);
            analyzeReader(componentInfo, (CollectionReaderDescription) aSpecifier);
        }
        
        if (componentInfo.getFunctionInfo() == null) {
        	FunctionInfo functioninfo = new FunctionInfo();
            functioninfo.setFunction(OperationType.HTTP___W3ID_ORG_META_SHARE_OMTD_SHARE_PROCESSOR);
        	componentInfo.setFunctionInfo(functioninfo);
        }
    }

    private void analyzeEngine(ComponentInfo aDescriptor, AnalysisEngineDescription aSpecifier)
    {
        analyzeMetadata(aDescriptor, aSpecifier.getAnalysisEngineMetaData(), false);
    }

    private void analyzeReader(ComponentInfo aDescriptor, CollectionReaderDescription aSpecifier)
    {
        analyzeMetadata(aDescriptor, aSpecifier.getCollectionReaderMetaData(), true);
    }
    
    private void analyzeMetadata(ComponentInfo aDescriptor, ProcessingResourceMetaData aSpecifier,
            boolean aDataFormatOnInput)
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
            
            if (aDescriptor.getRightsInfo() == null) {
            	aDescriptor.setRightsInfo(new RightsInfo());
            }
            aDescriptor.getRightsInfo().setCopyrightStatement(copyright);
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
            else {
                identificationInfo.getDescriptions().add(createDescription("no description"));
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
            ProcessingResourceInfo processingResourceInfo = ComponentDescriptorFactory
                    .getOrCreateInputContentResourceInfo(aDescriptor);
            
            List<ParameterInfo> parameterInfos = new ArrayList<ParameterInfo>();
            
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
                
                parameterInfos.add(parameterInfo);
            }
            
            aDescriptor.setParameterInfos(parameterInfos);
        }
        
        // OMTD input/output type
        // Assume that we are always processing documents
        getOrCreateInputContentResourceInfo(aDescriptor)
                .setProcessingResourceType(ProcessingResourceTypeEnum.DOCUMENT);
        getOrCreateOutputResourceInfo(aDescriptor)
                .setProcessingResourceType(ProcessingResourceTypeEnum.DOCUMENT);
        
        // Language capabilities
        if (aSpecifier.getCapabilities() != null) {
            for (Capability capability : aSpecifier.getCapabilities()) {
                if (capability.getLanguagesSupported() != null) {
                    getOrCreateOutputResourceInfo(aDescriptor).getLanguages()
                            .addAll(Arrays.asList(capability.getLanguagesSupported()));
                    getOrCreateInputContentResourceInfo(aDescriptor).getLanguages()
                            .addAll(Arrays.asList(capability.getLanguagesSupported()));
                }
            }
        }
        
        // Mime type capabilities
        Set<String> unmappedMimeTypes = new HashSet<>();
        if (aSpecifier.getCapabilities() != null) {
            for (Capability capability : aSpecifier.getCapabilities()) {
                if (capability.getMimeTypesSupported() != null) {
                    // For readers, we should attach the mime type capabilites on input, for other
                    // components (which are then likely writers) on output.
                    ProcessingResourceInfo procInfo = aDataFormatOnInput
                            ? getOrCreateInputContentResourceInfo(aDescriptor)
                            : getOrCreateOutputResourceInfo(aDescriptor);
                    // Assume that we are always processing documents
                    procInfo.setProcessingResourceType(ProcessingResourceTypeEnum.DOCUMENT);
                                        
                    for (String mimeType : capability.getMimeTypesSupported()) {
                        String format = mimeTypeToDataFormatMapping.get(mimeType);
                        if (format != null) {
                            try {
                                DataFormatInfo dataFormatInfo = new DataFormatInfo();
                                dataFormatInfo.setDataFormat(DataFormatType.fromValue(format));
                                procInfo.getDataFormats().add(dataFormatInfo);
                            }
                            catch (IllegalArgumentException e) {
                                log.warn("Unknown data format : [" + mimeType + "] - skipped");
                            }
                        }
                        else {
                            unmappedMimeTypes.add(mimeType);
                        }
                    }
                }
            }
        }
        if (!unmappedMimeTypes.isEmpty()) {
            for (String type : unmappedMimeTypes) {
                log.warn("Unmapped mime type: [" + type + "] - skipped");
            }
        }
        
        
        // Annotation type capabilities
        Set<String> inputs = new HashSet<>();
        Set<String> outputs = new HashSet<>();
        Set<String> unmappedAnnotationTypes = new HashSet<>();
        if (aSpecifier.getCapabilities() != null) {
            for (Capability capability : aSpecifier.getCapabilities()) {
                for (TypeOrFeature tof  : capability.getInputs()) {
                    if (tof.isType()) {
                        String type = uimaTypeToAnnotationTypeMapping.get(tof.getName());
                        if (type != null) {
                            inputs.add(type);
                        }
                        else {
                            unmappedAnnotationTypes.add(tof.getName());
                        }
                    }
                }
                for (TypeOrFeature tof  : capability.getOutputs()) {
                    if (tof.isType()) {
                        String type = uimaTypeToAnnotationTypeMapping.get(tof.getName());
                        if (type != null) {
                            outputs.add(type);
                        }
                        else {
                            unmappedAnnotationTypes.add(tof.getName());
                        }
                    }
                }
            }
        }
        
        if (!unmappedAnnotationTypes.isEmpty()) {
            for (String type : unmappedAnnotationTypes) {
                log.warn("Unmapped UIMA type: [" + type + "] - skipped");
            }
        }

        ProcessingResourceInfo inProcInfo = getOrCreateInputContentResourceInfo(aDescriptor);
        inputs.stream().sorted().forEach(input -> recordAnnotationType(inProcInfo, input));
        
        ProcessingResourceInfo outProcInfo = getOrCreateOutputResourceInfo(aDescriptor);
        outputs.stream().sorted().forEach(output -> recordAnnotationType(outProcInfo, output));
    }
    
    private void recordAnnotationType(ProcessingResourceInfo aProcInfo, String aType)
    {
        AnnotationTypeInfo annotTypeInfo = new AnnotationTypeInfo();
        
        try {
            annotTypeInfo.setAnnotationType(AnnotationTypeType.fromValue(aType));
        }
        catch (IllegalArgumentException e) {
            log.warn("Unknown annotation level: [" + aType
                    + "] - recording as 'annotationTypeOther'");
            annotTypeInfo.setAnnotationTypeOther(aType);
        }
        
        aProcInfo.getAnnotationTypes().add(annotTypeInfo);
    }
}
