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

public final class DataFormatType
{
    /**
     * Solr format
     */
    public static final String SOLR = "http://w3id.org/meta-share/omtd-share/Solr";

    /**
     * Any format based on columns
     */
    public static final String TABULAR_FORMAT = "http://w3id.org/meta-share/omtd-share/TabularFormat";

    /**
     * Formats used in the CoNLL Shared Tasks
     */
    public static final String CONLL_FORMAT = "http://w3id.org/meta-share/omtd-share/ConllFormat";

    /**
     * The CoNLL 2009 format targets semantic role labeling. Columns are tab-separated. Sentences
     * are separated by a blank new line.
     */
    public static final String CONLL2009 = "http://w3id.org/meta-share/omtd-share/Conll2009";

    /**
     * The CoNLL 2006 (aka CoNLL-X) format targets dependency parsing. Columns are tab-separated.
     * Sentences are separated by a blank new line.
     */
    public static final String CONLL2006 = "http://w3id.org/meta-share/omtd-share/Conll2006";

    /**
     * The CoNLL 2008 format targets syntactic and semantic dependencies. Columns are tab-separated.
     * Sentences are separated by a blank new line.
     */
    public static final String CONLL2008 = "http://w3id.org/meta-share/omtd-share/Conll2008";

    /**
     * The CoNLL 2012 format targets semantic role labeling and coreference. Columns are
     * tab-separated. Sentences are separated by a blank new line.
     */
    public static final String CONLL2012 = "http://w3id.org/meta-share/omtd-share/Conll2012";

    /**
     * Format used for CoNLL.
     */
    public static final String CONLL_U = "http://w3id.org/meta-share/omtd-share/ConllU";

    /**
     * The CoNLL 2002 format encodes named entity spans. Fields are separated by a single space.
     * Sentences are separated by a blank new line.
     */
    public static final String CONLL2002 = "http://w3id.org/meta-share/omtd-share/Conll2002";

    /**
     * The CoNLL 2000 format represents POS and Chunk tags. Fields in a line are separated by
     * spaces. Sentences are separated by a blank new line.
     */
    public static final String CONLL2000 = "http://w3id.org/meta-share/omtd-share/Conll2000";

    /**
     * The CoNLL 2004 format encodes named entity spans and chunk spans. Fields are separated by a
     * single space. Sentences are separated by a blank new line. Named entities and chunks are
     * encoded in the IOB1 format. I.e. a B prefix is only used if the category of the following
     * span differs from the category of the current span.
     */
    public static final String CONLL2003 = "http://w3id.org/meta-share/omtd-share/Conll2003";

    /**
     * Data format with comma-separated values
     */
    public static final String CSV = "http://w3id.org/meta-share/omtd-share/Csv";

    /**
     * Data format for Microsoft Excel documents
     */
    public static final String MS_EXCEL = "http://w3id.org/meta-share/omtd-share/MsExcel";

    /**
     * Format for files with tab-separated values
     */
    public static final String TSV = "http://w3id.org/meta-share/omtd-share/Tsv";

    /**
     * A tab-separated format with limited markup (e.g. for sentences, documents, but not recursive
     * structures like parse-trees) used by the IMS Open Corpus Workbench.
     */
    public static final String IMSCWB = "http://w3id.org/meta-share/omtd-share/Imscwb";

    /**
     * Formats used for databases
     */
    public static final String DATABASE_FORMAT = "http://w3id.org/meta-share/omtd-share/DatabaseFormat";

    /**
     * For JDBC databases
     */
    public static final String JDBC = "http://w3id.org/meta-share/omtd-share/Jdbc";

    /**
     * Data format for Microsoft Access database
     */
    public static final String MS_ACCESS_DATABASE = "http://w3id.org/meta-share/omtd-share/MsAccessDatabase";

    /**
     * Any format used for documents (textual resources)
     */
    public static final String DOCUMENT_FORMAT = "http://w3id.org/meta-share/omtd-share/DocumentFormat";

