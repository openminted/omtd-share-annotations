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

import org.jdom.Document;
import org.jdom.Element;

import eu.openminted.registry.domain.Component;

public class AlvisClassAnalyzer
    implements Analyzer<Document>
{
    @Override
    public void analyze(Component aDescriptor, Document aObject) throws AnalyzerException
    {
        // to be adapted... to generate descriptor from Java class //
        Element element = aObject.getRootElement();

        // Pass on descriptor to delegate analyzer
        AlvisDescriptorAnalyzer analyzer = new AlvisDescriptorAnalyzer();
        analyzer.analyze(aDescriptor, element);
    }
}
