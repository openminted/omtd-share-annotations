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
            parameterInfo.setMultiValue(false); // FIXME ?
            parameterInfo.setOptional(Boolean.valueOf(param.getAttributeValue("OPTIONAL")));
            parameterInfo.getDefaultValue().add(param.getAttributeValue("DEFAULT"));

            //impossible to do this fully as any java class can be used as a param type
            switch (param.getText()) {
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
