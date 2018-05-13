/*
 * Licensed to the OpenMinTeD Consortium under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The OpenMinTeD Consortium 
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.openminted.share.annotations.api.constants;

public class PublicationIdentifierScheme
{
    /**
     * Value for publicationIdentifierSchemeName, referring to the DOI system (Digital Object Identifier, https://www.doi.org/)
     */
    public static final String DOI = "DOI";

    /**
     * Value for publicationIdentifierSchemeName, referring to the Handle system for identifiers (https://www.handle.net/)
     */
    public static final String  HANDLE = "Handle";

    /**
     * Value for publicationIdentifierSchemeName, referring to the Archival Resource Key identifiers (https://confluence.ucop.edu/display/Curation/ARK)
     */
    public static final String ARK = "ARK";

    /**
     * Value for publicationIdentifierSchemeName, referring to the identifiers issued by arXiv (https://arxiv.org/)
     * 
     */
    public static final String AR_XIV = "arXiv";

    /**
     * Value for publicationIdentifierSchemeName, referring to bibcode identifiers, i.e. a type of identifier used for literature references by astronomical data systems (Bibliographic Code, http://adsabs.harvard.edu/abs_doc/help_pages/data.html)
     */
    public static final String BIBCODE = "bibcode";

    /**
     * Value for publicationIdentifierSchemeName, referring to EAN (International Article Number, also known as European Article Number) and more specifically to the EAN 13 standard (cf. https://en.wikipedia.org/wiki/International_Article_Number)
     */
    public static final String EAN_13 = "EAN13";

    /**
     * Value for publicationIdentifierSchemeName, referring to the e-ISSN (electronic International Standard Serial Number, https://en.wikipedia.org/wiki/International_Standard_Serial_Number)
     */
    public static final String EISSN = "EISSN";

    /**
     * Value for publicationIdentifierSchemeName, referring to the ISBN (International Standard Book Number, https://www.isbn-international.org/content/what-isbn)
     */
    public static final String ISBN = "ISBN";

    /**
     * Value for publicationIdentifierSchemeName, referring to the ISSN (International Standard Serial Number, http://www.issn.org/)
     */
    public static final String ISSN = "ISSN";

    /**
     * Value for publicationIdentifierSchemeName, referring to the ISTC (International Standard Text Code, http://www.istc-international.org/)
     */
    public static final String ISTC = "ISTC";

    /**
     * Value for publicationIdentifierSchemeName, referring to the linking ISSN or ISSN-L
     */
    public static final String LISSN = "LISSN";

    /**
     * Value for publicationIdentifierSchemeName, referring to the LSID (Life Sciences Identifier, http://www.webcitation.org/getfile?fileid=27fcd073ea70199946ace15c6868520be2cab2ab)
     */
    public static final String LSID = "LSID";

    /**
     * Value for publicationIdentifierSchemeName, referring to PURL (Persistent URL, https://archive.org/services/purl/help)
     */
    public static final String PURL = "PURL";

    /**
     * Value for publicationIdentifierSchemeName, referring to UPC (Universal Product Number, http://www.gtin.info/upc/)
     */
    public static final String UPC = "UPC";

    /**
     * Value for publicationIdentifierSchemeName, referring to the URL (Uniform Resource Locator, https://en.wikipedia.org/wiki/URL)
     */
    public static final String URL = "URL";

    /**
     * Value for publicationIdentifierSchemeName, referring to the URN (Uniform Resource Name, https://en.wikipedia.org/wiki/Uniform_Resource_Name)
     */
    public static final String URN = "URN";

    /**
     * Value for publicationIdentifierSchemeName, referring to the OAI identifier (Open Archives Initiative, http://www.openarchives.org/OAI/2.0/guidelines-oai-identifier.htm)
     */
    public static final String OAI = "OAI";

    /**
     * Value for publicationIdentifierSchemeName, referring to the PMCID (PubMed Central Identifier, https://www.ncbi.nlm.nih.gov/pmc/pmctopmid/)
     * 
     */
    public static final String PMCID = "PMCID";

    /**
     * Value for publicationIdentifierSchemeName, referring to the PMID (PubMed Identifier, https://www.ncbi.nlm.nih.gov/pmc/pmctopmid/)
     */
    public static final String PMID = "PMID";

    /**
     * Value for publicationIdentifierSchemeName, referring to the OpenAIRE identifier (www.openaire.eu)
     */
    public static final String OPEN_AIRE = "OpenAIRE";

    /**
     * Value for publicationIdentifierSchemeName, referring to the CORE identifier (core.ac.uk)
     */
    public static final String CORE = "CORE";

    /**
     * Value for resourceSchemeName, to be used when the desired value is not included in the listed values
     */
    public static final String OTHER = "other";

    private PublicationIdentifierScheme()
    {
        // No instances
    }
}
