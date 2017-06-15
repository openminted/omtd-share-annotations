package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createDescription;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;

import org.jdom.Element;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.FrameworkEnum;
import eu.openminted.registry.domain.ParameterInfo;
import eu.openminted.registry.domain.ParameterTypeEnum;
import eu.openminted.registry.domain.ResourceTypeEnum;

public class AlvisDescriptorAnalyzer implements Analyzer<Element> {

	@Override
	public void analyze(Component aDescriptor, Element aObject) throws AnalyzerException {
        ComponentInfo componentInfo = aDescriptor.getComponentInfo();
        
        componentInfo.setResourceType(ResourceTypeEnum.COMPONENT);
        componentInfo.getComponentCreationInfo().setFramework(FrameworkEnum.ALVIS_NLP);
        componentInfo.getComponentCreationInfo().setImplementationLanguage("Java");
             
        
        Element content = aObject.getChild("module-doc");
        
        // alvis module descriptions are html streams, the content must be checked 
        String description = content.getChildText("description");
        if (isNotBlank(description)) {
            componentInfo.getIdentificationInfo().getDescriptions()
                    .add(createDescription(description));
        }
        
        // params 
        for (Element param : (List<Element>) content.getChildren("param-doc")) {
            ParameterInfo parameterInfo = new ParameterInfo();
            parameterInfo.setParameterName(param.getAttributeValue("name"));
            parameterInfo.setParameterLabel(param.getAttributeValue("name"));
            // alvis param descriptions are html streams, the content must be checked 
            parameterInfo.setParameterDescription(param.getText());
            parameterInfo.setOptional(Boolean.valueOf(param.getAttributeValue("mandatory")));
            
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
            

            componentInfo.getInputContentResourceInfo().getParameterInfos().add(parameterInfo);
			
		}

        
	}

}
