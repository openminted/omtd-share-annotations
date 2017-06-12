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
