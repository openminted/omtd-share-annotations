package eu.openminted.share.annotations.util.scanner;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createComponent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import eu.openminted.registry.domain.Component;
import eu.openminted.share.annotations.util.analyzer.AnalyzerException;
import eu.openminted.share.annotations.util.analyzer.GATEDescriptorAnalyzer;
import eu.openminted.share.annotations.util.internal.ScannerUtil;

public class GateComponentScanner {

	private final Log log = LogFactory.getLog(getClass());

	public List<DescriptorSet<Element>> scan(ClassLoader aClassloader) throws IOException {
		// Look for manifests containing pointers
		String[] manifestLocations = ScannerUtil.resolve("classpath*:META-INF/gate/creole.xml");

		return scan(manifestLocations);
	}

	public List<DescriptorSet<Element>> scan(String... aPatterns) throws IOException {
		// Resolve the actual component descriptor locations
		String[] componentDescriptorLocations = ScannerUtil.resolve(aPatterns);

		// we may want to resolve the Maven project first from the pom.xml on
		// the classpath to get version info etc. not stored in creole.xml

		List<DescriptorSet<Element>> descriptorSets = new ArrayList<DescriptorSet<Element>>();

		SAXBuilder builder = new SAXBuilder(false);

		for (String componentDescriptorLocation : componentDescriptorLocations) {

			try {
				Document creoleXML = builder.build(new URL(componentDescriptorLocation));

				// need to loop through the resources to find PRs as OMTD
				// assumes individual components at this point
				XPath resourceXPath = XPath
						.newInstance("//*[translate(local-name(),'resource', 'RESOURCE') = 'RESOURCE']");

				for (Element resourceElement : (List<Element>) resourceXPath.selectNodes(creoleXML)) {

					Component component = createComponent();

					GATEDescriptorAnalyzer analyzer = new GATEDescriptorAnalyzer();
					analyzer.analyze(component, resourceElement);

					DescriptorSet<Element> ds = new DescriptorSet<Element>();
					ds.setImplementationName("GATE CREOLE XML");
					ds.setNativeDescriptor(resourceElement);
					ds.setNativeDescriptorLocation(componentDescriptorLocation);
					ds.setOmtdShareDescriptor(component);

					descriptorSets.add(ds);
				}

			} catch (JDOMException | AnalyzerException e) {
				log.info("Unable to extract CREOLE info", e);
			}
		}

		return descriptorSets;
	}
}
