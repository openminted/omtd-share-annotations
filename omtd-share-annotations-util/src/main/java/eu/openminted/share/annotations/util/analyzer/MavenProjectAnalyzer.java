package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createGroupName;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createName;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Contributor;
import org.apache.maven.model.Developer;
import org.apache.maven.model.IssueManagement;
import org.apache.maven.model.License;
import org.apache.maven.model.MailingList;
import org.apache.maven.model.Organization;
import org.apache.maven.model.Scm;
import org.apache.maven.project.MavenProject;

import eu.openminted.registry.domain.Affiliation;
import eu.openminted.registry.domain.CommunicationInfo;
import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.GroupInfo;
import eu.openminted.registry.domain.LicenceEnum;
import eu.openminted.registry.domain.LicenceInfo;
import eu.openminted.registry.domain.LicenceInfos;
import eu.openminted.registry.domain.MailingListInfo;
import eu.openminted.registry.domain.NonStandardLicenceName;
import eu.openminted.registry.domain.OrganizationInfo;
import eu.openminted.registry.domain.OrganizationName;
import eu.openminted.registry.domain.PersonInfo;
import eu.openminted.registry.domain.ResourceIdentifier;
import eu.openminted.registry.domain.ResourceIdentifierSchemeNameEnum;
import eu.openminted.registry.domain.RightsInfo;
import eu.openminted.registry.domain.ScmInfo;

