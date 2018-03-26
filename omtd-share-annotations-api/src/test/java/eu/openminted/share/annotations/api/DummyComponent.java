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

@Component(value="reader")
@ResourceInput(
        type = "corpus",
        encoding = "UTF-8",
        keyword = "some keyword",
        annotationLevel = "lemmatization",
        language = @Language(languageId="en", languageTag="en", scriptId="Latn", regiontId="154", variantId="fonipa"),
        dataFormat = @DataFormat(dataFormat = "conll2000", mimeType = "text/tab-separated-values", fileExtension = ".conll"))
@ResourceOutput(
        type = "corpus",
        encoding = "UTF-8",
        keyword = "some keyword",
        annotationLevel = "lemmatization",
        language = @Language(languageId="en", languageTag="en", scriptId="Latn", regiontId="154", variantId="fonipa"),
        dataFormat = @DataFormat(dataFormat = "conll2000", mimeType = "text/tab-separated-values", fileExtension = ".conll"))
public class DummyComponent
{
    // Nothing
}
