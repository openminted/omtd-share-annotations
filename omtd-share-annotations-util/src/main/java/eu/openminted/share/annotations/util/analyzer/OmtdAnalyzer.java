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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.openminted.registry.domain.AnnotationTypeInfo;
import eu.openminted.registry.domain.AnnotationTypeType;
import eu.openminted.registry.domain.CharacterEncodingEnum;
import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.registry.domain.DataFormatInfo;
import eu.openminted.registry.domain.DataFormatType;
import eu.openminted.registry.domain.FunctionInfo;
import eu.openminted.registry.domain.OperationType;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.registry.domain.ProcessingResourceTypeEnum;
import eu.openminted.share.annotations.api.ContactPerson;
import eu.openminted.share.annotations.api.DataFormat;
import eu.openminted.share.annotations.api.Language;
import eu.openminted.share.annotations.api.ResourceInput;
import eu.openminted.share.annotations.api.ResourceOutput;

public class OmtdAnalyzer
    implements Analyzer<Class<?>>
{
    private final Log log = LogFactory.getLog(getClass());
    
    @Override
    public void analyze(Component aDescriptor, Class<?> aComponent)
    {
        eu.openminted.share.annotations.api.Component annoComponent = getInheritableAnnotation(
                eu.openminted.share.annotations.api.Component.class, aComponent);
        
        ContactPerson annoContactPerson = getInheritableAnnotation(ContactPerson.class, aComponent);

        ResourceInput annoResourceInput = getInheritableAnnotation(ResourceInput.class, aComponent);

        ResourceOutput annoResourceOutput = getInheritableAnnotation(ResourceOutput.class, aComponent);

        if ((annoComponent != null) || (annoContactPerson != null) || (annoResourceInput != null)
                || (annoResourceOutput != null)) {
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
}
