package eu.openminted.share.annotations.util;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.*;

import org.junit.Test;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ComponentTypeEnum;
import eu.openminted.registry.domain.PersonInfo;

public class DescriptorModelTest
{
    @Test
    public void createDescriptor()
        throws Exception
    {
        
        Component component = createComponent();
        
        ComponentInfo componentInfo = component.getComponentInfo();
        componentInfo.setComponentType(ComponentTypeEnum.CHUNKER);
        
        PersonInfo personInfo = new PersonInfo();
        personInfo.getGivenNames().add(createGivenNames("Will"));
        personInfo.getSurnames().add(createSurnames("Smith"));
        personInfo.getGivenNames().add(createGivenNames("Willard"));
        personInfo.getSurnames().add(createSurnames("Carroll", "Smith", "Jr."));
        personInfo.getNames().add(createNames("Smith, Will", "Carroll Smith Jr., Willard"));
        
        componentInfo.getContactInfo().getContactPersons().add(personInfo);

        XmlUtil.write(component, System.out);
    }
}
