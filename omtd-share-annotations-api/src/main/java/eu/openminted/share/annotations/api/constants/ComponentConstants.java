package eu.openminted.share.annotations.api.constants;

public class ComponentConstants
{
    /**
     * Contains values permitted for ComponentType field in OMTD-SHARE 2.0.1
     * <a href="https://openminted.github.io/releases/omtd-share/2.0.1/xsd/OMTD-SHARE-Component.xsd">https://openminted.github.io/releases/omtd-share/2.0.1/xsd/OMTD-SHARE-Component.xsd</a>
     */
    public static class ComponentTypeConstants
    {
        /**
         * A component used for providing access to data resources
         */
        public static final String accessComponent = "accessComponent";

        /**
         * A component that reads content of various types (pdf, txt, xml etc.)
         */
        public static final String reader = "reader";

        /**
         * A component that writes processing results in various formats
         */
        public static final String writer = "writer";

        /**
         * A component that provides support to developers
         */
        public static final String supportComponent = "supportComponent";

        /**
         * A component or interface that renders the contents of a resource in a graphic way for visualisation purposes
         */
        public static final String visualiser = "visualiser";

        /**
         * A component that helps in the debugging process
         */
        public static final String debugger = "debugger";

        /**
         * A component used to confirm that a system/resource meets the specifications and fulfills its intended purpose
         */
        public static final String validator = "validator";

        /**
         * A component that provides access to the contents of a resource but intended only for access by humans
         */
        public static final String viewer = "viewer";

        /**
         * A component that provides access to the contents of a corpus but intended only for access by humans
         */
        public static final String corpusViewer = "corpusViewer";

        /**
         * A component that provides access to the contents of a lexical/conceptual resoruces but intended only for access by humans
         */
        public static final String lexiconViewer = "lexiconViewer";

        /**
         * A component that allows humans to edit the contents of a resource
         */
        public static final String editor = "editor";

        /**
         * A component that is used in training models for machine learning
         */

        public static final String mlTrainer = "mlTrainer";

        /**
         * A component that is used in predicting based on machine learning models
         */
        public static final String mlPredictor = "mlPredictor";

        /**
         * A component that is used for extracting features
         */
        public static final String featureExtractor = "featureExtractor";

        /**
         * A component that performs data splitting for cross validation purposes
         */
        public static final String dataSplitter = "dataSplitter";

        /**
         * A component that supports data merging from various sources
         */
        public static final String dataMerger = "dataMerger";

        /**
         * A component that performs conversion between formats of a resource
         */
        public static final String converter = "converter";

        /**
         * A component that converts a constituency tree into a dependency tree
         */
        public static final String dependencyConverter = "dependencyConverter";

        /**
         * A component that generates a dependency tree from typically token and POS annotations
         */
        public static final String dependencyParser = "dependencyParser";

        /**
         * A component that a constituency tree from typically token and POS annotations
         */
        public static final String constituencyParser = "constituencyParser";

        /**
         * A component that is used in the evaluation of the performance of a component
         */
        public static final String evaluator = "evaluator";

        /**
         * A component that supports controlling flows
         */
        public static final String flowController = "flowController";

        /**
         * A component that performs analysis tasks based on a script
         */
        public static final String scriptBasedAnalyser = "scriptBasedAnalyser";

        /**
         * A component that allows matching (mapping) of elements
         */
        public static final String matcher = "matcher";

        /**
         * A component that allows matching of elements based on a gazeteer
         */
        public static final String gazeteerBasedMatcher = "gazeteerBasedMatcher";

        /**
         * A component that supports crowd sourcing operations
         */
        public static final String crowdSourcingComponent = "crowdSourcingComponent";

        /**
         * A component that collects (retrieves) data from various sources
         */
        public static final String dataCollector = "dataCollector";

        /**
         * A component that crawls the web and collects data from various web sites
         */
        public static final String crawler = "crawler";

        /**
         * A component that is used in processing operations
         */
        public static final String processor = "processor";

        /**
         * A component that annotates any language data (text, video, audio etc.), i.e. adds any descriptive or
         * analytic notations (structural, linguistic, etc) to raw language data
         */
        public static final String annotator = "annotator";

        /**
         * A component that segments a text into structural untis (chapters, paragraphs, sentences, words, tokens etc.)
         */
        public static final String segmenter = "segmenter";

        /**
         * A component that extracts stems from words in a text, usually by removing the most common morphological and
         * inflectional endings from words
         */
        public static final String stemmer = "stemmer";

        /**
         * A component that annotates the tokens of a text with lemma information
         */
        public static final String lemmatizer = "lemmatizer";

        /**
         * A component that annotates tokens of a text with morphological information (part-of-speech and morphological
         * features)
         */
        public static final String morphologicalTagger = "morphologicalTagger";

        /**
         * A component that groups tokens of a text into chunks
         */
        public static final String chunker = "chunker";

