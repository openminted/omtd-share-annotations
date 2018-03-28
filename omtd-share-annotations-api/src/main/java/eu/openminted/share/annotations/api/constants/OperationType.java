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

public final class OperationType
{
    /**
     * A component that provides support to developers
     */
    public static final String SUPPORT_COMPONENT = "http://w3id.org/meta-share/omtd-share/SupportComponent";

    /**
     * A component used to confirm that a system/resource meets the specifications and fulfills its
     * intended purpose
     */
    public static final String VALIDATOR = "http://w3id.org/meta-share/omtd-share/Validator";

    /**
     * A component that supports crowdsourcing operations
     */
    public static final String CROWDSOURCING_COMPONENT = "http://w3id.org/meta-share/omtd-share/CrowdsourcingComponent";

    /**
     * A component that collects (retrieves) data from various sources
     */
    public static final String DATA_COLLECTOR = "http://w3id.org/meta-share/omtd-share/DataCollector";

    /**
     * A component that crawls the web and collects data from various web sites
     */
    public static final String CRAWLER = "http://w3id.org/meta-share/omtd-share/Crawler";

    /**
     * A component that is used in predicting based on machine learning models
     */
    public static final String MACHINE_LEARNING_PREDICTOR = "http://w3id.org/meta-share/omtd-share/MachineLearningPredictor";

    /**
     * A component that is used for extracting features
     */
    public static final String FEATURE_EXTRACTOR = "http://w3id.org/meta-share/omtd-share/FeatureExtractor";

    /**
     * A component or interface that renders the contents of a resource in a graphic way for
     * visualisation purposes
     */
    public static final String VISUALISER = "http://w3id.org/meta-share/omtd-share/Visualiser";

    /**
     * A component that supports data merging from various sources
     */
    public static final String DATA_MERGER = "http://w3id.org/meta-share/omtd-share/DataMerger";

    /**
     * A component that is used in the evaluation of the performance of a component
     */
    public static final String EVALUATOR = "http://w3id.org/meta-share/omtd-share/Evaluator";

    /**
     * A component that is used in training models for machine learning
     */
    public static final String TRAINER_OF_MACHINE_LEARNING_MODELS = "http://w3id.org/meta-share/omtd-share/TrainerOfMachineLearningModels";

    /**
     * A component that is used in the debugging process
     */
    public static final String DEBUGGER = "http://w3id.org/meta-share/omtd-share/Debugger";

    /**
     * A component that allows humans to edit the contents of a resource
     */
    public static final String EDITOR = "http://w3id.org/meta-share/omtd-share/Editor";

    /**
     * A technology that supports the development of software components and data resources required
     * for their operation
     */
    public static final String SOFTWARE_DEVELOPMENT_ENVIRONMENT = "http://w3id.org/meta-share/omtd-share/SoftwareDevelopmentEnvironment";

    /**
     * A component that supports controlling flows
     */
    public static final String FLOW_CONTROLLER = "http://w3id.org/meta-share/omtd-share/FlowController";

    /**
     * A component that performs data splitting for cross validation purposes
     */
    public static final String DATA_SPLITTER = "http://w3id.org/meta-share/omtd-share/DataSplitter";

    /**
     * A component that performs analysis tasks based on a script
     */
    public static final String SCRIPT_BASED_ANALYSER = "http://w3id.org/meta-share/omtd-share/ScriptBasedAnalyser";

    /**
     * A component that supports humans in accessing the contents of a resource
     */
    public static final String VIEWER = "http://w3id.org/meta-share/omtd-share/Viewer";

    /**
     * A component that supports humans in accessing the contents of a corpus
     */
    public static final String CORPUS_VIEWER = "http://w3id.org/meta-share/omtd-share/CorpusViewer";

    /**
     * A component that supports humans in accessing the contents of a lexical/conceptual resource
     */
    public static final String LEXICON_VIEWER = "http://w3id.org/meta-share/omtd-share/LexiconViewer";

    /**
     * A component that performs conversion between formats of a resource
     */
    public static final String CONVERTER = "http://w3id.org/meta-share/omtd-share/Converter";

    /**
     * A component that converts a constituency tree into a dependency tree
     */
    public static final String DEPENDENCY_CONVERTER = "http://w3id.org/meta-share/omtd-share/DependencyConverter";

    /**
     * A component that allows matching of elements
     */
    public static final String MATCHER = "http://w3id.org/meta-share/omtd-share/Matcher";

