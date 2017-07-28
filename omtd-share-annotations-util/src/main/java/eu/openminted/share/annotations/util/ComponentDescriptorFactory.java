package eu.openminted.share.annotations.util;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import eu.openminted.registry.domain.Description;
import eu.openminted.registry.domain.GivenName;
import eu.openminted.registry.domain.GroupName;
import eu.openminted.registry.domain.Name;
import eu.openminted.registry.domain.PersonInfo;
import eu.openminted.registry.domain.ResourceName;
import eu.openminted.registry.domain.ResourceShortName;
import eu.openminted.registry.domain.Surname;

public class ComponentDescriptorFactory
{
    public static PersonInfo createPersonInfo(String aName, String aMail)
    {
        PersonInfo personInfo = new PersonInfo();

        if (isNotBlank(aName)) {
            personInfo.getNames().add(createName(aName));
        }

        if (isNotBlank(aMail)) {
            personInfo.getCommunicationInfo().getEmails().add(aMail);
        }

        return personInfo;
    }

    public static Description createDescription(String aDesciption)
    {
        Description name = new Description();
        name.setValue(aDesciption);
        return name;
    }

    public static ResourceName createResourceName(String aName)
    {
        ResourceName name = new ResourceName();
        name.setValue(aName);
        return name;
    }

    public static ResourceShortName createResourceShortName(String aName)
    {
        ResourceShortName name = new ResourceShortName();
        name.setValue(aName);
        return name;
    }

    public static GivenName createGivenName(String aSurname)
    {
        GivenName name = new GivenName();
        name.setValue(aSurname);
        return name;
    }

    public static Surname createSurname(String aSurname)
    {
        Surname name = new Surname();
        name.setValue(aSurname);
        return name;
    }

    public static Name createName(String aName)
    {
        Name name = new Name();
        name.setValue(aName);
        return name;
    }

    public static GroupName createGroupName(String aName)
    {
        GroupName name = new GroupName();
        name.setValue(aName);
        return name;
    }
}
