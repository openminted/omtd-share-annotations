package eu.openminted.share.annotations.util;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createGivenName;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createName;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createSurname;

import org.junit.Test;

import eu.openminted.registry.domain.GivenNames;
import eu.openminted.registry.domain.Names;
import eu.openminted.registry.domain.PersonInfo;
import eu.openminted.registry.domain.Surnames;

public class PersonInfoTest
{
    @Test
    public void buildPersonInfo()
    {
        Names names1 = new Names();
        names1.getName().add(createName("Sumner, Gordon"));

        Names names2 = new Names();
        names2.getName().add(createName("Sting"));

        Surnames surnames1 = new Surnames();
        surnames1.getSurname().add(createSurname("Sumner"));

        GivenNames givenNames1 = new GivenNames();
        givenNames1.getGivenName().add(createGivenName("Gordon"));
        
        GivenNames givenNames2 = new GivenNames();
        givenNames2.getGivenName().add(createGivenName("Sting"));

        PersonInfo contactPerson = new PersonInfo();
        contactPerson.getNames().add(names1);
        contactPerson.getNames().add(names2);
        contactPerson.getSurnames().add(surnames1);
        contactPerson.getGivenNames().add(givenNames1);
        contactPerson.getGivenNames().add(givenNames2);
    }
}
