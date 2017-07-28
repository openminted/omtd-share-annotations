package eu.openminted.share.annotations.util.analyzer;

import static org.apache.commons.lang3.StringUtils.*;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createPersonInfo;
import static eu.openminted.share.annotations.util.internal.ReflectionUtil.getInheritableAnnotation;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.registry.domain.DataFormatEnum;
import eu.openminted.registry.domain.DataFormatInfo;
import eu.openminted.registry.domain.MimeTypeEnum;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.share.annotations.api.ContactPerson;
import eu.openminted.share.annotations.api.DataFormat;
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

        if ((annoComponent != null) || (annoContactPerson != null)) {
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
        if (aAnnoResourceInput.dataFormats() != null) {
            for (DataFormat dataFormat : aAnnoResourceInput.dataFormats()) {
                ProcessingResourceInfo procInfo = aComponentInfo.getInputContentResourceInfo();
                if (procInfo == null) {
                    procInfo = new ProcessingResourceInfo();
                    aComponentInfo.setInputContentResourceInfo(procInfo);
                }
                
                analyzeDataFormat(procInfo, dataFormat);
            }
        }
    }
    
    private void analyzeResourceOutput(ComponentInfo aComponentInfo,
            ResourceOutput aAnnoResourceOutput)
    {
        if (aAnnoResourceOutput.dataFormats() != null) {
            for (DataFormat dataFormat : aAnnoResourceOutput.dataFormats()) {
                ProcessingResourceInfo procInfo = aComponentInfo.getOutputResourceInfo();
                if (procInfo == null) {
                    procInfo = new ProcessingResourceInfo();
                    aComponentInfo.setOutputResourceInfo(procInfo);
                }
                
                analyzeDataFormat(procInfo, dataFormat);
            }
        }
    }
    
    private void analyzeDataFormat(ProcessingResourceInfo aProcInfo, DataFormat aDataFormat)
    {
        DataFormatInfo dataFormatInfo = new DataFormatInfo();
        try {
            dataFormatInfo.setDataFormat(DataFormatEnum.fromValue(aDataFormat.dataFormat()));
        }
        catch (IllegalArgumentException e) {
            System.err.println("Unsupported data format: [" + aDataFormat.dataFormat() + "]");
        }
        try {
            dataFormatInfo.setMimeType(MimeTypeEnum.fromValue(aDataFormat.mimeType()));
        }
        catch (IllegalArgumentException e) {
            System.err.println("Unsupported mime type: [" + aDataFormat.mimeType() + "]");
        }
        if (isNotBlank(aDataFormat.description())) {
            dataFormatInfo.setDataFormatDescription(aDataFormat.description());
        }
        if (isNotBlank(aDataFormat.documentationURL())) {
            dataFormatInfo.setDocumentationURL(aDataFormat.documentationURL());
        }
        if (isNotBlank(aDataFormat.fileExtension())) {
            dataFormatInfo.setFileExtension(aDataFormat.fileExtension());
        }
        
        aProcInfo.getDataFormats().add(dataFormatInfo);
    }

    private void analyzeComponent(ComponentInfo aComponentInfo,
            eu.openminted.share.annotations.api.Component aComponentAnno)
    {
        if (aComponentAnno.value() != null) {
            aComponentInfo.setComponentType(aComponentAnno.value());
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
