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
        urlToSpdx.put("https://creativecommons.org/licenses/by/4.0/", CC-BY-4.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-nc/4.0/", CC-BY-NC-4.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-nc-nd/4.0/", CC-BY-NC-ND-4.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-nc-sa/4.0/", CC-BY-NC-SA-4.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-nd/4.0/", CC-BY-ND-4.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-sa/4.0/", CC-BY-SA-4.0);
        urlToSpdx.put("https://creativecommons.org/publicdomain/zero/1.0/", CC0-1.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by/3.0/", CC-BY-3.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-nc/3.0/", CC-BY-NC-3.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-nc-nd/3.0/", CC-BY-NC-ND-3.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-nc-sa/3.0/", CC-BY-NC-SA-3.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-nd/3.0/", CC-BY-ND-3.0);
        urlToSpdx.put("https://creativecommons.org/licenses/by-sa/3.0/", CC-BY-SA-3.0);
        urlToSpdx.put("https://opendatacommons.org/licenses/pddl/", PDDL);
        urlToSpdx.put("https://opendatacommons.org/licenses/by/1-0/", ODC-BY-1.0);
        urlToSpdx.put("https://opendatacommons.org/licenses/odbl/1-0/", ODbL-1.0);
        urlToSpdx.put("http://www.affero.org/oagpl.html", AGPL-1.0);
        urlToSpdx.put("http://directory.fsf.org/wiki/License:BSD_4Clause", BSD-4-Clause);
        urlToSpdx.put("https://opensource.org/licenses/BSD-3-Clause", BSD-3-Clause);
        urlToSpdx.put("https://opensource.org/licenses/BSD-2-Clause", BSD-2-Clause);
        urlToSpdx.put("https://www.gnu.org/licenses/fdl-1.3.en.html", GFDL-1.3);
        urlToSpdx.put("https://www.gnu.org/licenses/lgpl-3.0.en.html", LGPL-3.0);
        urlToSpdx.put("https://opensource.org/licenses/MIT", MIT);
        urlToSpdx.put("https://wordnet.princeton.edu/wordnet/license/", Princeton-WordNet);
        urlToSpdx.put("https://www.eclipse.org/legal/epl-v10.html", EPL-1.0);
        urlToSpdx.put("https://www.nlm.nih.gov/databases/license/license.pdf", NLM);
        urlToSpdx.put("https://www.hbz-nrw.de/produkte/open-access/lizenzen/dppl/fdppl/f-DPPL_v3_en_11-2008", f-DPPL-3.0);
        urlToSpdx.put("https://www.hbz-nrw.de/produkte/open-access/lizenzen/dppl/mdppl/m-DPPL_v3_en_11-2008", M-DPPL-3.0);
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