    /**
     * Rich Text Format; proprietary data format of Microsoft
     */
    public static final String RTF = "http://w3id.org/meta-share/omtd-share/Rtf";

    /**
     * Data format for PostScript files
     */
    public static final String POSTSCRIPT = "http://w3id.org/meta-share/omtd-share/Postscript";

    /**
     * Formats used for BioNLP shared tasks
     */
    public static final String BIONLP_FORMATS = "http://w3id.org/meta-share/omtd-share/BionlpFormats";

    /**
     * Format used in BioNLP Shared Task 2013
     */
    public static final String BIONLP_ST2013A1_A2 = "http://w3id.org/meta-share/omtd-share/BionlpSt2013A1_a2";

    /**
     * File format used for the BioNLP Shared Task format
     */
    public static final String BIONLP = "http://w3id.org/meta-share/omtd-share/Bionlp";

    /**
     * JSON format of the Genia dataset
     */
    public static final String JSON_GENIA = "http://w3id.org/meta-share/omtd-share/Json_genia";

    /**
     * Data format for PDF files (Portable Document Format)
     */
    public static final String PDF = "http://w3id.org/meta-share/omtd-share/Pdf";

    /**
     * Data format according to the Pronunciation Lexicon Specification (PLS)
     */
    public static final String PLS = "http://w3id.org/meta-share/omtd-share/Pls";

    /**
     * Data format for XHTML (Extensible HyperText Markup Language)
     */
    public static final String XHTML = "http://w3id.org/meta-share/omtd-share/Xhtml";

    /**
     * HTML format
     */
    public static final String HTML = "http://w3id.org/meta-share/omtd-share/Html";

    /**
     * Format according to the specifications of HTML5 Microdata
     */
    public static final String HTML5MICRODATA = "http://w3id.org/meta-share/omtd-share/Html5Microdata";

    /**
     * Data format for documents using Tex (a typesetting system)
     */
    public static final String TEX = "http://w3id.org/meta-share/omtd-share/Tex";

    /**
     * Data format for Microsoft Word documents
     */
    public static final String MS_WORD = "http://w3id.org/meta-share/omtd-share/MsWord";

    /**
     * Format used in Cochrane texts
     */
    public static final String COCHRANE = "http://w3id.org/meta-share/omtd-share/Cochrane";

    /**
     * Data format for documents using LaTeX (a high-quality typesetting system very popular for
     * scientific documents)
     */
    public static final String LATEX = "http://w3id.org/meta-share/omtd-share/Latex";

    /**
     * Textual format used for PubMed articles
     */
    public static final String PUBMED = "http://w3id.org/meta-share/omtd-share/Pubmed";

    /**
     * SGML format
     */
    public static final String SGML = "http://w3id.org/meta-share/omtd-share/Sgml";

    /**
     * Default value for the format of textual files; a textual file should be human-readable and
     * must not contain binary data
     */
    public static final String TEXT = "http://w3id.org/meta-share/omtd-share/Text";

    /**
     * Data format for the XML Metadata Interchange (XMI), which is an Object Management Group (OMG)
     * standard for exchanging metadata information via Extensible Markup Language (XML)
     */
    public static final String XMI = "http://w3id.org/meta-share/omtd-share/Xmi";

    /**
     * Data format encoding Linked Data using JSON
     */
    public static final String JSON_LD = "http://w3id.org/meta-share/omtd-share/Json_ld";

    /**
     * Superclass for wiki formats
     */
    public static final String WIKI_FORMATS = "http://w3id.org/meta-share/omtd-share/WikiFormats";

    /**
     * Wiki markup for formatting
     */
    public static final String MEDIA_WIKI_MARKUP = "http://w3id.org/meta-share/omtd-share/MediaWikiMarkup";

    /**
     * Formats used for the UIMA CAS (Common Analysis System) objects
     */
    public static final String UIMA_CAS_FORMAT = "http://w3id.org/meta-share/omtd-share/UimaCasFormat";

