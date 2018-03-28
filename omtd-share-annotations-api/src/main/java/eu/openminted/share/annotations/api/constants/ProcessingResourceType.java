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

public class ProcessingResourceType
{
    /**
     * For components that take as input or produce as output a set of documents (corpus)
     */
    public static final String CORPUS = "corpus";

    /**
     * For components that take as input or produce as output a single document
     */
    public static final String DOCUMENT = "document";

    /**
     * For components that take as input a text typed in by the user
     */
    public static final String USER_INPUT_TEXT = "userInputText";

    /**
     * For components that take as input a lexical or conceptual resource (e.g. lexicon, term list,
     * ontology etc.) in order to perform operations on it or produce it as an output (e.g. a
     * converter between two lexica or an extractor of lemmas from documents)
     */
    public static final String LEXICAL_CONCEPTUAL_RESOURCE = "lexicalConceptualResource";

    /**
     * For components that take as input (in order to perform operations on it) or produce as output
     * a language description (e.g. a grammar or a machine learning model); examples include
     * converters between two grammars, a ML model trainer, etc.
     */
    public static final String LANGUAGE_DESCRIPTION = "languageDescription";
}
