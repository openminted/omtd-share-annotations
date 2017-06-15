package eu.openminted.share.annotations.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import eu.openminted.share.annotations.util.scanner.AlvisComponentScanner;

public class AlvisDescriptorTest {
    @Test
    public void testSingleAlvisDescriptior()
        throws IOException
    {
        AlvisComponentScanner scanner = new AlvisComponentScanner();

        scanner.scan("file:src/test/resources/META-INF/alvis/simpleProjector2.xml");

        assertEquals("Unexpected number of Alvis resources found", 1,
                scanner.getComponents().size());
    }
    
}
