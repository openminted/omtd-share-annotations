package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createDescription;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createResourceName;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentCreationInfo;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.FrameworkEnum;
import eu.openminted.registry.domain.FunctionInfo;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.OperatingSystemEnum;
import eu.openminted.registry.domain.OperationType;
import eu.openminted.registry.domain.ParameterInfo;
import eu.openminted.registry.domain.ParameterTypeEnum;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.registry.domain.ProcessingResourceTypeEnum;
import eu.openminted.registry.domain.ResourceTypeEnum;

public class GateDescriptorAnalyzer
    implements Analyzer<Element>
{
    @Override
    public void analyze(Component aDescriptor, Element aResourceElement)
        throws AnalyzerException
    {
        ComponentInfo componentInfo = aDescriptor.getComponentInfo();
        if (componentInfo == null) {
            componentInfo = new ComponentInfo();
            aDescriptor.setComponentInfo(componentInfo);
        }
        
        componentInfo.setResourceType(ResourceTypeEnum.COMPONENT);
        
        if (componentInfo.getFunctionInfo() == null) {
        	FunctionInfo functionInfo = new FunctionInfo();
        	functionInfo.setFunction(OperationType.OTHER);
        	componentInfo.setFunctionInfo(functionInfo);
        }        	
        
        ComponentCreationInfo componentCreationInfo = componentInfo.getComponentCreationInfo();
        if (componentCreationInfo == null) {
            componentCreationInfo = new ComponentCreationInfo();
            componentInfo.setComponentCreationInfo(componentCreationInfo);
        }
        
        componentCreationInfo.setFramework(FrameworkEnum.GATE);
        componentCreationInfo.setImplementationLanguage("Java");

        ComponentDistributionInfo distributionInfo = new ComponentDistributionInfo();
        distributionInfo.setCommand(aResourceElement.getChildText("CLASS"));
        distributionInfo.setOperatingSystems(asList(OperatingSystemEnum.OS_INDEPENDENT));
        componentInfo.getDistributionInfos().add(distributionInfo);
        
        String name = aResourceElement.getChildText("NAME");
        if (isNotBlank(name)) {
            IdentificationInfo identificationInfo = componentInfo.getIdentificationInfo();
            if (identificationInfo == null) {
                identificationInfo = new IdentificationInfo();
                componentInfo.setIdentificationInfo(identificationInfo);
            }
            
            identificationInfo.getResourceNames().add(createResourceName(name));
        }

        String description = aResourceElement.getChildText("COMMENT");
        if (isNotBlank(description)) {
            componentInfo.getIdentificationInfo().getDescriptions()
                    .add(createDescription(description));
        }
        else {
			componentInfo.getIdentificationInfo().getDescriptions().add(createDescription("no description"));
        }

        List<Element> parameter = (List<Element>) aResourceElement.getChildren("PARAMETER");
        if (!parameter.isEmpty()) {
            ProcessingResourceInfo processingResourceInfo = componentInfo.getInputContentResourceInfo();
            if (processingResourceInfo == null) {
                processingResourceInfo = new ProcessingResourceInfo();
                componentInfo.setInputContentResourceInfo(processingResourceInfo);
            }
            
            List<ParameterInfo> parameterInfos = new ArrayList<ParameterInfo>();
            
            for (Element param : parameter) {
                ParameterInfo parameterInfo = new ParameterInfo();
                parameterInfo.setParameterName(param.getAttributeValue("NAME"));
                parameterInfo.setParameterLabel(param.getAttributeValue("NAME"));
                if (isNotBlank(param.getAttributeValue("COMMENT"))) {
                	parameterInfo.setParameterDescription(param.getAttributeValue("COMMENT"));
                }
                else {
                	parameterInfo.setParameterDescription("no description");
                }
                parameterInfo.setOptional(Boolean.valueOf(param.getAttributeValue("OPTIONAL")));
    
                if (isNotBlank(param.getAttributeValue("DEFAULT"))) {
                    parameterInfo.getDefaultValue().add(param.getAttributeValue("DEFAULT"));
                }
    
                String type = param.getText();
                parameterInfo.setMultiValue("java.util.List".equals(type));
                if (parameterInfo.isMultiValue()) {
                    type = param.getAttributeValue("ITEM_CLASS_NAME");
                }
                
                //impossible to do this fully as any java class can be used as a param type
                switch (type) {
                case "java.lang.Float": // fallthrough
                case "java.lang.Double":
                    parameterInfo.setParameterType(ParameterTypeEnum.FLOAT);
                    break;
                case "java.lang.Long": // fallthrough
                case "java.lang.Integer":
                	parameterInfo.setParameterType(ParameterTypeEnum.INTEGER);
                	break;
                case "java.lang.Boolean":
                    parameterInfo.setParameterType(ParameterTypeEnum.BOOLEAN);
                    break;
                case "java.lang.String":
                    parameterInfo.setParameterType(ParameterTypeEnum.STRING);
                    break;
                case "gate.Document":
                	parameterInfo.setParameterType(ParameterTypeEnum.OTHER);
                	processingResourceInfo.setProcessingResourceType(ProcessingResourceTypeEnum.DOCUMENT);
                	break;
                default:
                	parameterInfo.setParameterType(ParameterTypeEnum.OTHER);
                }
    
                parameterInfos.add(parameterInfo);
                
            }
            
            componentInfo.setParameterInfos(parameterInfos);
        }
    }
}
