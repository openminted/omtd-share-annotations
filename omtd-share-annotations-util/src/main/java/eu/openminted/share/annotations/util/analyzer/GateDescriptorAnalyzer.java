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

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createDescription;
import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createResourceName;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentCreationInfo;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.FrameworkEnum;
import eu.openminted.registry.domain.FunctionInfo;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.OperatingSystemEnum;
import eu.openminted.registry.domain.OperationType;
import eu.openminted.registry.domain.ParameterInfo;
import eu.openminted.registry.domain.ParameterTypeEnum;
import eu.openminted.registry.domain.ProcessingResourceInfo;
import eu.openminted.registry.domain.ProcessingResourceTypeEnum;
import eu.openminted.registry.domain.ResourceTypeEnum;

public class GateDescriptorAnalyzer
    implements Analyzer<Element>
{
    @Override
    public void analyze(Component aDescriptor, Element aResourceElement)
        throws AnalyzerException
    {
        ComponentInfo componentInfo = aDescriptor.getComponentInfo();
        if (componentInfo == null) {
            componentInfo = new ComponentInfo();
            aDescriptor.setComponentInfo(componentInfo);
        }
        
        componentInfo.setResourceType(ResourceTypeEnum.COMPONENT);
        
        if (componentInfo.getFunctionInfo() == null) {
        	FunctionInfo functionInfo = new FunctionInfo();
        	functionInfo.setFunction(OperationType.HTTP___W3ID_ORG_META_SHARE_OMTD_SHARE_PROCESSOR);
        	componentInfo.setFunctionInfo(functionInfo);
        }        	
        
        ComponentCreationInfo componentCreationInfo = componentInfo.getComponentCreationInfo();
        if (componentCreationInfo == null) {
            componentCreationInfo = new ComponentCreationInfo();
            componentInfo.setComponentCreationInfo(componentCreationInfo);
        }
        
        componentCreationInfo.setFramework(FrameworkEnum.GATE);
        componentCreationInfo.setImplementationLanguage("Java");

        ComponentDistributionInfo distributionInfo = new ComponentDistributionInfo();
        //distributionInfo.setCommand(aResourceElement.getChildText("CLASS"));
        distributionInfo.setOperatingSystems(asList(OperatingSystemEnum.OS_INDEPENDENT));
        componentInfo.getDistributionInfos().add(distributionInfo);
        
        String name = aResourceElement.getChildText("NAME");
        if (isNotBlank(name)) {
            IdentificationInfo identificationInfo = componentInfo.getIdentificationInfo();
            if (identificationInfo == null) {
                identificationInfo = new IdentificationInfo();
                componentInfo.setIdentificationInfo(identificationInfo);
            }
            
            identificationInfo.getResourceNames().add(createResourceName(name));
        }

        String description = aResourceElement.getChildText("COMMENT");
        if (isNotBlank(description)) {
            componentInfo.getIdentificationInfo().getDescriptions()
                    .add(createDescription(description));
        }
        else {
            componentInfo.getIdentificationInfo().getDescriptions()
                    .add(createDescription("no description"));
        }
        
        ProcessingResourceInfo processingResourceInfo = componentInfo.getInputContentResourceInfo();
        if (processingResourceInfo == null) {
            processingResourceInfo = new ProcessingResourceInfo();
            componentInfo.setInputContentResourceInfo(processingResourceInfo);
        }
        
        processingResourceInfo.setProcessingResourceType(ProcessingResourceTypeEnum.DOCUMENT);
        
        List<Element> parameter = (List<Element>) aResourceElement.getChildren("PARAMETER");
        if (!parameter.isEmpty()) {
            
            List<ParameterInfo> parameterInfos = new ArrayList<ParameterInfo>();
            
            for (Element param : parameter) {
            	
            	String paramName = param.getAttributeValue("NAME");
            	
				// document and corpus params are handled at execution time and
				// should never be set by the user
				if (paramName.equals("document") || paramName.equals("corpus"))
					continue;            	
            	
                ParameterInfo parameterInfo = new ParameterInfo();
                parameterInfo.setParameterName(paramName);                
                parameterInfo.setParameterLabel(paramName);
                if (isNotBlank(param.getAttributeValue("COMMENT"))) {
                	parameterInfo.setParameterDescription(param.getAttributeValue("COMMENT"));
                }
                else {
                	parameterInfo.setParameterDescription("no description");
                }
                parameterInfo.setOptional(Boolean.valueOf(param.getAttributeValue("OPTIONAL")));
    
                if (isNotBlank(param.getAttributeValue("DEFAULT"))) {
                    parameterInfo.getDefaultValue().add(param.getAttributeValue("DEFAULT"));
                }
    
                String type = param.getText();
                parameterInfo.setMultiValue("java.util.List".equals(type));
                if (parameterInfo.isMultiValue()) {
                    type = param.getAttributeValue("ITEM_CLASS_NAME");
                }
                
                //impossible to do this fully as any java class can be used as a param type
                switch (type) {
                case "java.lang.Float": // fallthrough
                case "java.lang.Double":
                    parameterInfo.setParameterType(ParameterTypeEnum.FLOAT);
                    break;
                case "java.lang.Long": // fallthrough
                case "java.lang.Integer":
                	parameterInfo.setParameterType(ParameterTypeEnum.INTEGER);
                	break;
                case "java.lang.Boolean":
                    parameterInfo.setParameterType(ParameterTypeEnum.BOOLEAN);
                    break;
                case "java.lang.String":
                    parameterInfo.setParameterType(ParameterTypeEnum.STRING);
                    break;
                default:
                	parameterInfo.setParameterType(ParameterTypeEnum.OTHER);
                }
    
                parameterInfos.add(parameterInfo);
                
            }
            
            componentInfo.setParameterInfos(parameterInfos);
        }
    }
}
