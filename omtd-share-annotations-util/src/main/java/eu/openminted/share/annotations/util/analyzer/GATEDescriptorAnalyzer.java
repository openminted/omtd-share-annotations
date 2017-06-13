package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createDescription;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createResourceName;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;

import org.jdom.Element;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ParameterInfo;

public class GATEDescriptorAnalyzer implements Analyzer<Element> {

	@Override
	public void analyze(Component descriptor, Element resourceElement) throws AnalyzerException {

		ComponentInfo componentInfo = descriptor.getComponentInfo();

		String name = resourceElement.getChildText("NAME");

		if (isNotBlank(name))
			componentInfo.getIdentificationInfo().getResourceNames().add(createResourceName(name));

		String description = resourceElement.getChildText("COMMENT");

		if (isNotBlank(description))
			componentInfo.getIdentificationInfo().getDescriptions().add(createDescription(description));

		for (Element parameter : (List<Element>) resourceElement.getChildren("PARAMETER")) {
			ParameterInfo parameterInfo = new ParameterInfo();
			parameterInfo.setParameterName(parameter.getAttributeValue("NAME"));
			parameterInfo.setParameterLabel(parameter.getAttributeValue("NAME"));

			String comment = parameter.getAttributeValue("COMMENT");
			if (isNotBlank(comment))
				parameterInfo.setParameterDescription(comment);

			parameterInfo.setOptional("true".equals(parameter.getAttributeValue("OPTIONAL")));

			// Not sure the best way of mapping to this enum, especially as I
			// can't find the code for it! in our repos
			// parameterInfo.setParameterType(ParameterTypeEnum.BOOLEAN);

			String defaultValue = parameter.getAttributeValue("DEFAULT");
			if (isNotBlank(defaultValue))
				parameterInfo.getDefaultValue().add(defaultValue);

			componentInfo.getInputContentResourceInfo().getParameterInfos().add(parameterInfo);
		}
	}

}
