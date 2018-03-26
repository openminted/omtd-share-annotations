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
package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapping of outputResourceInfo from OMTD-SHARE schema.
 * Groups together information on the output of a component
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface ResourceOutput
{
    /**
     * The type of the resource that a component takes as input or produces as output
     * @see eu.openminted.share.annotations.api.constants.ProcessingResourceConstants.ResourceTypeConstants ResourceType permitted values
     */
    String type();
    String[] encoding() default {};
    Language[] language() default {};
    String[] annotationLevel() default {};
    String[] keyword() default {};
    DataFormat[] dataFormat() default {};
}
