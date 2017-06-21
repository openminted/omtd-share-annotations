package eu.openminted.share.annotations.util;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentCreationInfo;
import eu.openminted.registry.domain.ComponentDependencies;
import eu.openminted.registry.domain.ComponentEvaluationInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.registry.domain.Description;
import eu.openminted.registry.domain.GivenName;
import eu.openminted.registry.domain.GroupName;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.IssueManagementInfo;
import eu.openminted.registry.domain.Name;
import eu.openminted.registry.domain.PersonInfo;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.registry.domain.ResourceCreationInfo;
import eu.openminted.registry.domain.ResourceDocumentationInfo;
import eu.openminted.registry.domain.ResourceName;
import eu.openminted.registry.domain.ResourceShortName;
import eu.openminted.registry.domain.ScmInfo;
import eu.openminted.registry.domain.Surname;
import eu.openminted.registry.domain.UsageInfo;
import eu.openminted.registry.domain.VersionInfo;

public class ComponentDescriptorFactory
{
    public static Component createComponent()
    {
        Component component = new Component();
        component.setComponentInfo(createComponentInfo());
        return component;
    }
    
    public static ComponentInfo createComponentInfo()
    {
        ComponentInfo componentInfo = new ComponentInfo();
        
        // Instantiate all the complex members of componentInfo to avoid NPEs
        componentInfo.setComponentCreationInfo(new ComponentCreationInfo());
        componentInfo.setComponentDependencies(new ComponentDependencies());
        componentInfo.setComponentEvaluationInfo(new ComponentEvaluationInfo());
        componentInfo.setContactInfo(new ContactInfo());
        componentInfo.setIdentificationInfo(new IdentificationInfo());
        componentInfo.setInputContentResourceInfo(new ProcessingResourceInfo());
        componentInfo.setIssueManagementInfo(new IssueManagementInfo());
        componentInfo.setOutputResourceInfo(new ProcessingResourceInfo());
        componentInfo.setResourceCreationInfo(new ResourceCreationInfo());
        componentInfo.setResourceDocumentationInfo(new ResourceDocumentationInfo());
        componentInfo.setScmInfo(new ScmInfo());
        componentInfo.setUsageInfo(new UsageInfo());
        componentInfo.setVersionInfo(new VersionInfo());
        
        return componentInfo;
    }
    
    
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