    /**
     * A component that allows matching of elements based on a gazeteer
     */
    public static final String GAZETEER_BASED_MATCHER = "http://w3id.org/meta-share/omtd-share/GazeteerBasedMatcher";

    /**
     * A component that provides access to data resources, e.g. reads a resource or writes the
     * output of a process in a certain format
     */
    public static final String ACCESS_COMPONENT = "http://w3id.org/meta-share/omtd-share/AccessComponent";

    /**
     * A component that reads content of various types (pdf, txt, xml etc.)
     */
    public static final String READER = "http://w3id.org/meta-share/omtd-share/Reader";

    /**
     * A component that writes processing results in various formats
     */
    public static final String WRITER = "http://w3id.org/meta-share/omtd-share/Writer";

    /**
     * A component that is used in processing operations
     */
    public static final String PROCESSOR = "http://w3id.org/meta-share/omtd-share/Processor";

    /**
     * A component that annotates any data (text, video, audio etc.), i.e. adds any descriptive or
     * analytic notations (structural, linguistic, etc) to raw data
     */
    public static final String ANNOTATOR = "http://w3id.org/meta-share/omtd-share/Annotator";

    /**
     * A component that annotates tokens of a text with coreference labels, marking expressions that
     * refer to the same entity in the text
     */
    public static final String CO_REFERENCE_ANNOTATOR = "http://w3id.org/meta-share/omtd-share/CoReferenceAnnotator";

    /**
     * A component that annotates the tokens of a text with lemma information
     */
    public static final String LEMMATIZER = "http://w3id.org/meta-share/omtd-share/Lemmatizer";

    /**
     * A component that extracts stems from words in a text, usually by removing the most common
     * morphological and inflectional endings from words
     */
    public static final String STEMMER = "http://w3id.org/meta-share/omtd-share/Stemmer";

    /**
     * A component that takes as input text and returns a form of data structure (e.g. syntactic
     * parse as a tree, or bracketed structure etc.)
     */
    public static final String PARSER = "http://w3id.org/meta-share/omtd-share/Parser";

    /**
     * A component that generates a dependency tree from typically token and part-of-speech
     * annotations
     */
    public static final String DEPENDENCY_PARSER = "http://w3id.org/meta-share/omtd-share/DependencyParser";

    /**
     * A component that builds a constituency tree from typically token and part-of-speech
     * annotations
     */
    public static final String CONSTITUENCY_PARSER = "http://w3id.org/meta-share/omtd-share/ConstituencyParser";

    /**
     * A component that seeks to locate and classify elements in a text into pre-defined categories
     * such as the names of persons, organizations, locations, expressions of times,
     * discipline-specific classes, etc
     */
    public static final String NAMED_ENTITITY_RECOGNIZER = "http://w3id.org/meta-share/omtd-share/NamedEntitityRecognizer";

    /**
     * A component that annotates tokens of a text with morphological information (part-of-speech
     * and morphological features)
     */
    public static final String MORPHOLOGICAL_TAGGER = "http://w3id.org/meta-share/omtd-share/MorphologicalTagger";

    /**
     * A component that annotates tokens of a text with part-of-speech information
     */
    public static final String PART_OF_SPEECH_TAGGER = "http://w3id.org/meta-share/omtd-share/PartOfSpeechTagger";

    /**
     * A component that annotates the tokens of a text with semantic features
     */
    public static final String SEMANTIC_ANNOTATOR = "http://w3id.org/meta-share/omtd-share/SemanticAnnotator";

    /**
     * A component that annotates the tokens of a text with Semantic Role labels
     */
    public static final String ANNOTATOR_OF_SEMANTIC_ROLE_LABELS = "http://w3id.org/meta-share/omtd-share/AnnotatorOfSemanticRoleLabels";

    /**
     * A component that annotates the tokens of a text with readability scores
     */
    public static final String READABILITY_ANNOTATOR = "http://w3id.org/meta-share/omtd-share/ReadabilityAnnotator";

    /**
     * A component that detects and annotates equivalence relations between items (corpora, texts,
     * paragraphs, sentences, phrases, words) in two languages
     */
    public static final String ALIGNER = "http://w3id.org/meta-share/omtd-share/Aligner";

    /**
     * A component that groups tokens of a text into chunks
     */
    public static final String CHUNKER = "http://w3id.org/meta-share/omtd-share/Chunker";

