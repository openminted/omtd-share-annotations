package eu.openminted.share.annotations.util;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createComponent;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createGivenName;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createName;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createSurname;

import org.junit.Test;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ComponentTypeEnum;
import eu.openminted.registry.domain.PersonInfo;
import eu.openminted.registry.domain.SeparateNames;

public class PersonInfoTest
{
    @Test
    public void buildPersonInfo()
        throws Exception
    {
        Component component = createComponent();

        ComponentInfo componentInfo = component.getComponentInfo();
        componentInfo.setComponentType(ComponentTypeEnum.CHUNKER);

        PersonInfo personInfo = new PersonInfo();
        personInfo.setSeparateNames(new SeparateNames());
        personInfo.getSeparateNames().getGivenNames().add(createGivenName("Will"));
        personInfo.getSeparateNames().getSurnames().add(createSurname("Smith"));
        personInfo.getSeparateNames().getGivenNames().add(createGivenName("Willard"));
        personInfo.getSeparateNames().getSurnames().add(createSurname("Carroll Smith Jr."));
        personInfo.getNames().add(createName("Smith, Will"));
        personInfo.getNames().add(createName("Carroll Smith Jr., Willard"));

        componentInfo.getContactInfo().getContactPersons().add(personInfo);

        XmlUtil.write(component, System.out);
    }
}
