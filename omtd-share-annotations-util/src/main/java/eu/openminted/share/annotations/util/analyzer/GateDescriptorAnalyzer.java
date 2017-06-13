package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createDescription;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createResourceName;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;

import org.jdom.Element;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ParameterInfo;
import eu.openminted.registry.domain.ParameterTypeEnum;

public class GateDescriptorAnalyzer
    implements Analyzer<Element>
{
    @Override
    public void analyze(Component aDescriptor, Element aResourceElement)
        throws AnalyzerException
    {
        ComponentInfo componentInfo = aDescriptor.getComponentInfo();

        String name = aResourceElement.getChildText("NAME");
        if (isNotBlank(name)) {
            componentInfo.getIdentificationInfo().getResourceNames().add(createResourceName(name));
        }

        String description = aResourceElement.getChildText("COMMENT");
        if (isNotBlank(description)) {
            componentInfo.getIdentificationInfo().getDescriptions()
                    .add(createDescription(description));
        }

        for (Element param : (List<Element>) aResourceElement.getChildren("PARAMETER")) {
            ParameterInfo parameterInfo = new ParameterInfo();
            parameterInfo.setParameterName(param.getAttributeValue("NAME"));
            parameterInfo.setParameterLabel(param.getAttributeValue("NAME"));
            parameterInfo.setParameterDescription(param.getAttributeValue("COMMENT"));
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
