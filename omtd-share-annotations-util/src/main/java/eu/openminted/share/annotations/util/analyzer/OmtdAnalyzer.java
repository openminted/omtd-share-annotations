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

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createPersonInfo;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.getOrCreateInputContentResourceInfo;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.getOrCreateOutputResourceInfo;
import static eu.openminted.share.annotations.util.internal.ReflectionUtil.getInheritableAnnotation;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.interpolation.InterpolationException;
import org.codehaus.plexus.interpolation.PropertiesBasedValueSource;
import org.codehaus.plexus.interpolation.StringSearchInterpolator;

import eu.openminted.registry.domain.AnnotationTypeInfo;
import eu.openminted.registry.domain.AnnotationTypeType;
import eu.openminted.registry.domain.CharacterEncodingEnum;
import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.registry.domain.DataFormatInfo;
import eu.openminted.registry.domain.DataFormatType;
import eu.openminted.registry.domain.DocumentationTypeEnum;
import eu.openminted.registry.domain.FunctionInfo;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.OperationType;
import eu.openminted.registry.domain.ParameterInfo;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.registry.domain.ProcessingResourceTypeEnum;
import eu.openminted.registry.domain.PublicationIdentifier;
import eu.openminted.registry.domain.PublicationIdentifierSchemeNameEnum;
import eu.openminted.registry.domain.ResourceDocumentationInfo;
import eu.openminted.registry.domain.ResourceName;
import eu.openminted.registry.domain.RightsInfo;
import eu.openminted.share.annotations.api.ContactPerson;
import eu.openminted.share.annotations.api.DataFormat;
import eu.openminted.share.annotations.api.DocumentationIdentifier;
import eu.openminted.share.annotations.api.DocumentationResource;
import eu.openminted.share.annotations.api.DocumentationResources;
import eu.openminted.share.annotations.api.Language;
import eu.openminted.share.annotations.api.Parameters;
import eu.openminted.share.annotations.api.ResourceInput;
import eu.openminted.share.annotations.api.ResourceOutput;

