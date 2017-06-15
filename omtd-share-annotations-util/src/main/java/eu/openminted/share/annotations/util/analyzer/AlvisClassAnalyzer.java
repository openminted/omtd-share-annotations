package eu.openminted.share.annotations.util.analyzer;

import org.jdom.Document;
import org.jdom.Element;

import eu.openminted.registry.domain.Component;

public class AlvisClassAnalyzer implements Analyzer<Document> {

	@Override
	public void analyze(Component aDescriptor, Document aObject) throws AnalyzerException {


            // to be adapted... to generate descriptor from Java class // 
			Element element = aObject.getRootElement() ; 
            
            // Pass on descriptor to delegate analyzer
            AlvisDescriptorAnalyzer analyzer = new AlvisDescriptorAnalyzer();
            analyzer.analyze(aDescriptor, element);
	}

	

}