    /**
     * A component that segments a text into structural untis (chapters, paragraphs, sentences,
     * words, tokens etc.)
     */
    public static final String SEGMENTER = "http://w3id.org/meta-share/omtd-share/Segmenter";

    /**
     * A component that recognizes and tags tokens (words, punctuation marks, digits etc.) in a text
     */
    public static final String TOKENIZER = "http://w3id.org/meta-share/omtd-share/Tokenizer";

    /**
     * A component that splits a text into sentences
     */
    public static final String SENTENCE_SPLITTER = "http://w3id.org/meta-share/omtd-share/SentenceSplitter";

    /**
     * A component that generates (semi-)automatically natural language texts (based on
     * non-linguistic data, keywords, logical forms, knowledge bases...)
     */
    public static final String GENERATOR = "http://w3id.org/meta-share/omtd-share/Generator";

    /**
     * A component that produces a natural language synopsis of a longer text
     */
    public static final String SUMMARIZER = "http://w3id.org/meta-share/omtd-share/Summarizer";

    /**
     * A component that outputs a simpler rendition of a given item (sentence, text etc.)
     */
    public static final String SIMPLIFIER = "http://w3id.org/meta-share/omtd-share/Simplifier";

    /**
     * A component that is used at pre- or post-processing stages in order to normalize input/output
     */
    public static final String PRE_OR_POST_PROCESSOR = "http://w3id.org/meta-share/omtd-share/PreOrPostProcessor";

    /**
     * A component that corrects grammatical mistakes in a text
     */
    public static final String GRAMMAR_CHECKER = "http://w3id.org/meta-share/omtd-share/GrammarChecker";

    /**
     * A component that corrects spelling mistakes in a text
     */
    public static final String SPELLING_CHECKER = "http://w3id.org/meta-share/omtd-share/SpellingChecker";

    /**
     * A component that is used for filtering text input or annotations based on specific criteria
     */
    public static final String FILTER = "http://w3id.org/meta-share/omtd-share/Filter";

    /**
     * A component that removes unwanted material from text (e.g. quotation marks, hyphenations
     * etc.) or performs edits so that specific items (tokens, dates, etc.) are
     * substituted/represented with normalized values
     */
    public static final String NORMALIZER = "http://w3id.org/meta-share/omtd-share/Normalizer";

    /**
     * A component that is used for analyzing an input text in order to extract specific
     * features/information (e.g. word list), or to produce statements over the whole text (e.g.
     * classify it by topic)
     */
    public static final String ANALYZER = "http://w3id.org/meta-share/omtd-share/Analyzer";

    /**
     * A component that tries to recognize and annotate emotions (e.g. fear, anger, happiness etc.)
     * from text, video, audio and image
     */
    public static final String EMOTION_RECOGNIZER = "http://w3id.org/meta-share/omtd-share/EmotionRecognizer";

    /**
     * A component that extracts specific lexical information contained in other lexica
     */
    public static final String LEXICON_EXTRACTOR_FROM_LEXICA = "http://w3id.org/meta-share/omtd-share/LexiconExtractorFromLexica";

    /**
     * A component that tries to extract information related to incidents referred to in a text
     */
    public static final String EVENT_EXTRACTOR = "http://w3id.org/meta-share/omtd-share/EventExtractor";

    /**
     * A component that identifies the language of a given text based on its contents
     */
    public static final String LANGUAGE_IDENTIFIER = "http://w3id.org/meta-share/omtd-share/LanguageIdentifier";

    /**
     * A component that tries to identify persuasive expressions in a given text
     */
    public static final String PERSUASIVE_EXPRESSION_MINER = "http://w3id.org/meta-share/omtd-share/PersuasiveExpressionMiner";

    /**
     * A component that tries to automatically recognize elements that reveal contradiction in a
     * text
     */
    public static final String CONTRADICTION_DETECTOR = "http://w3id.org/meta-share/omtd-share/ContradictionDetector";

    /**
     * A component that tries to extract terms from a corpus
     */
    public static final String TERM_EXTRACTOR = "http://w3id.org/meta-share/omtd-share/TermExtractor";

    /**
     * A component that guesses the topic of a text
     */
    public static final String TOPIC_EXTRACTOR = "http://w3id.org/meta-share/omtd-share/TopicExtractor";

