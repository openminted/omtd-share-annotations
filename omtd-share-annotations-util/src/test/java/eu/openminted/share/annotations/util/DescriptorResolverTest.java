package eu.openminted.share.annotations.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.Ignore;
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
    	
    	URL[] actual = DescriptorResolver.scanDescriptors(new URL(jarFile.toExternalForm()));
    	assertEquals(2, actual.length);
        assertTrue(actual[0].toString(), actual[0].toString().endsWith("ExampleJAR.jar!/descriptors/Component1.xml"));
        assertTrue(actual[1].toString(), actual[1].toString().endsWith("ExampleJAR.jar!/descriptors/Component2.xml"));
    }
    
    @Test
    public void testScanThenGenerateMaven() throws Exception {
    	URL[] actual = DescriptorResolver.scanDescriptors("de.tudarmstadt.ukp.dkpro.core", "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-gpl", "1.8.0");
    	assertEquals(0, actual.length);
    	
    	String[] descriptors = DescriptorResolver.generateDescriptors("de.tudarmstadt.ukp.dkpro.core", "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-gpl", "1.8.0");
    	assertEquals(8, descriptors.length);
    }
    
    @Ignore("needs a published jar with OMTD descriptors to work")
    @Test
    public void testScanMaven() throws Exception {
    	URL[] actual = DescriptorResolver.scanDescriptors("uk.ac.gate.plugins","annie","8.5-SNAPSHOT");
    	assertEquals(17, actual.length);
    	
    	actual = DescriptorResolver.scanDescriptors("de.tudarmstadt.ukp.dkpro.core", "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-gpl", "1.8.0");
    	assertEquals(0, actual.length);
    }
    
    @Test
    public void testGenerateMaven() throws Exception {
    	String[] descriptors = DescriptorResolver.generateDescriptors("uk.ac.gate.plugins", "annie", "8.5-alpha1");
    	assertEquals(17, descriptors.length);
    	
    	descriptors = DescriptorResolver.generateDescriptors("de.tudarmstadt.ukp.dkpro.core", "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-gpl", "1.8.0");
    	assertEquals(8, descriptors.length);
    }
}
