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