    /**
     * A component that tries to extract keywords from a given text
     */
    public static final String KEYWORD_EXTRACTOR = "http://w3id.org/meta-share/omtd-share/KeywordExtractor";

    /**
     * A component that extracts lexical information from corpora in order to produce structured
     * lexical resources
     */
    public static final String LEXICON_EXTRACTOR_FROM_CORPORA = "http://w3id.org/meta-share/omtd-share/LexiconExtractorFromCorpora";

    /**
     * A component that tries to identify variables (in social sciences) in a text
     */
    public static final String VARIABLES_DECTECTOR = "http://w3id.org/meta-share/omtd-share/VariablesDectector";

    /**
     * A component that is used to disambiguate between two or more ambiguous items
     */
    public static final String DISAMBIGUATOR = "http://w3id.org/meta-share/omtd-share/Disambiguator";

    /**
     * A component that tries to identify which sense of a word (i.e. meaning) is used in a
     * sentence, when the word has multiple meanings
     */
    public static final String WORD_SENSE_DISAMBIGUATOR = "http://w3id.org/meta-share/omtd-share/WordSenseDisambiguator";

    /**
     * A component that tries to classify a document into one or more categories
     */
    public static final String DOCUMENT_CLASSIFIER = "http://w3id.org/meta-share/omtd-share/DocumentClassifier";

    /**
     * A component that tries to identify sentences that express the author\u2019s negative or
     * positive feelings on something
     */
    public static final String SENTIMENT_ANALYZER = "http://w3id.org/meta-share/omtd-share/SentimentAnalyzer";

    /**
     * A component that automatically extracts structured information from unstructured and/or
     * semi-structured machine-readable documents
     */
    public static final String INFORMATION_EXTRACTOR = "http://w3id.org/meta-share/omtd-share/InformationExtractor";

    /**
     * A note by way of explanation or comment added to a text or diagram [OED,
     * https://en.oxforddictionaries.com/definition/annotation]. Text or corpus annotation refers to
     * the interpretative linguistic information grounded in a knowledge resource that is added
     * manually or automatically to a text or corpus respectively.
     */
    public static final String ANNOTATION = "http://w3id.org/meta-share/omtd-share/Annotation";

    /**
     * The process/task of adding annotations (notes or comments) to a text; in TDM, the annotations
     * refer mainly to the interpretative linguistic information grounded in a knowledge resource
     * that is added manually or automatically to a text
     */
    public static final String TEXT_ANNOTATION = "http://w3id.org/meta-share/omtd-share/TextAnnotation";

    /**
     * The task/process of recognizing and marking the syntactic structure of a text or text segment
     */
    public static final String PARSING = "http://w3id.org/meta-share/omtd-share/Parsing";

    /**
     * The task/process of identifying and marking the grammatical structure of a sentence,
     * establishing relationships between "head" words and words that modify those heads
     */
    public static final String DEPENDENCY_PARSING = "http://w3id.org/meta-share/omtd-share/DependencyParsing";

    /**
     * The task/process of dividing a sentence into chunks (non-overlapping text segments consisting
     * of a head and preceding function words and/or modifiers)
     */
    public static final String CHUNKING = "http://w3id.org/meta-share/omtd-share/Chunking";

    /**
     * The task/process of identifying and marking constituents (phrases, governed by a head and
     * including function words and/or modifiers ) in a text or text segment
     */
    public static final String CONSTITUENCY_PARSING = "http://w3id.org/meta-share/omtd-share/ConstituencyParsing";

    /**
     * The task/process of adding morphosyntactic tags to words in a text, i.e. part-of-speech and,
     * optionally, morphological features per part-of-speech.
     */
    public static final String MORPHOSYNTACTIC_TAGGING = "http://w3id.org/meta-share/omtd-share/MorphosyntacticTagging";

    /**
     * The task/process of marking words with the part of speech (word category, e.g. noun, verb
     * etc.) to which they belong
     */
    public static final String POS_TAGGING = "http://w3id.org/meta-share/omtd-share/PosTagging";

    /**
     * The annotation of words with morphological information besides the part of speech and
     * dependent upon it (e.g. for nouns: gender, number and case; for verbs: tense, number, person
     * etc.)
     */
    public static final String BELOW_POS_TAGGING = "http://w3id.org/meta-share/omtd-share/BelowPosTagging";

