package eu.openminted.share.annotations.util;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import eu.openminted.registry.domain.Description;
import eu.openminted.registry.domain.GroupName;
import eu.openminted.registry.domain.PersonInfo;
import eu.openminted.registry.domain.ResourceName;

public class ComponentDescriptorFactory
{
    public static PersonInfo createPersonInfo(String aName, String aMail)
    {
        PersonInfo personInfo = new PersonInfo();

        if (isNotBlank(aName)) {
        	//TODO should we try splitting the name into parts for given name and surname?
            personInfo.setSurname(aName);
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

    public static GroupName createGroupName(String aName)
    {
        GroupName name = new GroupName();
        name.setValue(aName);
        return name;
    }
}
