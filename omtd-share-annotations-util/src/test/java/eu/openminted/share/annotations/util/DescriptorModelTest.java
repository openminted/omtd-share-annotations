package eu.openminted.share.annotations.util;

import org.junit.Test;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ComponentTypeEnum;

public class DescriptorModelTest
{
    @Test
    public void createDescriptor()
        throws Exception
    {
        ComponentInfo componentInfo = new ComponentInfo();
        componentInfo.setComponentType(ComponentTypeEnum.CHUNKER);
        
        Component component = new Component();
        component.setComponentInfo(componentInfo);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(component);
        System.out.println(xml);
    }
}
