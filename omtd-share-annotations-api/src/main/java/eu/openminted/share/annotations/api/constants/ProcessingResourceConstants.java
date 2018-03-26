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
package eu.openminted.share.annotations.api.constants;

public class ProcessingResourceConstants
{
    /**
     * Contains values permitted for processingResourceType field in OMTD-SHARE 2.0.1
     * <a href="https://openminted.github.io/releases/omtd-share/2.0.1/xsd/OMTD-SHARE-Component.xsd">https://openminted.github.io/releases/omtd-share/2.0.1/xsd/OMTD-SHARE-Component.xsd</a>
     */
    public static class ResourceTypeConstants
    {
        /**
         * For components that take as input or produce as output a set of documents (corpus)
         */
        public static final String corpus = "corpus";

        /**
         * For components that take as input or produce as output a single document
         */
        public static final String document = "document";

        /**
         * For components that take as input a text typed in by the user
         */
        public static final String userInputText = "userInputText";

        /**
         * For components that take as input a lexical or conceptual resource (e.g. lexicon, term list, ontology etc.)
         * in order to perform operations on it (and not use it as an ancillary/reference resource, e.g. for annotating a document)
         */
        public static final String lexicalConceptualResource = "lexicalConceptualResource";

        /**
         * For components that take as input a language description (e.g. a grammar) in order to perform operations on
         * it (and not use it as an ancillary/reference resource, e.g. for parsing a document)
         */
        public static final String languageDescription = "languageDescription";
    }
}
