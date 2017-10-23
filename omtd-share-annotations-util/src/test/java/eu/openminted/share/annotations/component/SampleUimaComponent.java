package eu.openminted.share.annotations.component;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.ResourceMetaData;
import org.apache.uima.jcas.JCas;

import eu.openminted.share.annotations.api.Component;

@Component("segmenter")
@ResourceMetaData(name = "Sample component", version = "1.0", copyright = "Copyright 2007", vendor = "ACME", description = "Dummy component")
public class SampleUimaComponent
    extends JCasAnnotator_ImplBase
{
    @ConfigurationParameter
    private boolean booleanParameter;

    @Override
    public void process(JCas aJCas)
        throws AnalysisEngineProcessException
    {
        // Nothing to do
    }
}
