package eu.openminted.share.annotations.util;

import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import eu.openminted.share.annotations.util.scanner.GateComponentScanner;

public class GateDescriptorTest
{

    @Test
    public void testGATEDescriptior()
        throws IOException
    {
        GateComponentScanner scanner = new GateComponentScanner();

        scanner.scan(this.getClass().getClassLoader());

        assertEquals("Unexpected number of GATE resources found", 17,
                scanner.getComponents().size());
    }
}