    /**
     * The task/process of adding annotations relevant to discourse, such as discourse structure,
     * discourse markers etc.
     */
    public static final String DISCOURSE_ANNOTATION = "http://w3id.org/meta-share/omtd-share/DiscourseAnnotation";

    /**
     * The act or process of forming reasons and of drawing conclusions and applying them to a case
     * in discussion [https://www.merriam-webster.com/dictionary/argumentation]
     */
    public static final String ARGUMENTATION = "http://w3id.org/meta-share/omtd-share/Argumentation";

    /**
     * Lemmatisation (or lemmatization) in linguistics is the process of grouping together the
     * inflected forms of a word so they can be analysed as a single item, identified by the word's
     * lemma, or dictionary form. [Wikipedia]
     */
    public static final String LEMMATIZATION = "http://w3id.org/meta-share/omtd-share/Lemmatization";

    /**
     * The task/process of segmenting a text and recognizing textual structural units (paragraphs,
     * sentences, words etc.)
     */
    public static final String STRUCTURAL_ANNOTATION = "http://w3id.org/meta-share/omtd-share/StructuralAnnotation";

    /**
     * The task/process of recognizing and tagging tokens (words, punctuation marks, digits etc.) in
     * a text
     */
    public static final String TOKENIZATION = "http://w3id.org/meta-share/omtd-share/Tokenization";

    /**
     * The task/process of recognizing and tagging sentence boundaries in a text
     */
    public static final String SENTENCE_SPLITTING = "http://w3id.org/meta-share/omtd-share/SentenceSplitting";

    /**
     * The task/process of annotating the internal structure of a document (e.g. book chapters,
     * sections in a journal article, title, preface, images/figures etc.)
     */
    public static final String ANNOTATION_OF_DOCUMENT_STRUCTURE = "http://w3id.org/meta-share/omtd-share/AnnotationOfDocumentStructure";

    /**
     * The task/process of segmenting a text into paragraphs and marking their boundaries
     */
    public static final String PARAGRAPH_SPLITTING = "http://w3id.org/meta-share/omtd-share/ParagraphSplitting";

    /**
     * The task/process of cutting off the ends of words (mainly inflectional affixes but sometimes
     * also derivational affixes) aiming to relate words to a base form.
     */
    public static final String STEMMING = "http://w3id.org/meta-share/omtd-share/Stemming";

    /**
     * The task/process of adding annotations pertaining to the morphological level of analysis
     * (e.g. gender, number, person etc.)
     */
    public static final String MORPHOLOGICAL_ANNOTATION = "http://w3id.org/meta-share/omtd-share/MorphologicalAnnotation";

    /**
     * The task/process of annotating multi-word units, i.e. combinations of words that are
     * considered as one
     */
    public static final String ANNOTATION_OF_MULTI_WORD_UNITS = "http://w3id.org/meta-share/omtd-share/AnnotationOfMultiWordUnits";

    /**
     * The task/process of adding annotations relevant to the derivational level of analysis (e.g.
     * recognizing derivational affixes, tagging their meaning etc.)
     */
    public static final String ANNOTATION_OF_DERIVATIONAL_FEATURES = "http://w3id.org/meta-share/omtd-share/AnnotationOfDerivationalFeatures";

    /**
     * The task/process of marking compounds (single words composed of two or more free morphemes)
     * and their parts
     */
    public static final String ANNOTATION_OF_COMPOUNDING_FEATURES = "http://w3id.org/meta-share/omtd-share/AnnotationOfCompoundingFeatures";

    /**
     * The task/process of segmenting (cutting) a word into root and affixes
     */
    public static final String WORD_SEGMENTATION = "http://w3id.org/meta-share/omtd-share/WordSegmentation";

    /**
     * The task/process of representing information about entities in a form that machines are
     * capable of understanding it
     */
    public static final String KNOWLEDGE_REPRESENTATION = "http://w3id.org/meta-share/omtd-share/KnowledgeRepresentation";

    /**
     * The process/task of extracting, organising and systematising knowledge usually of a specific
     * domain from external sources so that it can be used in a knowledge-based system
     */
    public static final String KNOWLEDGE_ACQUISITION = "http://w3id.org/meta-share/omtd-share/KnowledgeAcquisition";

    /**
     * The task/process of constructing lexical resources based on the restructuring of lexical
     * information contained in lexica (e.g. by parsing definitions or using syntactic information
     * attached to lemmas)
     */
    public static final String LEXICON_EXTRACTION_FROM_LEXICA = "http://w3id.org/meta-share/omtd-share/LexiconExtractionFromLexica";

