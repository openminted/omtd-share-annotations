package eu.openminted.share.annotations.util.analyzer;

import eu.openminted.registry.domain.Component;

public interface Analyzer<T>
{
    void analyze(Component aDescriptor, T aObject)
        throws AnalyzerException;
}
