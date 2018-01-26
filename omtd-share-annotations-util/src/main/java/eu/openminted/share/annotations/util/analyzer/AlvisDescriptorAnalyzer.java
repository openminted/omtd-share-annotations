package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createDescription;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createGroupName;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createResourceName;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;

import org.jdom.Element;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentCreationInfo;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.registry.domain.DataFormatInfo;
import eu.openminted.registry.domain.DataFormatType;
import eu.openminted.registry.domain.FrameworkEnum;
import eu.openminted.registry.domain.GroupInfo;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.OperatingSystemEnum;
import eu.openminted.registry.domain.ParameterInfo;
import eu.openminted.registry.domain.ParameterTypeEnum;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.registry.domain.ResourceTypeEnum;
import eu.openminted.registry.domain.RightsInfo;
import eu.openminted.registry.domain.VersionInfo;

public class AlvisDescriptorAnalyzer implements Analyzer<Element> {

	@Override
	public void analyze(Component aDescriptor, Element aObject) throws AnalyzerException {
        
		
		
		/**
		 * componentInfo
		 */
		ComponentInfo componentInfo = aDescriptor.getComponentInfo();
        if (componentInfo == null) {
            componentInfo = new ComponentInfo();
            aDescriptor.setComponentInfo(componentInfo);
        }
       
        /**
         * resource Type
         */
        componentInfo.setResourceType(ResourceTypeEnum.COMPONENT);
        

       /**
        * component name
        */
        //Element module = aObject.getChild("alvisnlp-doc");
        String name = aObject.getAttributeValue("short-target");
        if (isNotBlank(name)) {
            IdentificationInfo identificationInfo = componentInfo.getIdentificationInfo();
            if (identificationInfo == null) {
                identificationInfo = new IdentificationInfo();
                componentInfo.setIdentificationInfo(identificationInfo);
            }
            
            
            identificationInfo.getResourceNames().add(createResourceName(name));
        }
        
        /**
         * component description
         */
        Element content = aObject.getChild("module-doc");
        // alvis module descriptions are html streams, the content must be checked        
        String description = content.getChildText("description");
        if (isNotBlank(description)) {
            IdentificationInfo identificationInfo = componentInfo.getIdentificationInfo();
            if (identificationInfo == null) {
                identificationInfo = new IdentificationInfo();
                componentInfo.setIdentificationInfo(identificationInfo);
            }
            
            
            identificationInfo.getDescriptions().add(createDescription(description));
        }
        
                
        
        /**
         * componentCreationInfo
				framework(string! predefined values)
				implementationLanguage (string)
         */
        ComponentCreationInfo componentCreationInfo = componentInfo.getComponentCreationInfo();
        if (componentCreationInfo == null) {
            componentCreationInfo = new ComponentCreationInfo();
            componentInfo.setComponentCreationInfo(componentCreationInfo);
        }
        componentCreationInfo.setFramework(FrameworkEnum.ALVIS_NLP);
        componentCreationInfo.setImplementationLanguage("Java");
        
        
        /**
         * DistributionInfo(s)
				command (string)
				operatingSystems(string! predefined values)
				copyrightStatement
         */
        String copyright = "none";
        if (isNotBlank(copyright)) {
            // If there already is a distribution info, then update it instead of creating a new
            // one.
            ComponentDistributionInfo distributionInfo;
            if (!aDescriptor.getComponentInfo().getDistributionInfos().isEmpty()) {
                distributionInfo = aDescriptor.getComponentInfo().getDistributionInfos().get(0);
            }
            else {
                distributionInfo = new ComponentDistributionInfo();
                aDescriptor.getComponentInfo().getDistributionInfos().add(distributionInfo);
            }
            
            if (componentInfo.getRightsInfo() == null) {
            	componentInfo.setRightsInfo(new RightsInfo());
            }
            componentInfo.getRightsInfo().setCopyrightStatement(copyright);
        }
        ComponentDistributionInfo distributionInfo = new ComponentDistributionInfo();
        distributionInfo.setCommand(componentInfo.getIdentificationInfo().getResourceNames().get(0).getValue());
        distributionInfo.getOperatingSystems().add(OperatingSystemEnum.LINUX);
        componentInfo.getDistributionInfos().add(distributionInfo);
        
        
        /**
         * ContactInfo
			 groupInfo(s)
			 	groupName(string)
         */
        
        String group = "maiage.bibliome";
        if (isNotBlank(group)) {
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.getGroupNames().add(createGroupName(group));

            ContactInfo contactInfo = componentInfo.getContactInfo();
            if (contactInfo == null) {
                contactInfo = new ContactInfo();
                componentInfo.setContactInfo(contactInfo);
            }

            contactInfo.getContactGroups().add(groupInfo);
        }
        
        
        /**
         * 	VersionInfo
				version(string)
         */
        
        String version = "1.0.0";
        if (isNotBlank(version)) {
            VersionInfo versionInfo = componentInfo.getVersionInfo();
            if (versionInfo == null) {
                versionInfo = new VersionInfo();
                componentInfo.setVersionInfo(versionInfo);
            }
            versionInfo.setVersion(version);
        }
        
        
        /**
         * InputContentResourceInfo
				parameterInfo
					parameterName(string)
					parameterLabel(string)
					parameterDescription(string)
					parameterMultiValued(boolean)
					optional(boolean)
					parameterType(string! predefined types)
					defaultValue(string)
				LangageInfo
					language
						languageTag(string)
						languageId(string)
				DataFormatInfo
					mimeType(string)
         */
        // params 
        List<Element> paramDoc = (List<Element>) content.getChildren("param-doc");
        if (!paramDoc.isEmpty()) {
            ProcessingResourceInfo processingResourceInfo = componentInfo.getInputContentResourceInfo();
            if (processingResourceInfo == null) {
                processingResourceInfo = new ProcessingResourceInfo();
                componentInfo.setInputContentResourceInfo(processingResourceInfo);
            }
            
            for (Element param : paramDoc) {
                ParameterInfo parameterInfo = new ParameterInfo();
                parameterInfo.setParameterName(param.getAttributeValue("name"));
                parameterInfo.setParameterLabel(param.getAttributeValue("name"));
                // alvis param descriptions are html streams, the content must be checked 
                parameterInfo.setParameterDescription(param.getText());
                // multivalued info not available
                parameterInfo.setOptional(Boolean.valueOf(param.getAttributeValue("mandatory")));
                // default Value
                if (isNotBlank(param.getAttributeValue("default-value"))) {
                    parameterInfo.getDefaultValue().add(param.getAttributeValue("DEFAULT"));
                }
                
                switch (param.getAttributeValue("type")) {
                case "java.lang.Float": // fallthrough
                case "java.lang.Double":
                    parameterInfo.setParameterType(ParameterTypeEnum.FLOAT);
                    break;
                case "java.lang.Boolean":
                    parameterInfo.setParameterType(ParameterTypeEnum.BOOLEAN);
                    break;
                case "java.net.URL":
                    // currently not supported
                    break;
                case "java.lang.String":
                    parameterInfo.setParameterType(ParameterTypeEnum.STRING);
                    break;
                }
                
                componentInfo.getParameterInfos().add(parameterInfo);
            }
            
            
            
    		}
        
        /**
         * 	LangageInfo
				language
					languageTag(string)
					languageId(string)
         */
                ProcessingResourceInfo procInfo = componentInfo.getInputContentResourceInfo();
                if (procInfo == null) {
                    procInfo = new ProcessingResourceInfo();
                    componentInfo.setInputContentResourceInfo(procInfo);
                }
                
                procInfo.getLanguages().add("EN");
                
                
                
         /**
          * DataFormatInfo
				mimeType(string)
          */
                ProcessingResourceInfo procInfo4output = componentInfo.getOutputResourceInfo();
                if (procInfo4output == null) {
                	procInfo4output = new ProcessingResourceInfo();
                	componentInfo.setOutputResourceInfo(procInfo);
                }
                DataFormatInfo dataFormatInfo = new DataFormatInfo();
                dataFormatInfo.setDataFormat(DataFormatType.HTTP___W3ID_ORG_META_SHARE_OMTD_SHARE_TEXT);
                componentInfo.getOutputResourceInfo().getDataFormats().add(dataFormatInfo);
   
          
    
        
	}

}