    /**
     * The task/process of creating an ontology based on other resources (corpora, other lexical
     * resources, etc.)
     */
    public static final String ONTOLOGY_ACQUISITION = "http://w3id.org/meta-share/omtd-share/OntologyAcquisition";

    /**
     * The task/process of constructing lexical resources from corpora
     */
    public static final String LEXICON_ACQUISITION_FROM_CORPORA = "http://w3id.org/meta-share/omtd-share/LexiconAcquisitionFromCorpora";

    /**
     * The task/process of improving an ontology, typically by adding new relations or entities
     */
    public static final String ONTOLOGY_ENHANCEMENT = "http://w3id.org/meta-share/omtd-share/OntologyEnhancement";

    /**
     * The task/process of improving (e.g. increasing the size of entries, improving the
     * information, adding new types of information, etc.) a lexicon
     */
    public static final String LEXICON_ENHANCEMENT = "http://w3id.org/meta-share/omtd-share/LexiconEnhancement";

    /**
     * The task/process of inducing word translations from monolingual or comparable corpora in two
     * languages
     */
    public static final String BILINGUAL_LEXICON_INDUCTION = "http://w3id.org/meta-share/omtd-share/BilingualLexiconInduction";

    /**
     * The activity of obtaining information resources relevant to an information need from a
     * collection of information resources; searches can be based on full-text or other
     * content-based indexing
     */
    public static final String INFORMATION_RETRIEVAL = "http://w3id.org/meta-share/omtd-share/InformationRetrieval";

    /**
     * A type of search which, in contrast to traditional lookup search, covers a broad class of
     * activities, such as investigating, evaluating, comparing, and synthesizing
     */
    public static final String EXPLORATORY_SEARCH = "http://w3id.org/meta-share/omtd-share/ExploratorySearch";

    /**
     * The process/task of removing (filtering out) redundant or unwanted information from an
     * information stream using (semi)automated or computerized methods prior to presentation to a
     * human user; the selection of the items is based on the correlation between the content of the
     * items and the user\u2019s preferences (content-based filtering) or the correlation between
     * people with similar preferences (collaborative filtering)
     */
    public static final String INFORMATION_FILTERING = "http://w3id.org/meta-share/omtd-share/InformationFiltering";

    /**
     * The delivery of information in the form of suggestions by recommender systems; recommender
     * systems seek to predict the "rating" or "preference" that a user would give to an item
     */
    public static final String INFORMATION_FILTERING_BY_RECOMMENDER_SYSTEMS = "http://w3id.org/meta-share/omtd-share/InformationFilteringByRecommenderSystems";

    /**
     * A type of search that seeks to improve search accuracy by understanding the searcher's intent
     * and the contextual meaning of terms as they appear in the searchable dataspace, whether on
     * the Web or within a closed system, to generate more relevant results.
     */
    public static final String SEMANTIC_SEARCH = "http://w3id.org/meta-share/omtd-share/SemanticSearch";

    /**
     * The task/process of automatically searching large volumes of data for patterns that can be
     * considered knowledge about the data
     */
    public static final String KNOWLEDGE_DISCOVERY = "http://w3id.org/meta-share/omtd-share/KnowledgeDiscovery";

    /**
     * The task/process of assigning documents into classes or categories
     */
    public static final String TEXT_CATEGORIZATION = "http://w3id.org/meta-share/omtd-share/TextCategorization";

    /**
     * The process/task of computationally identifying and categorizing opinions expressed in a
     * piece of text, especially in order to determine whether the writer's attitude towards a
     * particular topic, product, etc. is positive, negative, or neutral
     */
    public static final String SENTIMENT_ANALYSIS = "http://w3id.org/meta-share/omtd-share/SentimentAnalysis";

    /**
     * A task/process that intends to recognize for two text fragments whether the meaning of one
     * text is entailed in that of the other, i.e. whether the truth of one text fragment follows
     * from that of the other fragment.
     */
    public static final String RECOGNIZING_TEXTUAL_ENTAILMENT = "http://w3id.org/meta-share/omtd-share/RecognizingTextualEntailment";

    /**
     * The task/process of identifying the topic of a text or dataset (e.g. by clustering keywords
     * or using topic models)
     */
    public static final String TOPIC_DETECTION = "http://w3id.org/meta-share/omtd-share/TopicDetection";