    /**
     * Binary format used for CAS data
     */
    public static final String BINARY_CAS = "http://w3id.org/meta-share/omtd-share/BinaryCas";

    /**
     * UIMA serialisation in JSON
     */
    public static final String UIMA_JSON = "http://w3id.org/meta-share/omtd-share/Uima_json";

    /**
     * The CAS is the native data model used by UIMA; there are various ways of saving CAS data,
     * using XMI, XCAS, or binary formats; this is for the serialized format
     */
    public static final String SERIALIZED_CAS = "http://w3id.org/meta-share/omtd-share/SerializedCas";

    /**
     * Any format used for annotated textual documents
     */
    public static final String ANNOTATION_FORMAT = "http://w3id.org/meta-share/omtd-share/AnnotationFormat";

    /**
     * Format for TGrep2 (search engine for searching syntactic parse trees represented as bracketed
     * structures)
     */
    public static final String TGREP2 = "http://w3id.org/meta-share/omtd-share/Tgrep2";

    /**
     * CHAT (Codes for the Human Analysis of Transcripts) transcription format; used by CHILDES
     * corpora
     */
    public static final String CHAT = "http://w3id.org/meta-share/omtd-share/Chat";

    /**
     * Data format for documents and corpora using the XCES standard (Corpus Encoding Standard for
     * XML), cf. http://www.xces.org/
     */
    public static final String XCES = "http://w3id.org/meta-share/omtd-share/Xces";

    /**
     * A variant of XCES implemented for documents
     */
    public static final String XCES_ILSP_VARIANT = "http://w3id.org/meta-share/omtd-share/XcesIlspVariant";

    /**
     * The TIGER XML format was created for encoding syntactic constituency structures in the German
     * TIGER corpus. It has since been used for many other corpora as well. TIGERSearch is a
     * linguistic search engine specifically targetting this format. The format has later been
     * extended to also support semantic frame annotations.
     */
    public static final String TIGER_XML = "http://w3id.org/meta-share/omtd-share/TigerXml";

    /**
     * GrAF (Graph Annotation Format) is an extension of the Linguistic Annotation Framework (LAF)
     */
    public static final String GRAF = "http://w3id.org/meta-share/omtd-share/Graf";

    /**
     * The NAF format is linguistic annotation format designed for complex NLP pipelines. NAF
     * combines strengths of the Linguistic Annotation Framework (LAF) as described in Ide et al.
     * (2003) and the NLP Interchange Format (Hellman et al. 2013, NIF).
     */
    public static final String NAF = "http://w3id.org/meta-share/omtd-share/Naf";

    /**
     * An XML data exchange format developed within the WebLicht architecture to facilitate
     * efficient interoperability between the tools; it allows the various linguistic annotations
     * produced by the tools within WebLicht to be stored in one document; it supports incremental
     * enrichment of linguistic annotations at various levels of analysis in a stand-off
     * XML\u2010based format
     */
    public static final String TCF = "http://w3id.org/meta-share/omtd-share/Tcf";

    /**
     * Format for linguistic annotations of documents used for the ALVIS framework
     */
    public static final String ALVIS_ENRICHED_DOCUMENT_FORMAT = "http://w3id.org/meta-share/omtd-share/AlvisEnrichedDocumentFormat";

    /**
     * Inline XML file format
     */
    public static final String INLINE_XML = "http://w3id.org/meta-share/omtd-share/InlineXml";

    /**
     * Format following Dialogue Act Markup Language (DiAML) which is defined within the ISO
     * standard 24617-2
     */
    public static final String DIAML = "http://w3id.org/meta-share/omtd-share/Diaml";

    /**
     * BRAT stand-off format for annotations (BRAT is a online environment for collaborative text
     * annotation, cf. http://brat.nlplab.org/)
     */
    public static final String BRAT = "http://w3id.org/meta-share/omtd-share/Brat";

    /**
     * Format of the I2B2 challenge
     */
    public static final String I2B2 = "http://w3id.org/meta-share/omtd-share/I2b2";

