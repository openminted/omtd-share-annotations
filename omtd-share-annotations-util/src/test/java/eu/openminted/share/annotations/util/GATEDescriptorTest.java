package eu.openminted.share.annotations.util;

import java.io.IOException;
import java.util.List;

import org.jdom.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import eu.openminted.share.annotations.util.scanner.DescriptorSet;
import eu.openminted.share.annotations.util.scanner.GateComponentScanner;

public class GATEDescriptorTest {

	@Test
	public void testGATEDescriptior() throws IOException {
		GateComponentScanner scanner = new GateComponentScanner();
		
		List<DescriptorSet<Element>> ds = scanner.scan(this.getClass().getClassLoader());
		
		assertEquals("Unexpected number of GATE resources found",17,ds.size());
	}
}
