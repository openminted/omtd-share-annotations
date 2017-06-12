package eu.openminted.share.annotations.util;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.*;

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
        Component component = createComponent();

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
