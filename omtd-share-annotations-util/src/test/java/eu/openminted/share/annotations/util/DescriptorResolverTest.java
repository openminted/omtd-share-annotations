package eu.openminted.share.annotations.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.Test;

public class DescriptorResolverTest
{
    @Test
    public void testScanDescriptors() throws Exception
    {
        URL[] actual = DescriptorResolver.scanDescriptors();
        
        assertEquals(2, actual.length);
        assertTrue(actual[0].toString(), actual[0].toString().endsWith("target/test-classes/descriptors/Component1.xml"));
        assertTrue(actual[1].toString(), actual[1].toString().endsWith("target/test-classes/descriptors/Component2.xml"));
    }
    
    @Test
    public void testScanJAR() throws Exception
    {
    	URL jarFile = this.getClass().getClassLoader().getResource("ExampleJAR.jar");
    	
    	URL[] actual = DescriptorResolver.scanDescriptors(jarFile);
    	assertEquals(2, actual.length);
        assertTrue(actual[0].toString(), actual[0].toString().endsWith("ExampleJAR.jar!/descriptors/Component1.xml"));
        assertTrue(actual[1].toString(), actual[1].toString().endsWith("ExampleJAR.jar!/descriptors/Component2.xml"));
    }
}