        /**
         * A component that takes as input text and returns a form of data structure (e.g. syntactic parse as a tree,
         * or bracketed structure etc.)
         */
        public static final String parser = "parser";

        /**
         * A component that annotates tokens of a text with coreference information, i.e. annotating expressions that
         * refer to the same entity in the text
         */
        public static final String coreferenceAnnotator = "coreferenceAnnotator";

        /**
         * A component that seeks to locate and classify elements in a text into pre-defined categories such as the
         * names of persons, organizations, locations, expressions of times, etc.
         */
        public static final String namedEntityRecognizer = "namedEntityRecognizer";

        /**
         * A component that annotates the tokens of a text with semantic features
         */
        public static final String semanticsAnnotator = "semanticsAnnotator";

        /**
         * A component that annotates the tokens of a text with Semantic Role labels
         */
        public static final String srlAnnotator = "srlAnnotator";

        /**
         * A component that annotates the tokens of a text with readability scores
         */
        public static final String readabilityAnnotator = "readabilityAnnotator";

        /**
         * A component that detects and annotates equivalence relations between items (corpora, texts, paragraphs,
         * sentences, phrases, words) in two languages
         */
        public static final String aligner = "aligner";

        /**
         * A component that generates (semi-)automatically natural language texts (based on non-linguistic data,
         * keywords, logical forms, knowledge bases...)
         */
        public static final String generator = "generator";

        /**
         * A component that produces a natural language synopsis of a longer text
         */
        public static final String summarizer = "summarizer";

        /**
         * A component that outputs a simpler rendition of a given item (sentence, text etc.)
         */
        public static final String simplifier = "simplifier";

        /**
         * A component that is used at pre- or post-processing stages in order to normalize input/output
         */
        public static final String preOrPostProcessingComponent = "preOrPostProcessingComponent";

        /**
         * A component that corrects spelling mistakes in a text
         */
        public static final String spellingChecker = "spellingChecker";

        /**
         * A component that corrects grammatical mistakes in a text
         */
        public static final String grammarChecker = "grammarChecker";

        /**
         * A component that removes unwanted material from text, usually as a pre-processing step
         */
        public static final String normalizer = "normalizer";

        /**
         * A component that is used for filtering text input or annotations based on specific criteria
         */
        public static final String filters = "filters";

        /**
         * A component that is used for analysing an input text in order to perform extraction of features/information
         * (e.g. word list), or characterization of the whole text
         */
        public static final String analyzer = "analyzer";

        /**
         * A component that guesses the topic of a text
         */
        public static final String topicExtractor = "topicExtractor";

        /**
         * A component that tries to classify a document into one or more categories
         */
        public static final String documentClassifier = "documentClassifier";

        /**
         * A component that identifies the language of a given text based on its contents
         */
        public static final String languageIdentifier = "languageIdentifier";

        /**
         * A component that tries to identify sentences that express the author’s negative or positive feelings on
         * something; (Sentiment analysis (also known as opinion mining) refers to the use of natural language
         * processing, text analysis and computational linguistics to identify and extract subjective information in
         * source materials (wikipedia) )
         */
        public static final String sentimentAnalyzer = "sentimentAnalyzer";

        /**
         * A component that tries to recognize and annotate emotions (e.g. fear, anger, happiness etc.) from text,
         * video, audio and image
         */
        public static final String emotionRecognizer = "emotionRecognizer";

        /**
         * A component that tries to extract keywords from a given text
         */
        public static final String keywordsExtractor = "keywordsExtractor";

        /**
         * A component that tries to extract terms from a corpus
         */
        public static final String termExtractor = "termExtractor";

        /**
         * A component that tries to automatically recognise elements that reveal contradiction in a text
         */
        public static final String contradictionDetector = "contradictionDetector";

        /**
         * A component that tries to extract information related to incidents referred to in a text
         */
        public static final String eventExtractor = "eventExtractor";

        /**
         * A component that tries to identify persuasive expressions in a given text
         */
        public static final String persuasiveExpressionMiner = "persuasiveExpressionMiner";

        /**
         * A component that automatically extracts structured information from unstructured and/or semi-structured
         * machine-readable documents
         */
        public static final String informationExtractor = "informationExtractor";

        /**
         * A component that extracts structured lexical resources from corpora
         */
        public static final String lexiconExtractorFromCorpora = "lexiconExtractorFromCorpora";

        /**
         * A component that extracts specific lexical information contained in other lexica
         */
        public static final String lexiconExtractorFromLexica = "lexiconExtractorFromLexica";

        /**
         * A component that tries to identify which sense of a word (i.e. meaning) is used in a sentence, when the word
         * has multiple meanings (Source: wikipedia)
         */
        public static final String wordSenseDisambiguator = "wordSenseDisambiguator";

        /**
         * Value for componentType, to be used when the desired value is not included in the listed values
         */
        public static final String other = "other";
    }
}
