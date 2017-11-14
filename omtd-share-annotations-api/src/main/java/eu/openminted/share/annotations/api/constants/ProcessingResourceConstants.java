package eu.openminted.share.annotations.api.constants;

public class ProcessingResourceConstants
{
    /**
     * Contains values permitted for processingResourceType field in OMTD-SHARE 2.0.1
     * <a href="https://openminted.github.io/releases/omtd-share/2.0.1/xsd/OMTD-SHARE-Component.xsd">https://openminted.github.io/releases/omtd-share/2.0.1/xsd/OMTD-SHARE-Component.xsd</a>
     */
    public static class ResourceTypeConstants
    {
        /**
         * For components that take as input or produce as output a set of documents (corpus)
         */
        public static final String corpus = "corpus";

        /**
         * For components that take as input or produce as output a single document
         */
        public static final String document = "document";

        /**
         * For components that take as input a text typed in by the user
         */
        public static final String userInputText = "userInputText";

        /**
         * For components that take as input a lexical or conceptual resource (e.g. lexicon, term list, ontology etc.)
         * in order to perform operations on it (and not use it as an ancillary/reference resource, e.g. for annotating a document)
         */
        public static final String lexicalConceptualResource = "lexicalConceptualResource";

        /**
         * For components that take as input a language description (e.g. a grammar) in order to perform operations on
         * it (and not use it as an ancillary/reference resource, e.g. for parsing a document)
         */
        public static final String languageDescription = "languageDescription";
    }
}
