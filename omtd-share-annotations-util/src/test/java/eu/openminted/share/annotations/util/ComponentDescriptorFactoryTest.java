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

import org.junit.Test;
import eu.openminted.registry.domain.Component;
import eu.openminted.share.annotations.component.SampleUimaComponent;
import eu.openminted.share.annotations.util.analyzer.UimaClassAnalyzer;

public class ComponentDescriptorFactoryTest
{
    @Test
    public void testCreateComponent()
        throws Exception
    {
        Component component = new Component();

        UimaClassAnalyzer uimaAnalyzer = new UimaClassAnalyzer();
        uimaAnalyzer.analyze(component, SampleUimaComponent.class);

        XmlUtil.write(component, System.out);
    }

    // @Test
    // public void testCombine()
    // throws Exception
    // {
    // XmlMapper xmlMapper = new XmlMapper();
    // xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    //
    // ComponentInfo componentInfo1 = createComponent(SampleUimaComponent.class);
    //
    // ComponentInfo componentInfo2 = new ComponentInfo();
    // componentInfo2.setApplicationFunction(ApplicationType.DISCOURSE_ANALYSIS);
    //
    // String xml1 = xmlMapper.writeValueAsString(componentInfo1);
    // String xml2 = xmlMapper.writeValueAsString(componentInfo2);
    //
    //
    // // create combiner
    // XmlCombiner combiner = new XmlCombiner();
    //
    // // combine files
    // combiner.combine(new ByteArrayInputStream(xml1.getBytes()));
    // combiner.combine(new ByteArrayInputStream(xml2.getBytes()));
    //
    // // store the result
    // combiner.buildDocument(System.out);
    // }
}