    /**
     * A structured model and format to enable annotations to be shared and reused across different
     * hardware and software platforms.
     */
    public static final String WEB_ANNOTATION_FORMAT = "http://w3id.org/meta-share/omtd-share/WebAnnotationFormat";

    /**
     * Data format for TEI-encoded (Text Encoding Initiative) texts
     */
    public static final String TEI = "http://w3id.org/meta-share/omtd-share/Tei";

    /**
     * KAF (also known as Knowledge Annotation Format) is a language neutral annotation format
     * representing both morpho-syntactic and semantic annotation of documents through a stand-off
     * multilayered structure
     */
    public static final String KAF = "http://w3id.org/meta-share/omtd-share/Kaf";

    /**
     * AlvisAE protocol format
     */
    public static final String CADIXE_JSON = "http://w3id.org/meta-share/omtd-share/Cadixe_json";

    /**
     * The NLP Interchange Format (NIF) is an RDF/OWL-based format that aims to achieve
     * interoperability between Natural Language Processing (NLP) tools, language resources and
     * annotations; it consists of specifications, ontologies and software (overview), which are
     * combined under the version identifier "NIF 2.0", but are versioned individually
     */
    public static final String NIF = "http://w3id.org/meta-share/omtd-share/Nif";

    /**
     * The purpose of the TMX format is to provide a standard method to describe translation memory
     * data that is being exchanged among tools and/or translation vendors, while introducing little
     * or no loss of critical data during the process.
     */
    public static final String TMX = "http://w3id.org/meta-share/omtd-share/Tmx";

    /**
     * Penn Tree Bank formats
     */
    public static final String PTB = "http://w3id.org/meta-share/omtd-share/Ptb";

    /**
     * Penn Treebank chunked format
     */
    public static final String PTB_CHUNKED = "http://w3id.org/meta-share/omtd-share/PtbChunked";

    /**
     * Penn Treebank combined format
     */
    public static final String PTB_COMBINED = "http://w3id.org/meta-share/omtd-share/PtbCombined";

    /**
     * Format of the Tu\u0308bingen Partially Parsed Corpus of Written German (Tu\u0308PP-D/Z) XML
     * files; T\u00fcPP D/Z
     * (http://www.sfs.uni-tuebingen.de/de/ascl/ressourcen/corpora/tuepp-dz.html) is a collection of
     * articles from the German newspaper taz (die tageszeitung) annotated and encoded in a XML
     * format.
     */
    public static final String TUEPP = "http://w3id.org/meta-share/omtd-share/Tuepp";

    /**
     * Format according to the Prague Markup Language
     * (http://ufal.mff.cuni.cz/jazz/PML/index_en.html); PML is a generic data format based on XML
     * intended for storing linguistically annotated data, such as the Prague Dependency Treebank,
     * also annotation lexicons, etc.
     */
    public static final String PML = "http://w3id.org/meta-share/omtd-share/Pml";

    /**
     * Topic proportions in the shape [\t]\t\t...
     */
    public static final String MALLET_LDA_TOPIC_PROPORTIONS = "http://w3id.org/meta-share/omtd-share/MalletLdaTopicProportions";

    /**
     * DkPro format for tokenized files containing one sentence per line and tokens split by
     * whitespaces.
     */
    public static final String DKPRO_TOKENIZED = "http://w3id.org/meta-share/omtd-share/DkproTokenized";

    /**
     * Factored tag lemma format
     */
    public static final String FACTORED_TAG_LEM_FORMAT = "http://w3id.org/meta-share/omtd-share/FactoredTagLemFormat";

    /**
     * FoLiA is an XML-based annotation format, suitable for the representation of linguistically
     * annotated language resources
     */
    public static final String FOLIA = "http://w3id.org/meta-share/omtd-share/Folia";

    /**
     * Export format for annotated corpora in the NeGra project
     */
    public static final String NEGRA_EXPORT = "http://w3id.org/meta-share/omtd-share/NegraExport";

