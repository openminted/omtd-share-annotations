package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createDescription;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createResourceName;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.jdom.Element;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;

public class GateDescriptorAnalyzer
    implements Analyzer<Element>
{
    @Override
    public void analyze(Component descriptor, Element resourceElement)
        throws AnalyzerException
    {
        ComponentInfo componentInfo = descriptor.getComponentInfo();

        String name = resourceElement.getChildText("NAME");
        if (isNotBlank(name)) {
            componentInfo.getIdentificationInfo().getResourceNames().add(createResourceName(name));
        }

        String description = resourceElement.getChildText("COMMENT");
        if (isNotBlank(description)) {
            componentInfo.getIdentificationInfo().getDescriptions()
                    .add(createDescription(description));
        }
    }
}
