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

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.resource.ResourceCreationSpecifier;

import eu.openminted.registry.domain.Component;

public class UimaClassAnalyzer
    implements Analyzer<Class<?>>
{
    @Override
    public void analyze(Component aDescriptor, Class<?> aClass)
        throws AnalyzerException
    {
        try {
            ResourceCreationSpecifier spec;
            
            // Use uimaFIT to generate descriptor from Java class
            if (AnalysisComponent.class.isAssignableFrom(aClass)) {
                spec = createEngineDescription((Class) aClass);
            }
            else if (CollectionReader.class.isAssignableFrom(aClass)) {
                spec = createReaderDescription((Class) aClass);
            }
            else {
                throw new IllegalArgumentException("Unable to handle " + aClass);
            }
            
            // Pass on descriptor to delegate analyzer
            UimaDescriptorAnalyzer analyzer = new UimaDescriptorAnalyzer();
            analyzer.analyze(aDescriptor, spec);
        }
        catch (Exception e) {
            throw new AnalyzerException(e);
        }
    }
}
