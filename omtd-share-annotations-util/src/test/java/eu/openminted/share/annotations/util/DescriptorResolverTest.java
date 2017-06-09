package eu.openminted.share.annotations.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DescriptorResolverTest
{
    @Test
    public void testScanDescriptors() throws Exception
    {
        String[] actual = DescriptorResolver.scanDescriptors();
        
        assertEquals(2, actual.length);
        assertTrue(actual[0], actual[0].endsWith("target/test-classes/descriptors/Component1.xml"));
        assertTrue(actual[1], actual[1].endsWith("target/test-classes/descriptors/Component2.xml"));
    }
}
