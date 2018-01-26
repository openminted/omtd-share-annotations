package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.registry.domain.LicenceEnum.AGPL_1_0;
import static eu.openminted.registry.domain.LicenceEnum.APACHE_2_0;
import static eu.openminted.registry.domain.LicenceEnum.BSD_2_CLAUSE;
import static eu.openminted.registry.domain.LicenceEnum.BSD_3_CLAUSE;
import static eu.openminted.registry.domain.LicenceEnum.BSD_4_CLAUSE;
import static eu.openminted.registry.domain.LicenceEnum.CC0_1_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_3_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_4_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_NC_3_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_NC_4_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_NC_ND_3_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_NC_ND_4_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_NC_SA_3_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_NC_SA_4_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_ND_3_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_ND_4_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_SA_3_0;
import static eu.openminted.registry.domain.LicenceEnum.CC_BY_SA_4_0;
import static eu.openminted.registry.domain.LicenceEnum.F_DPPL_3_0;
import static eu.openminted.registry.domain.LicenceEnum.GFDL_1_3;
import static eu.openminted.registry.domain.LicenceEnum.GPL_3_0;
import static eu.openminted.registry.domain.LicenceEnum.LGPL_3_0;
import static eu.openminted.registry.domain.LicenceEnum.MIT;
import static eu.openminted.registry.domain.LicenceEnum.M_DPPL_3_0;
import static eu.openminted.registry.domain.LicenceEnum.NLM;
import static eu.openminted.registry.domain.LicenceEnum.ODB_L_1_0;
import static eu.openminted.registry.domain.LicenceEnum.ODC_BY_1_0;
import static eu.openminted.registry.domain.LicenceEnum.PDDL;
import static eu.openminted.registry.domain.LicenceEnum.PRINCETON_WORD_NET;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.apache.commons.lang3.StringUtils.stripEnd;

import java.util.HashMap;
import java.util.Map;

import eu.openminted.registry.domain.LicenceEnum;

public final class LicenseMappings
{
    private static Map<String, LicenceEnum> URL_TO_SPDX = new HashMap<>();
    
    static {
        URL_TO_SPDX.put("www.apache.org/licenses/LICENSE-2.0", APACHE_2_0);
        URL_TO_SPDX.put("www.apache.org/licenses/LICENSE-2.0.txt", APACHE_2_0);
        URL_TO_SPDX.put("www.gnu.org/licenses/gpl-3.0.en.html", GPL_3_0);
        URL_TO_SPDX.put("www.gnu.org/licenses/gpl-3.0-standalone.html", GPL_3_0);
        URL_TO_SPDX.put("www.gnu.org/licenses/gpl-3.0.txt", GPL_3_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by/4.0", CC_BY_4_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-nc/4.0", CC_BY_NC_4_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-nc-nd/4.0", CC_BY_NC_ND_4_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-nc-sa/4.0", CC_BY_NC_SA_4_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-nd/4.0", CC_BY_ND_4_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-sa/4.0", CC_BY_SA_4_0);
        URL_TO_SPDX.put("creativecommons.org/publicdomain/zero/1.0", CC0_1_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by/3.0", CC_BY_3_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-nc/3.0", CC_BY_NC_3_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-nc-nd/3.0", CC_BY_NC_ND_3_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-nc-sa/3.0", CC_BY_NC_SA_3_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-nd/3.0", CC_BY_ND_3_0);
        URL_TO_SPDX.put("creativecommons.org/licenses/by-sa/3.0", CC_BY_SA_3_0);
        URL_TO_SPDX.put("opendatacommons.org/licenses/pddl", PDDL);
        URL_TO_SPDX.put("opendatacommons.org/licenses/by/1-0", ODC_BY_1_0);
        URL_TO_SPDX.put("opendatacommons.org/licenses/odbl/1-0", ODB_L_1_0);
        URL_TO_SPDX.put("www.affero.org/oagpl.html", AGPL_1_0);
        URL_TO_SPDX.put("directory.fsf.org/wiki/License:BSD_4Clause", BSD_4_CLAUSE);
        URL_TO_SPDX.put("opensource.org/licenses/BSD-3-Clause", BSD_3_CLAUSE);
        URL_TO_SPDX.put("opensource.org/licenses/BSD-2-Clause", BSD_2_CLAUSE);
        URL_TO_SPDX.put("www.gnu.org/licenses/fdl-1.3.en.html", GFDL_1_3);
        URL_TO_SPDX.put("www.gnu.org/licenses/lgpl-3.0.en.html", LGPL_3_0);
        URL_TO_SPDX.put("www.gnu.org/licenses/lgpl-3.0.txt", LGPL_3_0);
        URL_TO_SPDX.put("opensource.org/licenses/MIT", MIT);
        URL_TO_SPDX.put("wordnet.princeton.edu/wordnet/license", PRINCETON_WORD_NET);
        URL_TO_SPDX.put("www.eclipse.org/legal/epl-v10.html", LicenceEnum.EPL_1_0);
        URL_TO_SPDX.put("www.nlm.nih.gov/databases/license/license.pdf", NLM);
        URL_TO_SPDX.put("www.hbz-nrw.de/produkte/open-access/lizenzen/dppl/fdppl/f-DPPL_v3_en_11-2008", F_DPPL_3_0);
        URL_TO_SPDX.put("www.hbz-nrw.de/produkte/open-access/lizenzen/dppl/mdppl/m-DPPL_v3_en_11-2008", M_DPPL_3_0);
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
        return URL_TO_SPDX.get(licenseUrl);
    }
}