public class OmtdAnalyzer
    implements Analyzer<Class<?>>
{
    private static final String PROP_RESOURCE_NAME_ADDON = "resourceNameAddon";

    private static final String PROP_RESOURCE_COPYRIGHT = "resourceCopyright";

    private static final String PROP_LOCAL_SHORT_CLASS_NAME = "shortClassName";

    private static final String PROP_LOCAL_COMMAND = "command";

    private static final String PROP_LOCAL_VERSION = "version";

    private final Log log = LogFactory.getLog(getClass());
    
    private Properties properties = new Properties();
    
    @Override
    public void analyze(Component aDescriptor, Class<?> aComponent) throws AnalyzerException
    {
        eu.openminted.share.annotations.api.Component annoComponent = getInheritableAnnotation(
                eu.openminted.share.annotations.api.Component.class, aComponent);
        
        ContactPerson annoContactPerson = getInheritableAnnotation(ContactPerson.class, aComponent);

        ResourceInput annoResourceInput = getInheritableAnnotation(ResourceInput.class, aComponent);

        ResourceOutput annoResourceOutput = getInheritableAnnotation(ResourceOutput.class, aComponent);

        Parameters annoParameters = getInheritableAnnotation(Parameters.class, aComponent);

        DocumentationResources annoDocumentations = getInheritableAnnotation(
                DocumentationResources.class, aComponent);

        DocumentationResource annoDocumentation = getInheritableAnnotation(
                DocumentationResource.class, aComponent);

        if ((annoComponent != null) || (annoContactPerson != null) || (annoResourceInput != null)
                || (annoResourceOutput != null) || (annoParameters != null)
                || (annoDocumentation != null)) {
            ComponentInfo componentInfo = aDescriptor.getComponentInfo();
            if (componentInfo == null) {
                componentInfo = new ComponentInfo();
                aDescriptor.setComponentInfo(componentInfo);
            }
            
            if (annoComponent != null) {
                analyzeComponent(componentInfo, annoComponent);
            }

            if (annoContactPerson != null) {
                analyzeContactPerson(componentInfo, annoContactPerson);
            }

            if (annoResourceInput != null) {
                analyzeResourceInput(componentInfo, annoResourceInput);
            }

            if (annoResourceOutput != null) {
                analyzeResourceOutput(componentInfo, annoResourceOutput);
            }
            
            if (annoParameters != null) {
                analyzeParameters(componentInfo, annoParameters);
            }
            
            try {
                if (annoDocumentations != null) {
                    for (DocumentationResource res : annoDocumentations.value()) {
                        analyzeDocumentation(componentInfo, res);
                    }
                }
                else if (annoDocumentation != null) {
                    analyzeDocumentation(componentInfo, annoDocumentation);
                }
            }
            catch (InterpolationException e) {
                throw new AnalyzerException(e);
            }
        }
    }
    
    private void analyzeDocumentation(ComponentInfo aComponentInfo,
            DocumentationResource aAnnoDocumentation) throws InterpolationException
    {
        Properties localProperties = new Properties(properties);
        localProperties.setProperty(PROP_LOCAL_VERSION, aComponentInfo.getVersionInfo().getVersion());
        String command = aComponentInfo.getDistributionInfos().get(0).getCommand();
        localProperties.setProperty(PROP_LOCAL_COMMAND, command);
        if (command.contains(".")) {
            localProperties.setProperty(PROP_LOCAL_SHORT_CLASS_NAME,
                    StringUtils.substringAfterLast(command, "."));
        }
        
        StringSearchInterpolator interpolator = new StringSearchInterpolator();
        interpolator.addValueSource(new PropertiesBasedValueSource(localProperties));
        
        ResourceDocumentationInfo docInfo = new ResourceDocumentationInfo();
        
        docInfo.setDocumentationDescription(
                StringUtils.trimToNull(interpolator.interpolate(aAnnoDocumentation.value())));
        docInfo.setDocumentationType(DocumentationTypeEnum.fromValue(aAnnoDocumentation.type()));

        for (DocumentationIdentifier annoId : aAnnoDocumentation.id()) {
            PublicationIdentifier pubId = new PublicationIdentifier();
            pubId.setValue(StringUtils.trimToNull(interpolator.interpolate(annoId.value())));
            pubId.setSchemeURI(annoId.schemeURI());
            pubId.setPublicationIdentifierSchemeName(
                    PublicationIdentifierSchemeNameEnum.fromValue(annoId.scheme()));
            docInfo.getPublicationIdentifiers().add(pubId);
        }

        aComponentInfo.getResourceDocumentations().add(docInfo);
    }

    private void analyzeParameters(ComponentInfo aComponentInfo, Parameters aAnnoParameters)
    {
        List<ParameterInfo> parameterInfos = aComponentInfo.getParameterInfos();
     
        // Remove all excluded parameters
        if (aAnnoParameters.exclude() != null && parameterInfos != null) {
            Set<String> hiddenParameters = new HashSet<>(asList(aAnnoParameters.exclude()));

            Iterator<ParameterInfo> i = parameterInfos.iterator();
            while (i.hasNext()) {
                ParameterInfo paramInfo = i.next();
                if (hiddenParameters.contains(paramInfo.getParameterName())) {
                    if (!(paramInfo.isOptional() || paramInfo.getDefaultValue() != null)) {
                        throw new IllegalStateException(
                                "Excluded parameters must either be optional or have a default value.");
                    }
                    
                    i.remove();
                }
            }
        }
    }

    private void analyzeResourceInput(ComponentInfo aComponentInfo,
            ResourceInput aAnnoResourceInput)
    {
        ProcessingResourceInfo procInfo = getOrCreateInputContentResourceInfo(aComponentInfo);
        procInfo.setProcessingResourceType(
                ProcessingResourceTypeEnum.fromValue(aAnnoResourceInput.type()));

        analyzeLanguage(procInfo, aAnnoResourceInput.language());
        analyzeEncoding(procInfo, aAnnoResourceInput.encoding());
        analyzeDataFormat(procInfo, aAnnoResourceInput.dataFormat());
        // See: https://github.com/openminted/omtd-share-annotations/issues/31
        // analyzeKeyword(procInfo, aAnnoResourceInput.keyword());
        analyzeAnnotationLevel(procInfo, aAnnoResourceInput.annotationLevel());
    }
    
    private void analyzeResourceOutput(ComponentInfo aComponentInfo,
            ResourceOutput aAnnoResourceOutput)
    {
        ProcessingResourceInfo procInfo = getOrCreateOutputResourceInfo(aComponentInfo);
        procInfo.setProcessingResourceType(
                ProcessingResourceTypeEnum.fromValue(aAnnoResourceOutput.type()));
        
        analyzeLanguage(procInfo, aAnnoResourceOutput.language());
        analyzeEncoding(procInfo, aAnnoResourceOutput.encoding());
        analyzeDataFormat(procInfo, aAnnoResourceOutput.dataFormat());
        // See: https://github.com/openminted/omtd-share-annotations/issues/31
        // analyzeKeyword(procInfo, aAnnoResourceOutput.keyword());
        analyzeAnnotationLevel(procInfo, aAnnoResourceOutput.annotationLevel());                
    }
    
    private void analyzeEncoding(ProcessingResourceInfo aProcInfo, String... aEncodings)
    {
        for (String encoding : aEncodings) {
            try {
                aProcInfo.getCharacterEncodings().add(CharacterEncodingEnum.fromValue(encoding));
            }
            catch (IllegalArgumentException e) {
                log.warn("Unknown encoding: [" + encoding + "] - skipped");
            }
        }
    }
    
    private void analyzeLanguage(ProcessingResourceInfo aProcInfo, Language... aAnnoLanguages)
    {
        for (Language annoLanguage : aAnnoLanguages) {
        	// This was used with v2 of the schema but now it just wants the language ID
//            eu.openminted.registry.domain.Language language = new eu.openminted.registry.domain.Language();
//            language.setLanguageTag(annoLanguage.languageTag());	
//            if (isNotBlank(annoLanguage.languageId())) {
//                language.setLanguageId(annoLanguage.languageId());
//            }
//            if (isNotBlank(annoLanguage.scriptId())) {
//                try {
//                    language.setScriptId(ScriptIdType.fromValue(annoLanguage.scriptId()));;
//                }
//                catch (IllegalArgumentException e) {
//                    System.err.println("Unsupported script id : [" + annoLanguage.scriptId() + "]");
//                }
//            }
//            if (isNotBlank(annoLanguage.regiontId())) {
//                try {
//                    language.setRegiontId(RegionIdType.fromValue(annoLanguage.regiontId()));;
//                }
//                catch (IllegalArgumentException e) {
//                    System.err.println("Unsupported region id : [" + annoLanguage.regiontId() + "]");
//                }
//            }
//            if (isNotBlank(annoLanguage.variantId())) {
//                try {
//                    language.setVariantId(VariantIdType.fromValue(annoLanguage.variantId()));;
//                }
//                catch (IllegalArgumentException e) {
//                    System.err.println("Unsupported variant id : [" + annoLanguage.variantId() + "]");
//                }
//            }
            aProcInfo.getLanguages().add(annoLanguage.languageId());
        }
    }
    
    private void analyzeAnnotationLevel(ProcessingResourceInfo aProcInfo, String[] aAnnotationLevel)
    {
        for (String level : aAnnotationLevel) {
        	AnnotationTypeInfo annotTypeInfo = new AnnotationTypeInfo();
        	try {
                annotTypeInfo.setAnnotationType(AnnotationTypeType.fromValue(level));
            }
            catch (IllegalArgumentException e) {
                log.warn("Unknown annotation level: [" + level
                        + "] - recording as 'annotationTypeOther'");
                annotTypeInfo.setAnnotationTypeOther(level);
            }

            aProcInfo.getAnnotationTypes().add(annotTypeInfo);
        }
    }
    
    // See: https://github.com/openminted/omtd-share-annotations/issues/31
//    private void analyzeKeyword(ProcessingResourceInfo aProcInfo, String[] aKeyword)
//    {
//        //TODO were have keywords moved to or have they been removed
//    	//aProcInfo.getKeywords().addAll(asList(aKeyword));
//    }

    private void analyzeDataFormat(ProcessingResourceInfo aProcInfo, DataFormat[] aDataFormats)
    {
        for (DataFormat dataFormat : aDataFormats) {
            DataFormatInfo dataFormatInfo = new DataFormatInfo();
            try {
                dataFormatInfo.setDataFormat(DataFormatType.fromValue(dataFormat.dataFormat()));
            }
            catch (IllegalArgumentException e) {
                log.warn("Unknown data format: [" + dataFormat.dataFormat() + "] - skipped");
            }
            
            // See: https://github.com/openminted/omtd-share-annotations/issues/32
//            try {
//                dataFormatInfo.setDataFormat(DataFormatType.fromValue(dataFormat.mimeType()));
//            }
//            catch (IllegalArgumentException e) {
//                System.err.println("Unsupported mime type: [" + dataFormat.mimeType() + "]");
//            }
//            if (isNotBlank(dataFormat.description())) {
//                dataFormatInfo.setDataFormatDescription(dataFormat.description());
//            }
//            if (isNotBlank(dataFormat.documentationURL())) {
//                dataFormatInfo.setDocumentationURL(dataFormat.documentationURL());
//            }
//            if (isNotBlank(dataFormat.fileExtension())) {
//                dataFormatInfo.setFileExtension(dataFormat.fileExtension());
//            }
            
            aProcInfo.getDataFormats().add(dataFormatInfo);
        }
    }
    
    private void analyzeComponent(ComponentInfo aComponentInfo,
            eu.openminted.share.annotations.api.Component aComponentAnno)
    {
        if (aComponentAnno.value() != null) {
            FunctionInfo functionInfo = new FunctionInfo();
            try {
                functionInfo.setFunction(OperationType.fromValue(aComponentAnno.value()));
            }
            catch (Exception e) {
                functionInfo.setFunctionOther(aComponentAnno.value());
            }
            aComponentInfo.setFunctionInfo(functionInfo);
        }
        
        // If the Maven plugin configuration specifies a copyright, overwrite existing copyright
        // information.
        String copyright = properties.getProperty(PROP_RESOURCE_COPYRIGHT);
        if (isNotBlank(copyright)) {
            // If there already is a distribution info, then update it instead of creating a new
            // one.
            ComponentDistributionInfo distributionInfo;
            if (!aComponentInfo.getDistributionInfos().isEmpty()) {
                distributionInfo = aComponentInfo.getDistributionInfos().get(0);
            }
            else {
                distributionInfo = new ComponentDistributionInfo();
                aComponentInfo.getDistributionInfos().add(distributionInfo);
            }
            
            if (aComponentInfo.getRightsInfo() == null) {
                aComponentInfo.setRightsInfo(new RightsInfo());
            }
            aComponentInfo.getRightsInfo().setCopyrightStatement(copyright);
        }
        
        String nameAddOn = properties.getProperty(PROP_RESOURCE_NAME_ADDON);
        if (isNotBlank(nameAddOn)) {

            IdentificationInfo identificationInfo = aComponentInfo.getIdentificationInfo();
            if (identificationInfo == null) {
                identificationInfo = new IdentificationInfo();
                aComponentInfo.setIdentificationInfo(identificationInfo);
            }

            for (ResourceName name : identificationInfo.getResourceNames()) {
                name.setValue(name.getValue() + " " + nameAddOn);
            }
        }
    }
    
    private void analyzeContactPerson(ComponentInfo aComponentInfo, ContactPerson aComponentAnno)
    {
        ContactInfo contactInfo = aComponentInfo.getContactInfo();
        if (contactInfo == null) {
            contactInfo = new ContactInfo();
            aComponentInfo.setContactInfo(contactInfo);
        }
        
        contactInfo.getContactPersons()
                .add(createPersonInfo(aComponentAnno.name(), aComponentAnno.mail()));
    }

    public void setProperties(Map<String, String> aProperties)
    {
        properties = new Properties();
        if (aProperties != null) {
            properties.putAll(aProperties);
        }
    }
}
