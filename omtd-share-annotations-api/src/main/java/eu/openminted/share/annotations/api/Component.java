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

import eu.openminted.share.annotations.api.constants.OperationType;

/**
 * Marks a class as an OpenMinTeD-compatible TDM component. A piece of software typically for a
 * specific technical purpose, such as a particular implementation of a part-of-speech tagger (e.g
 * TreeTagger), a tree parsing program (e.g. mstparser), etc.; it is wrapped in a standard way
 * within a particular component-oriented framework such as UIMA, GATE, etc. or as a specific type
 * of web service.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
public @interface Component
{
    /**
     * Component type from the OpenMinTeD-SHARE inventory that is associated with this component.
     * 
     * @see OperationType permitted values
     */
    String value() default "";

    /**
     * Indicates whether the component can be used as an integrated end-user application
     */
    boolean application() default false;

    // String applicationFunction();
}