public class MavenProjectAnalyzer
    implements Analyzer<MavenProject>
{
    @Override
    public void analyze(Component aDescriptor, MavenProject aProject)
        throws AnalyzerException
    {
        ComponentInfo componentInfo = aDescriptor.getComponentInfo();

        for (Contributor person : aProject.getContributors()) {
            PersonInfo personInfo = new PersonInfo();
            
            if (isNotBlank(person.getName())) {
                personInfo.getNames().add(createName(person.getName()));
            }

            if (isNotBlank(person.getEmail()) || isNotBlank(person.getUrl())) {
                personInfo.setCommunicationInfo(new CommunicationInfo());
                
                if (isNotBlank(person.getEmail())) {
                    personInfo.getCommunicationInfo().getEmails().add(person.getEmail());
                }

                if (isNotBlank(person.getUrl())) {
                    personInfo.getCommunicationInfo().getHomepages().add(person.getUrl());
                }
            }

            if (isNotBlank(person.getOrganization())) {
                OrganizationInfo organizationInfo = new OrganizationInfo();
                
                OrganizationName organizationName = new OrganizationName();
                organizationName.setValue(person.getOrganization());
                organizationInfo.getOrganizationNames().add(organizationName);
                
                if (isNotBlank(person.getOrganizationUrl())) {
                    CommunicationInfo communicationInfo = new CommunicationInfo();
                    communicationInfo.getHomepages().add(person.getOrganizationUrl());
                    organizationInfo.setCommunicationInfo(communicationInfo);
                }
                
                Affiliation affiliation = new Affiliation();
                affiliation.setAffiliatedOrganization(organizationInfo);
                
                if (person.getRoles() != null && !person.getRoles().isEmpty()) {
                    affiliation.setPosition(StringUtils.join(person.getRoles(), ", "));
                }
                
                personInfo.getAffiliations().add(affiliation);
            }
            
            // FIXME there is more that could be copied here
            // c.getProperties();
            // c.getTimezone();

            componentInfo.getContactInfo().getContactPersons().add(personInfo);
        }

        // aProject.getCiManagement();

        for (Developer person : aProject.getDevelopers()) {
            PersonInfo personInfo = new PersonInfo();
            
            if (isNotBlank(person.getName())) {
                personInfo.getNames().add(createName(person.getName()));
            }

            if (isNotBlank(person.getEmail()) || isNotBlank(person.getUrl())) {
                personInfo.setCommunicationInfo(new CommunicationInfo());
                
                if (isNotBlank(person.getEmail())) {
                    personInfo.getCommunicationInfo().getEmails().add(person.getEmail());
                }

                if (isNotBlank(person.getUrl())) {
                    personInfo.getCommunicationInfo().getHomepages().add(person.getUrl());
                }
            }

            if (isNotBlank(person.getOrganization())) {
                OrganizationInfo organizationInfo = new OrganizationInfo();
                
                OrganizationName organizationName = new OrganizationName();
                organizationName.setValue(person.getOrganization());
                organizationInfo.getOrganizationNames().add(organizationName);
                
                if (isNotBlank(person.getOrganizationUrl())) {
                    CommunicationInfo communicationInfo = new CommunicationInfo();
                    communicationInfo.getHomepages().add(person.getOrganizationUrl());
                    organizationInfo.setCommunicationInfo(communicationInfo);
                }
                
                Affiliation affiliation = new Affiliation();
                affiliation.setAffiliatedOrganization(organizationInfo);
                
                if (person.getRoles() != null && !person.getRoles().isEmpty()) {
                    affiliation.setPosition(StringUtils.join(person.getRoles(), ", "));
                }
                
                personInfo.getAffiliations().add(affiliation);
            }
            
            // FIXME there is more that could be copied here
            // c.getProperties();
            // c.getTimezone();

            componentInfo.getContactInfo().getContactPersons().add(personInfo);
        }

        IssueManagement im = aProject.getIssueManagement();
        if (im != null) {
            if (isNotBlank(im.getSystem())) {
                componentInfo.getIssueManagementInfo().setSystem(im.getSystem());
            }
            if (isNotBlank(im.getUrl())) {
                componentInfo.getIssueManagementInfo().setUrl(im.getUrl());
            }
        }

        for (License l : aProject.getLicenses()) {
            LicenceInfo licenseInfo = new LicenceInfo();
            
            try {
                // Check if the license name comes from the controlled vocabulary
                LicenceEnum.fromValue(l.getName());
            }
            catch (IllegalArgumentException e) {
                // If not, consider it non-standard
                if (isNotBlank(l.getName())) {
                    NonStandardLicenceName licenceName = new NonStandardLicenceName();
                    licenceName.setValue(l.getName());
                    licenseInfo.getNonStandardLicenceName().add(licenceName);
                }

                if (isNotBlank(l.getUrl())) {
                    licenseInfo.setNonStandardLicenceTermsURL(l.getUrl());
                }
            }
            
            LicenceInfos licenseInfos = new LicenceInfos();
            licenseInfos.getLicenceInfo().add(licenseInfo);
            
            // If there already is a distribution info, then update it instead of creating a new
            // one.
            ComponentDistributionInfo distributionInfo;
            if (!componentInfo.getDistributionInfos().isEmpty()) {
                distributionInfo = componentInfo.getDistributionInfos().get(0);
            }
            else {
                distributionInfo = new ComponentDistributionInfo();
                componentInfo.getDistributionInfos().add(distributionInfo);
            }
            distributionInfo.setRightsInfo(new RightsInfo());
            distributionInfo.getRightsInfo().getLicenceInfos().add(licenseInfos);
        }

        // Copy mailing list information
        for (MailingList ml : aProject.getMailingLists()) {
            MailingListInfo mailingListInfo = new MailingListInfo();
            mailingListInfo.setArchive(ml.getArchive());
            mailingListInfo.setMailingListName(ml.getName());
            mailingListInfo.setPost(ml.getPost());
            mailingListInfo.setSubscribe(ml.getSubscribe());
            mailingListInfo.setUnsubscribe(ml.getUnsubscribe());
            mailingListInfo.setOtherArchives(ml.getOtherArchives());

            componentInfo.getContactInfo().getMailingLists().add(mailingListInfo);
        }

        Organization organization = aProject.getOrganization();
        if (organization != null) {
            OrganizationInfo organizationInfo = new OrganizationInfo();
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.getGroupNames().add(createGroupName(organization.getName()));
            groupInfo.setAffiliatedOrganization(organizationInfo);
            componentInfo.getContactInfo().getContactGroups().add(groupInfo);
        }

        aProject.getProperties();

        Scm scm = aProject.getScm();
        if (scm != null) {
            ScmInfo scmInfo = componentInfo.getScmInfo();
            if (isNotBlank(scm.getConnection())) {
                scmInfo.setConnection(scm.getConnection());
            }
            if (isNotBlank(scm.getDeveloperConnection())) {
                scmInfo.setDeveloperConnection(scm.getDeveloperConnection());
            }
            if (isNotBlank(scm.getTag())) {
                scmInfo.setTag(scm.getTag());
            }
            if (isNotBlank(scm.getUrl())) {
                scmInfo.setUrl(scm.getUrl());
            }
        }

        componentInfo.getVersionInfo().setVersion(aProject.getVersion());
        
        ResourceIdentifier resourceIdentifier = new ResourceIdentifier();
        resourceIdentifier.setResourceIdentifierSchemeName(ResourceIdentifierSchemeNameEnum.MAVEN);
        resourceIdentifier.setValue(String.format("mvn:%s:%s:%s#%s", aProject.getGroupId(),
                aProject.getArtifactId(), aProject.getVersion(),
                aDescriptor.getComponentInfo().getDistributionInfos().get(0).getCommand()));
        componentInfo.getIdentificationInfo().getResourceIdentifiers().add(resourceIdentifier);
    }
}
