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
import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceCreationSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLParser;

import eu.openminted.registry.domain.Component;
import eu.openminted.share.annotations.util.analyzer.UimaDescriptorAnalyzer;
import eu.openminted.share.annotations.util.internal.ScannerUtil;

public class UimaComponentScanner
    implements ComponentScanner<ResourceCreationSpecifier>
{
    private final Log log = LogFactory.getLog(getClass());

    private List<DescriptorSet<ResourceCreationSpecifier>> descriptorSets = new ArrayList<>();

    public void scan(ClassLoader aClassloader)
        throws IOException
    {
        // Look for manifests containing pointers
        String[] manifestLocations = ScannerUtil
                .resolve("classpath*:META-INF/org.apache.uima.fit/component.txt");

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

        for (String componentDescriptorLocation : componentDescriptorLocations) {
            try {
                XMLInputSource xmlInput = new XMLInputSource(componentDescriptorLocation);
                XMLParser parser = UIMAFramework.getXMLParser();
                ResourceCreationSpecifier specifier = (ResourceCreationSpecifier) parser
                        .parseResourceSpecifier(xmlInput);

                Component component = new Component();

                UimaDescriptorAnalyzer analyzer = new UimaDescriptorAnalyzer();
                analyzer.analyze(component, specifier);

                DescriptorSet<ResourceCreationSpecifier> ds = new DescriptorSet<>();
                ds.setImplementationName(specifier.getImplementationName());
                ds.setNativeDescriptor(specifier);
                ds.setNativeDescriptorLocation(componentDescriptorLocation);
                ds.setOmtdShareDescriptor(component);
                
                component.getComponentInfo().getDistributionInfos().get(0).setCommand(ds.getImplementationName());

                descriptorSets.add(ds);
            }
            catch (InvalidXMLException | ClassCastException e) {
                log.info("Not a supported UIMA descriptor: " + componentDescriptorLocation);
            }
        }
    }

    @Override
    public List<DescriptorSet<ResourceCreationSpecifier>> getComponents()
    {
        return unmodifiableList(descriptorSets);
    }
}
