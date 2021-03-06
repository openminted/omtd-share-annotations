/*
 * Licensed to the OpenMinTeD Consortium under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The OpenMinTeD Consortium 
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createGroupName;
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

import eu.openminted.registry.domain.ActorInfo;
import eu.openminted.registry.domain.ActorTypeEnum;
import eu.openminted.registry.domain.Affiliation;
import eu.openminted.registry.domain.CommunicationInfo;
import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.registry.domain.ContactTypeEnum;
import eu.openminted.registry.domain.GroupInfo;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.IssueManagementInfo;
import eu.openminted.registry.domain.LicenceEnum;
import eu.openminted.registry.domain.LicenceInfo;
import eu.openminted.registry.domain.MailingListInfo;
import eu.openminted.registry.domain.OrganizationInfo;
import eu.openminted.registry.domain.OrganizationName;
import eu.openminted.registry.domain.PersonInfo;
import eu.openminted.registry.domain.ResourceCreationInfo;
import eu.openminted.registry.domain.ResourceIdentifier;
import eu.openminted.registry.domain.ResourceIdentifierSchemeNameEnum;
import eu.openminted.registry.domain.RightsInfo;
import eu.openminted.registry.domain.RightsStatementEnum;
import eu.openminted.registry.domain.ScmInfo;
import eu.openminted.registry.domain.VersionInfo;

public class MavenProjectAnalyzer
    implements Analyzer<MavenProject>
{
    @Override
    public void analyze(Component aDescriptor, MavenProject aProject)
        throws AnalyzerException
    {
        ComponentInfo componentInfo = aDescriptor.getComponentInfo();
        if (componentInfo == null) {
            componentInfo = new ComponentInfo();
            aDescriptor.setComponentInfo(componentInfo);
        }
        
        // Process landing page
        if (isNotBlank(aProject.getUrl())) {
            ContactInfo contactInfo = componentInfo.getContactInfo();
            if (contactInfo == null) {
                contactInfo = new ContactInfo();
                componentInfo.setContactInfo(contactInfo);
            }
            contactInfo.setContactPoint(aProject.getUrl());
            contactInfo.setContactType(ContactTypeEnum.LANDING_PAGE);
        }
        
        // Process potential contact persons
        for (Contributor person : aProject.getContributors()) {
            // The email is mandatory, so if there is no email for a person, then we also do
            // not add any other communication information.
            // Treat as an individual contact
            if (isNotBlank(person.getEmail())) {
                PersonInfo personInfo = new PersonInfo();
                
                if (isNotBlank(person.getName())) {
                    personInfo.setSurname(person.getName());
                }
                
                if (isNotBlank(person.getEmail())) {
                    personInfo.setCommunicationInfo(new CommunicationInfo());
                    
                    if (isNotBlank(person.getEmail())) {
                        personInfo.getCommunicationInfo().getEmails().add(person.getEmail());
                    }
        
                    // Can only assing the URL to the person if there is also a email because
                    // email is mandatory in CommunicationInfo
                    if (isNotBlank(person.getUrl())) {
                        personInfo.getCommunicationInfo().getHomepages().add(person.getUrl());
                    }
                }

                if (isNotBlank(person.getOrganization())) {
                    OrganizationInfo organizationInfo = new OrganizationInfo();
                    
                    OrganizationName organizationName = new OrganizationName();
                    organizationName.setValue(person.getOrganization());
                    organizationInfo.getOrganizationNames().add(organizationName);
                    
                    // Cannot set the organization URL because in CommunicationInfo the
                    // email is mandatory
                    // if (isNotBlank(person.getOrganizationUrl())) {
                    //    CommunicationInfo communicationInfo = new CommunicationInfo();
                    //    communicationInfo.getHomepages().add(person.getOrganizationUrl());
                    //    organizationInfo.setCommunicationInfo(communicationInfo);
                    // }
                    
                    Affiliation affiliation = new Affiliation();
                    affiliation.setAffiliatedOrganization(organizationInfo);
                    
                    if (person.getRoles() != null && !person.getRoles().isEmpty()) {
                        affiliation.setPosition(StringUtils.join(person.getRoles(), ", "));
                    }
                    
                    personInfo.setAffiliation(affiliation);
                }

                // FIXME there is more that could be copied here
                // c.getProperties();
                // c.getTimezone();

                ActorInfo actorInfo = new ActorInfo();
                actorInfo.setRelatedPerson(personInfo);
                actorInfo.setActorType(ActorTypeEnum.PERSON);
                
                ResourceCreationInfo creationInfo = componentInfo.getResourceCreationInfo();
                if (creationInfo == null) {
                    creationInfo = new ResourceCreationInfo();
                    componentInfo.setResourceCreationInfo(creationInfo);
                }
                creationInfo.getResourceCreators().add(actorInfo);
            }
        }

        // aProject.getCiManagement();

        // Process potential contact persons
        for (Developer person : aProject.getDevelopers()) {
            // The email is mandatory, so if there is no email for a person, then we also do
            // not add any other communication information.
            // Treat as an individual contact
            if (isNotBlank(person.getEmail())) {
                PersonInfo personInfo = new PersonInfo();
                
                if (isNotBlank(person.getName())) {
                    personInfo.setSurname(person.getName());
                }
                
                if (isNotBlank(person.getEmail())) {
                    personInfo.setCommunicationInfo(new CommunicationInfo());
                    
                    if (isNotBlank(person.getEmail())) {
                        personInfo.getCommunicationInfo().getEmails().add(person.getEmail());
                    }
        
                    // Can only assing the URL to the person if there is also a email because
                    // email is mandatory in CommunicationInfo
                    if (isNotBlank(person.getUrl())) {
                        personInfo.getCommunicationInfo().getHomepages().add(person.getUrl());
                    }
                }

                if (isNotBlank(person.getOrganization())) {
                    OrganizationInfo organizationInfo = new OrganizationInfo();
                    
                    OrganizationName organizationName = new OrganizationName();
                    organizationName.setValue(person.getOrganization());
                    organizationInfo.getOrganizationNames().add(organizationName);
                    
                    // Cannot set the organization URL because in CommunicationInfo the
                    // email is mandatory
                    // if (isNotBlank(person.getOrganizationUrl())) {
                    //    CommunicationInfo communicationInfo = new CommunicationInfo();
                    //    communicationInfo.getHomepages().add(person.getOrganizationUrl());
                    //    organizationInfo.setCommunicationInfo(communicationInfo);
                    // }
                    
                    Affiliation affiliation = new Affiliation();
                    affiliation.setAffiliatedOrganization(organizationInfo);
                    
                    if (person.getRoles() != null && !person.getRoles().isEmpty()) {
                        affiliation.setPosition(StringUtils.join(person.getRoles(), ", "));
                    }
                    
                    personInfo.setAffiliation(affiliation);
                }

                // FIXME there is more that could be copied here
                // c.getProperties();
                // c.getTimezone();

                ActorInfo actorInfo = new ActorInfo();
                actorInfo.setRelatedPerson(personInfo);
                actorInfo.setActorType(ActorTypeEnum.PERSON);
                
                ResourceCreationInfo creationInfo = componentInfo.getResourceCreationInfo();
                if (creationInfo == null) {
                    creationInfo = new ResourceCreationInfo();
                    componentInfo.setResourceCreationInfo(creationInfo);
                }
                creationInfo.getResourceCreators().add(actorInfo);
            }
        }

        IssueManagement im = aProject.getIssueManagement();
        if (im != null && (isNotBlank(im.getSystem()) || isNotBlank(im.getUrl()))) {
            IssueManagementInfo issueManagementInfo = componentInfo.getIssueManagementInfo();
            if (issueManagementInfo == null) {
                issueManagementInfo = new IssueManagementInfo();
                componentInfo.setIssueManagementInfo(issueManagementInfo);
            }
            
            if (isNotBlank(im.getSystem())) {
                issueManagementInfo.setSystem(im.getSystem());
            }
            if (isNotBlank(im.getUrl())) {
                issueManagementInfo.setUrl(im.getUrl());
            }
        }

        boolean openAccess = !aProject.getLicenses().isEmpty();
        
        for (License l : aProject.getLicenses()) {
            LicenceInfo licenseInfo = new LicenceInfo();
            
            LicenceEnum spdxId = null;
            
            // Check if the license name comes from the controlled vocabulary
            try {
                spdxId = LicenceEnum.fromValue(l.getName());
            }
            catch (IllegalArgumentException e) {
                // Ok, not in the controlled vocabulary
            }
            
            // See if we can use the URL
            if (isNotBlank(l.getUrl())) {
                spdxId = LicenseMappings.getSpdxIdFromUrl(l.getUrl());
            }

            if (spdxId != null) {
                licenseInfo.setLicence(spdxId);
            }
            else {
                if (isNotBlank(l.getName()) || isNotBlank(l.getUrl())) {
                    licenseInfo.setLicence(LicenceEnum.NON_STANDARD_LICENCE_TERMS);
                }
                
                // If not, consider it non-standard
                if (isNotBlank(l.getName())) {
                    licenseInfo.setNonStandardLicenceName(l.getName());
                }

                if (isNotBlank(l.getUrl())) {
                    licenseInfo.setNonStandardLicenceTermsURL(l.getUrl());
                }
            }
            
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
            
            if (componentInfo.getRightsInfo() == null) {
            	componentInfo.setRightsInfo(new RightsInfo());
            }
            componentInfo.getRightsInfo().getLicenceInfos().add(licenseInfo);
            
            openAccess = openAccess && (spdxId != null && LicenseMappings.isOpenAccess(spdxId));
        }
        
        if (componentInfo.getRightsInfo() != null) {
        	componentInfo.getRightsInfo().setRightsStatement(openAccess ? RightsStatementEnum.OPEN_ACCESS : RightsStatementEnum.RESTRICTED_ACCESS);
        }

        // Copy mailing list information
        for (MailingList ml : aProject.getMailingLists()) {
            MailingListInfo mailingListInfo = new MailingListInfo();
            mailingListInfo.setArchive(ml.getArchive());
            mailingListInfo.setMailingListName(ml.getName());
            mailingListInfo.setPost(ml.getPost());
            mailingListInfo.setSubscribe(ml.getSubscribe());
            mailingListInfo.setUnsubscribe(ml.getUnsubscribe());

            if (!ml.getOtherArchives().isEmpty()) {
                mailingListInfo.setOtherArchives(ml.getOtherArchives());
            }

            ContactInfo contactInfo = componentInfo.getContactInfo();
            if (contactInfo == null) {
                contactInfo = new ContactInfo();
                componentInfo.setContactInfo(contactInfo);
            }

            contactInfo.getMailingLists().add(mailingListInfo);
        }

        Organization organization = aProject.getOrganization();
        if (organization != null) {
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.getGroupNames().add(createGroupName(organization.getName()));

            ContactInfo contactInfo = componentInfo.getContactInfo();
            if (contactInfo == null) {
                contactInfo = new ContactInfo();
                componentInfo.setContactInfo(contactInfo);
            }
            
            if (contactInfo.getContactPoint() == null && organization.getUrl() != null) {
                contactInfo.setContactPoint(organization.getUrl());
                contactInfo.setContactType(ContactTypeEnum.LANDING_PAGE);
            }

            contactInfo.getContactGroups().add(groupInfo);
        }

        aProject.getProperties();

        Scm scm = aProject.getScm();
        if (scm != null
                && (isNotBlank(scm.getConnection()) || isNotBlank(scm.getDeveloperConnection())
                        || isNotBlank(scm.getTag()) || isNotBlank(scm.getUrl()))) {
            ScmInfo scmInfo = componentInfo.getScmInfo();
            if (scmInfo == null) {
                scmInfo = new ScmInfo();
                componentInfo.setScmInfo(scmInfo);
            }
            
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
        
        VersionInfo versionInfo = componentInfo.getVersionInfo();
        if (versionInfo == null) {
            versionInfo = new VersionInfo();
            componentInfo.setVersionInfo(versionInfo);
        }
        versionInfo.setVersion(aProject.getVersion());
        
        ResourceIdentifier resourceIdentifier = new ResourceIdentifier();
        resourceIdentifier.setResourceIdentifierSchemeName(ResourceIdentifierSchemeNameEnum.MAVEN);
        resourceIdentifier.setValue(String.format("mvn:%s:%s:%s#%s", aProject.getGroupId(),
                aProject.getArtifactId(), aProject.getVersion(),
                aDescriptor.getComponentInfo().getDistributionInfos().get(0).getCommand()));
        
        IdentificationInfo identificationInfo = componentInfo.getIdentificationInfo();
        if (identificationInfo == null) {
            identificationInfo = new IdentificationInfo();
            componentInfo.setIdentificationInfo(identificationInfo);
        }
        identificationInfo.getResourceIdentifiers().add(resourceIdentifier);
    }
}
