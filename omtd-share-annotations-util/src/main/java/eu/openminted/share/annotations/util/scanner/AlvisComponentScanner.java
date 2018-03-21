package eu.openminted.share.annotations.util.scanner;

import static java.util.Collections.unmodifiableList;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import eu.openminted.registry.domain.Component;
import eu.openminted.share.annotations.util.analyzer.AlvisDescriptorAnalyzer;
import eu.openminted.share.annotations.util.analyzer.AnalyzerException;
import eu.openminted.share.annotations.util.internal.ScannerUtil;

public class AlvisComponentScanner   implements ComponentScanner<Element> {
    private final Log log = LogFactory.getLog(getClass());

    private List<DescriptorSet<Element>> descriptorSets = new ArrayList<>();

    public void scan(ClassLoader aClassloader)
        throws IOException
    {
        // Look for manifests containing pointers
        String[] manifestLocations = ScannerUtil
                .resolve("classpath*:META-INF/alvis/component.txt");

        // Read pointers to actual component descriptors from manifests
        Set<String> patterns = new TreeSet<String>();
        for (String manifestLocation : manifestLocations) {
            try (InputStream is = new URL(manifestLocation).openStream()) {
                patterns.addAll(IOUtils.readLines(is, "UTF-8"));
            }
        }

        scan(patterns.toArray(new String[patterns.size()]));
    }
    
    
    @Override
    public void scan(String... aPatterns)
        throws IOException
    {
        // Resolve the actual component descriptor locations
        String[] componentDescriptorLocations = ScannerUtil.resolve(aPatterns);

        SAXBuilder builder = new SAXBuilder(false);
        for (String componentDescriptorLocation : componentDescriptorLocations) {
            try {
            	
            	Document document = builder.build(new URL(componentDescriptorLocation));
            	Element element = document.getRootElement();
            	
   
                Component component = new Component();

                AlvisDescriptorAnalyzer analyzer = new AlvisDescriptorAnalyzer();
                analyzer.analyze(component, element);

                DescriptorSet<Element> ds = new DescriptorSet<>();
                ds.setImplementationName(element.getAttributeValue("short-target"));
                ds.setNativeDescriptor(element);
                ds.setNativeDescriptorLocation(componentDescriptorLocation);
                ds.setOmtdShareDescriptor(component);
                
                component.getComponentInfo().getDistributionInfos().get(0).setCommand(ds.getImplementationName());

                descriptorSets.add(ds);
            }
            catch (JDOMException | AnalyzerException e) {
                log.info("Unable to extract Alvis module Doc", e);
            }
        }
    }

    @Override
    public List<DescriptorSet<Element>> getComponents()
    {
        return unmodifiableList(descriptorSets);
    }
}
