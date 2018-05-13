/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.fit.descriptor.LanguageCapability;
import org.apache.uima.fit.descriptor.MimeTypeCapability;
import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.Resource;

import eu.openminted.share.annotations.api.Component;
import eu.openminted.share.annotations.api.DataFormat;
import eu.openminted.share.annotations.api.Language;
import eu.openminted.share.annotations.api.Parameters;
import eu.openminted.share.annotations.api.ResourceInput;
import eu.openminted.share.annotations.api.ResourceOutput;
import eu.openminted.share.annotations.api.constants.AnnotationType;
import eu.openminted.share.annotations.api.constants.CharacterEncoding;
import eu.openminted.share.annotations.api.constants.DataFormatType;
import eu.openminted.share.annotations.api.constants.MimeType;
import eu.openminted.share.annotations.api.constants.ProcessingResourceType;
import eu.openminted.share.annotations.api.constants.OperationType;

@Component(OperationType.SEGMENTER)
@Parameters(exclude = TestAnnotator.PARAM_HIDDEN)
@ResourceInput(
        type = ProcessingResourceType.CORPUS,
        encoding = CharacterEncoding.UTF_8,
        keyword = "some keyword",
        annotationLevel = AnnotationType.LEMMA,
        language = @Language(languageId="en"),
        dataFormat = @DataFormat(dataFormat = DataFormatType.CONLL2000))
@ResourceOutput(
        type = ProcessingResourceType.CORPUS,
        encoding = CharacterEncoding.UTF_8,
        keyword = "some keyword",
        annotationLevel = AnnotationType.LEMMA,
        language = @Language(languageId="en"),
        dataFormat = @DataFormat(dataFormat = DataFormatType.CONLL2000))
@MimeTypeCapability(MimeType.TEXT_TAB_SEPARATED_VALUES)
@LanguageCapability("en")
@TypeCapability(
        inputs = { 
            "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token",
            "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence" }, 
        outputs = { 
            "de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS" })
public class TestAnnotator
    extends AbstractAnnotator
{
    /**
     * Parameter value 1.
     */
    public static final String PARAM_VALUE_1 = "value1";
    @ConfigurationParameter(name = PARAM_VALUE_1, mandatory = true)
    private String value1;

    /**
     * Hidden parameter.
     */
    public static final String PARAM_HIDDEN = "hidden";
    @ConfigurationParameter(name = PARAM_HIDDEN, mandatory = true, defaultValue = "val")
    private String hidden;

    /**
     * Documentation for resource
     */
    public static final String RES_KEY = "res";
    @ExternalResource(key = RES_KEY)
    private Resource res;

    @Override
    public void process(JCas aJCas) throws AnalysisEngineProcessException
    {
        // Nothing to do
    }
}