    /**
     * The task/process where computer systems try to automatically answer questions posed by users
     * in the form of natural language.
     */
    public static final String QUESTION_ANSWERING = "http://w3id.org/meta-share/omtd-share/QuestionAnswering";

    /**
     * In Machine Learning, it refers to the use of algorithms that learn from previous data in
     * order to make predictions on data (by estimating probabilities from previous data)
     */
    public static final String PREDICTION = "http://w3id.org/meta-share/omtd-share/Prediction";

    /**
     * The process/task of automatically extracting structured information from unstructured and/or
     * semi-structured data
     */
    public static final String INFORMATION_EXTRACTION = "http://w3id.org/meta-share/omtd-share/InformationExtraction";

    /**
     * A subtask of information extraction that seeks to locate and classify named entities in text
     * into pre-defined categories such as the names of persons, organizations, locations,
     * expressions of times, quantities, monetary values, percentages, etc.
     */
    public static final String NAMED_ENTITY_RECOGNITION = "http://w3id.org/meta-share/omtd-share/NamedEntityRecognition";

    /**
     * The task/process of identifying and extracting (especially from political speech texts)
     * pieces of text that aim to persuade
     */
    public static final String PERSUASIVE_EXPRESSION_MINING = "http://w3id.org/meta-share/omtd-share/PersuasiveExpressionMining";

    /**
     * The process/task of identifying and representing argumentation in text, so that systems have
     * the ability to use them in tasks, such as automated logical reasoning
     */
    public static final String COMPUTATIONAL_ARGUMENTATION = "http://w3id.org/meta-share/omtd-share/ComputationalArgumentation";

    /**
     * Extraction of information that pertains to specific domains/disciplines; it can be used
     * combined with "Annotation type" to specify the type of information extracted
     */
    public static final String EXTRACTION_OF_DOMAIN_SPECIFIC_INFORMATION = "http://w3id.org/meta-share/omtd-share/ExtractionOfDomainSpecificInformation";

    /**
     * The task/process of detecting in a text and extracting information relevant to funding (e.g.
     * funding programme, award, funder etc.)
     */
    public static final String EXTRACTION_OF_FUNDING_INFORMATION = "http://w3id.org/meta-share/omtd-share/ExtractionOfFundingInformation";

    /**
     * The task/process of identifying conflicting statements (contradictions) in a dataset
     */
    public static final String CONTRADICTION_DETECTION = "http://w3id.org/meta-share/omtd-share/ContradictionDetection";

    /**
     * The task/process of identifying keywords (words deemed indicative of the topic/subject) in a
     * text/corpus
     */
    public static final String KEYWORD_EXTRACTION = "http://w3id.org/meta-share/omtd-share/KeywordExtraction";

    /**
     * The process/task of identifying and classifying relation mentions between entities in text
     * and/or data.
     */
    public static final String RELATION_EXTRACTION = "http://w3id.org/meta-share/omtd-share/RelationExtraction";

    /**
     * The act/process of identifying and extracting candidate terms from a domain-specific corpus
     */
    public static final String TERM_EXTRACTION = "http://w3id.org/meta-share/omtd-share/TermExtraction";

    /**
     * The process/task of identifying types of feelings (e.g. anger, fear, happiness, sadness,
     * etc.) in the linguistic expression of texts or facial expressions
     */
    public static final String EMOTION_DETECTION = "http://w3id.org/meta-share/omtd-share/EmotionDetection";

    /**
     * The task/process of detecting in a text mentions of a specific class of entities (e.g.
     * biochemical entities, historical persons)
     */
    public static final String ENTITY_MENTION_RECOGNITION = "http://w3id.org/meta-share/omtd-share/EntityMentionRecognition";

    /**
     * The task/process of identifying temporal expressions (also called timex) in a text in order
     * to extract temporal information
     */
    public static final String TEMPORAL_EXPRESSION_RECOGNITION = "http://w3id.org/meta-share/omtd-share/TemporalExpressionRecognition";

    /**
     * The process/task of identifying events in data (text, video, images etc.), usually combined
     * with their classification into types of events and recognition of the event attributes (e.g.
     * time, place, participants and duration)
     */
    public static final String EVENT_DETECTION = "http://w3id.org/meta-share/omtd-share/EventDetection";

    private OperationType()
    {
        // No instances
    }
}