    /**
     * Format of the LLL challenge
     */
    public static final String LLL = "http://w3id.org/meta-share/omtd-share/Lll";

    /**
     * Topic proportions in the shape [\t]\t\t... sorted
     */
    public static final String MALLET_LDA_TOPIC_PROPORTIONS_SORTED = "http://w3id.org/meta-share/omtd-share/MalletLdaTopicProportionsSorted";

    /**
     * Data format according to the EMMA (Extensible MultiModal Annotation markup language)
     * specifications, cf. https://www.w3.org/TR/2007/CR-emma-20071211/
     */
    public static final String EMMA = "http://w3id.org/meta-share/omtd-share/Emma";

    /**
     * Formats used for linked data
     */
    public static final String LINKED_DATA_FORMAT = "http://w3id.org/meta-share/omtd-share/LinkedDataFormat";

    /**
     * Formats used for wikipedia
     */
    public static final String WIKIPEDIA_FORMAT = "http://w3id.org/meta-share/omtd-share/WikipediaFormat";

    /**
     * Format for wikipedia discussion pages
     */
    public static final String WIKIPEDIA_DISCUSSION = "http://w3id.org/meta-share/omtd-share/WikipediaDiscussion";

    /**
     * Format of wikipedia pages in the database (articles, discussions, etc)
     */
    public static final String WIKIPEDIA_PAGE = "http://w3id.org/meta-share/omtd-share/WikipediaPage";

    /**
     * Format for wikipedia articles
     */
    public static final String WIKIPEDIA_ARTICLE = "http://w3id.org/meta-share/omtd-share/WikipediaArticle";

    /**
     * Format for wikipedia revision pages
     */
    public static final String WIKIPEDIA_REVISION = "http://w3id.org/meta-share/omtd-share/WikipediaRevision";

    /**
     * Pairs of adjacent revisions of all articles
     */
    public static final String WIKIPEDIA_REVISION_PAIR = "http://w3id.org/meta-share/omtd-share/WikipediaRevisionPair";

    /**
     * Format for wikipedia links
     */
    public static final String WIKIPEDIA_LINK = "http://w3id.org/meta-share/omtd-share/WikipediaLink";

    /**
     * The Java Wikipedia API (Bliki engine) is a parser library for converting Wikipedia wikitext
     * notation to HTML.
     */
    public static final String BLIKIWIKIPEDIA = "http://w3id.org/meta-share/omtd-share/Blikiwikipedia";

    /**
     * Format of general article infos
     */
    public static final String WIKIPEDIA_ARTICLE_INFO = "http://w3id.org/meta-share/omtd-share/WikipediaArticleInfo";

    /**
     * Reads all article pages that match a query created by the numerous parameters of this class.
     */
    public static final String WIKIPEDIA_QUERY = "http://w3id.org/meta-share/omtd-share/WikipediaQuery";

    /**
     * Format for wikipedia pages that contain or do not contain the templates specified in the
     * template whitelist and template blacklist
     */
    public static final String WIKIPEDIA_TEMPLATE_FILTERED_ARTICLE = "http://w3id.org/meta-share/omtd-share/WikipediaTemplateFilteredArticle";

    /**
     * Superclass of JSON formats
     */
    public static final String JSON = "http://w3id.org/meta-share/omtd-share/Json";

    /**
     * A Twitter-style JSON format used for GATE documents
     */
    public static final String GATE_JSON = "http://w3id.org/meta-share/omtd-share/Gate_json";

    /**
     * Common format for social media data from http://datasift.com
     */
    public static final String DATASIFT_JSON = "http://w3id.org/meta-share/omtd-share/Datasift_json";

    /**
     * Any format of a computer file in which information is stored in the form of ones and zeros,
     * or in some other binary (two-state) sequence; used mainly for executable files or files that
     * need to be interpreted by a computer program
     */
    public static final String BINARY_FORMAT = "http://w3id.org/meta-share/omtd-share/BinaryFormat";

    /**
     * A compressed binary encoding of GATE XML
     */
    public static final String FAST_INFOSET = "http://w3id.org/meta-share/omtd-share/FastInfoset";

