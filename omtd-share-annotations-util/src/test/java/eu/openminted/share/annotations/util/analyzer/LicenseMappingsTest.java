package eu.openminted.share.annotations.util.analyzer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eu.openminted.registry.domain.LicenceEnum;

public class LicenseMappingsTest
{
    @Test
    public void test()
    {
        assertEquals(LicenceEnum.GPL_3_0, LicenseMappings
                .getSpdxIdFromUrl("http://www.gnu.org/licenses/gpl-3.0-standalone.html"));
    }
}
