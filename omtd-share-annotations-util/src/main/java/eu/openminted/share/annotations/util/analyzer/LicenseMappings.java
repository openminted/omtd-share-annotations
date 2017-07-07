package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.registry.domain.LicenceEnum.APACHE_2_0;
import static eu.openminted.registry.domain.LicenceEnum.GPL_3_0;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.apache.commons.lang3.StringUtils.stripEnd;

import java.util.HashMap;
import java.util.Map;

import eu.openminted.registry.domain.LicenceEnum;

public final class LicenseMappings
{
    private static Map<String, LicenceEnum> urlToSpdx = new HashMap<>();
    
    {
        urlToSpdx.put("www.apache.org/licenses/LICENSE-2.0", APACHE_2_0);
        urlToSpdx.put("www.apache.org/licenses/LICENSE-2.0.txt", APACHE_2_0);
        urlToSpdx.put("www.gnu.org/licenses/gpl-3.0.en.html", GPL_3_0);
        urlToSpdx.put("www.gnu.org/licenses/gpl-3.0-standalone.html", GPL_3_0);
    }
    
    private LicenseMappings()
    {
        // No instances
    }

    public static LicenceEnum getSpdxIdFromUrl(String aLicenseUrl)
    {
        String licenseUrl = aLicenseUrl.trim();
        licenseUrl = removeStart(licenseUrl, "http://");
        licenseUrl = removeStart(licenseUrl, "https://");
        licenseUrl = stripEnd(licenseUrl, "/");
        return urlToSpdx.get(licenseUrl);
    }
}
