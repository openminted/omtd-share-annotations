package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createPersonInfo;
import static eu.openminted.share.annotations.util.internal.ReflectionUtil.getInheritableAnnotation;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Arrays;
import java.util.stream.Collectors;

import eu.openminted.registry.domain.AnnotationLevelEnum;
import eu.openminted.registry.domain.CharacterEncodingEnum;
import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ComponentTypeEnum;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.registry.domain.DataFormatEnum;
import eu.openminted.registry.domain.DataFormatInfo;
import eu.openminted.registry.domain.MimeTypeEnum;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.registry.domain.ProcessingResourceTypeEnum;
import eu.openminted.registry.domain.RegionIdType;
import eu.openminted.registry.domain.ScriptIdType;
import eu.openminted.registry.domain.VariantIdType;
import eu.openminted.share.annotations.api.ContactPerson;
import eu.openminted.share.annotations.api.DataFormat;
import eu.openminted.share.annotations.api.Language;
import eu.openminted.share.annotations.api.ResourceInput;
import eu.openminted.share.annotations.api.ResourceOutput;

public class OmtdAnalyzer
    implements Analyzer<Class<?>>
{
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
        ProcessingResourceInfo procInfo = aComponentInfo.getInputContentResourceInfo();
        if (procInfo == null) {
            procInfo = new ProcessingResourceInfo();
            aComponentInfo.setInputContentResourceInfo(procInfo);
        }
        
        procInfo.setProcessingResourceTypes(Arrays.stream(aAnnoResourceInput.type())
                .map(s -> ProcessingResourceTypeEnum.fromValue(s)).collect(Collectors.toList()));

        analyzeLanguage(procInfo, aAnnoResourceInput.language());                
        analyzeEncoding(procInfo, aAnnoResourceInput.encoding());                
        analyzeDataFormat(procInfo, aAnnoResourceInput.dataFormat());
        analyzeKeyword(procInfo, aAnnoResourceInput.keyword());
        analyzeAnnotationLevel(procInfo, aAnnoResourceInput.annotationLevel());                
    }
    
    private void analyzeResourceOutput(ComponentInfo aComponentInfo,
            ResourceOutput aAnnoResourceOutput)
    {
        ProcessingResourceInfo procInfo = aComponentInfo.getOutputResourceInfo();
        if (procInfo == null) {
            procInfo = new ProcessingResourceInfo();
            aComponentInfo.setOutputResourceInfo(procInfo);
        }
        
        procInfo.setProcessingResourceTypes(Arrays.stream(aAnnoResourceOutput.type())
                .map(s -> ProcessingResourceTypeEnum.fromValue(s)).collect(Collectors.toList()));
        
        analyzeLanguage(procInfo, aAnnoResourceOutput.language());                
        analyzeEncoding(procInfo, aAnnoResourceOutput.encoding());                
        analyzeDataFormat(procInfo, aAnnoResourceOutput.dataFormat());
        analyzeKeyword(procInfo, aAnnoResourceOutput.keyword());
        analyzeAnnotationLevel(procInfo, aAnnoResourceOutput.annotationLevel());                
    }
    
    private void analyzeEncoding(ProcessingResourceInfo aProcInfo, String... aEncodings)
    {
        for (String encoding : aEncodings) {
            try {
                aProcInfo.getCharacterEncodings().add(CharacterEncodingEnum.fromValue(encoding));
            }
            catch (IllegalArgumentException e) {
                System.err.println("Unsupported encoding: [" + encoding + "]");
            }
        }
    }
    
    private void analyzeLanguage(ProcessingResourceInfo aProcInfo, Language... aAnnoLanguages)
    {
        for (Language annoLanguage : aAnnoLanguages) {
            eu.openminted.registry.domain.Language language = new eu.openminted.registry.domain.Language();
            language.setLanguageTag(annoLanguage.languageTag());
            if (isNotBlank(annoLanguage.languageId())) {
                language.setLanguageId(annoLanguage.languageId());
            }
            if (isNotBlank(annoLanguage.scriptId())) {
                try {
                    language.setScriptId(ScriptIdType.fromValue(annoLanguage.scriptId()));;
                }
                catch (IllegalArgumentException e) {
                    System.err.println("Unsupported script id : [" + annoLanguage.scriptId() + "]");
                }
            }
            if (isNotBlank(annoLanguage.regionId())) {
                try {
                    language.setRegiontId(RegionIdType.fromValue(annoLanguage.regionId()));;
                }
                catch (IllegalArgumentException e) {
                    System.err.println("Unsupported region id : [" + annoLanguage.regionId() + "]");
                }
            }
            if (isNotBlank(annoLanguage.variantId())) {
                try {
                    language.setVariantId(VariantIdType.fromValue(annoLanguage.variantId()));;
                }
                catch (IllegalArgumentException e) {
                    System.err.println("Unsupported variant id : [" + annoLanguage.variantId() + "]");
                }
            }
            aProcInfo.getLanguages().add(language);
        }
    }
    
    private void analyzeAnnotationLevel(ProcessingResourceInfo aProcInfo, String[] aAnnotationLevel)
    {
        for (String level : aAnnotationLevel) {
            try {
                aProcInfo.getAnnotationLevels().add(AnnotationLevelEnum.fromValue(level));
            }
            catch (IllegalArgumentException e) {
                System.err.println("Unsupported annotation level: [" + level + "]");
            }
        }
    }
    
    private void analyzeKeyword(ProcessingResourceInfo aProcInfo, String[] aKeyword)
    {
        aProcInfo.getKeywords().addAll(asList(aKeyword));
    }

    private void analyzeDataFormat(ProcessingResourceInfo aProcInfo, DataFormat[] aDataFormats)
    {
        for (DataFormat dataFormat : aDataFormats) {
            DataFormatInfo dataFormatInfo = new DataFormatInfo();
            try {
                dataFormatInfo.setDataFormat(DataFormatEnum.fromValue(dataFormat.dataFormat()));
            }
            catch (IllegalArgumentException e) {
                System.err.println("Unsupported data format: [" + dataFormat.dataFormat() + "]");
            }
            try {
                dataFormatInfo.setMimeType(MimeTypeEnum.fromValue(dataFormat.mimeType()));
            }
            catch (IllegalArgumentException e) {
                System.err.println("Unsupported mime type: [" + dataFormat.mimeType() + "]");
            }
            if (isNotBlank(dataFormat.description())) {
                dataFormatInfo.setDataFormatDescription(dataFormat.description());
            }
            if (isNotBlank(dataFormat.documentationURL())) {
                dataFormatInfo.setDocumentationURL(dataFormat.documentationURL());
            }
            if (isNotBlank(dataFormat.fileExtension())) {
                dataFormatInfo.setFileExtension(dataFormat.fileExtension());
            }
            
            aProcInfo.getDataFormats().add(dataFormatInfo);
        }
    }
    
    private void analyzeComponent(ComponentInfo aComponentInfo,
            eu.openminted.share.annotations.api.Component aComponentAnno)
    {
        if (aComponentAnno.value() != null) {
            aComponentInfo.setComponentType(ComponentTypeEnum.fromValue(aComponentAnno.value()));
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