    /**
     * Superclass for grouping together XML formats
     */
    public static final String XML = "http://w3id.org/meta-share/omtd-share/Xml";

    /**
     * BioC is a simple format to share text data and annotations.
     */
    public static final String XML_BIOC = "http://w3id.org/meta-share/omtd-share/XmlBioc";

    /**
     * Data format for the XML version of the British National Corpus (http://www.natcorp.ox.ac.uk/)
     */
    public static final String BNC_FORMAT = "http://w3id.org/meta-share/omtd-share/BncFormat";

    /**
     * XML-based format for GATE components
     */
    public static final String GATE_XML = "http://w3id.org/meta-share/omtd-share/GateXml";

    /**
     * XML format for OWL ontologies
     */
    public static final String OWL_XML = "http://w3id.org/meta-share/omtd-share/Owl_xml";

    /**
     * Data format for RDF (Resource Description Framework) XML format; RDF/XML is a serialisation
     * for RDF
     */
    public static final String RDF_XML = "http://w3id.org/meta-share/omtd-share/Rdf_xml";

    /**
     * XPath is a language for addressing parts of an XML document, designed to be used by both XSLT
     * and XPointer.
     */
    public static final String XPATH = "http://w3id.org/meta-share/omtd-share/Xpath";

    /**
     * A format used by a specific type of corpus (collection of texts)
     */
    public static final String CORPUS_FORMAT = "http://w3id.org/meta-share/omtd-share/CorpusFormat";

    /**
     * Data format specific to the ACL Anthology Reference Corpus (http://acl-arc.comp.nus.edu.sg/),
     * most probably version 20080325
     */
    public static final String ACL_ANTHOLOGY_CORPUS_FORMAT = "http://w3id.org/meta-share/omtd-share/AclAnthologyCorpusFormat";

    /**
     * Format of the Aimed corpus (225 abstracts from MEDLINE) with the gold standard sentence,
     * protein, protein-protein interaction annotations.
     */
    public static final String AIMED_CORPUS_FORMAT = "http://w3id.org/meta-share/omtd-share/AimedCorpusFormat";

    /**
     * File format used by the Web1T n-gram corpus, a huge collection of n-grams collected from the
     * internet.
     */
    public static final String WEB1T = "http://w3id.org/meta-share/omtd-share/Web1t";

    /**
     * Reuters-21578 corpus in SGML format
     */
    public static final String REUTERS21578SGML = "http://w3id.org/meta-share/omtd-share/Reuters21578Sgml";

    /**
     * KEA-style (Keyphrase Extraction Algorithm) corpus
     */
    public static final String KEA_CORPUS = "http://w3id.org/meta-share/omtd-share/KeaCorpus";

    /**
     * Reuters-21578 corpus transformed into text format using ExtractReuters in the
     * lucene-benchmarks project
     */
    public static final String REUTERS21578TXT = "http://w3id.org/meta-share/omtd-share/Reuters21578Txt";

    /**
     * Formats used for the GATE framework
     */
    public static final String GATE_FORMAT = "http://w3id.org/meta-share/omtd-share/GateFormat";

    /**
     * Formats for RDF (Resource Description Framework) resources
     */
    public static final String RDF_FORMATS = "http://w3id.org/meta-share/omtd-share/RdfFormats";

    /**
     * Serialization format for ontologies according to the Open Biomedical Ontologies model.
     */
    public static final String OBO = "http://w3id.org/meta-share/omtd-share/Obo";

    /**
     * Superclass for formats used for OWL
     */
    public static final String OWL = "http://w3id.org/meta-share/omtd-share/Owl";

    /**
     * Textual syntax for RDF that allows an RDF graph to be completely written in a compact and
     * natural text form, with abbreviations for common usage patterns and datatypes.
     */
    public static final String TURTLE = "http://w3id.org/meta-share/omtd-share/Turtle";

    private DataFormatType()
    {
        // No instances
    }
}
