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

public final class AnnotationType
{
    /**
     * Any kind of annotation that is used to describe a document (e.g. identifier, size, location,
     * language etc.)
     */
    public static final String DOCUMENT_ANNOTATION_TYPE = "http://w3id.org/meta-share/omtd-share/DocumentAnnotationType";

    /**
     * A division of words based on common grammatical features
     */
    public static final String PART_OF_SPEECH = "http://w3id.org/meta-share/omtd-share/PartOfSpeech";

    /**
     * Any kind of annotation that is used for specific domains (e.g. genes and proteins from the
     * biomedical domain, plants from agriculture etc.)
     */
    public static final String DOMAIN_SPECIFIC_ANNOTATION = "http://w3id.org/meta-share/omtd-share/Domain-specificAnnotation";

    /**
     * Any kind of annotation that pertains to entities of social sciences; the use of TheSoz is
     * recommended
     */
    public static final String SOCIAL_SCIENCES_ENTITY = "http://w3id.org/meta-share/omtd-share/SocialSciencesEntity";

    /**
     * ALLBUS variable
     */
    public static final String ALLBUS_VARIABLE = "http://w3id.org/meta-share/omtd-share/AllbusVariable";

    /**
     * Historical event
     */
    public static final String HISTORICAL_EVENT = "http://w3id.org/meta-share/omtd-share/HistoricalEvent";

    /**
     * Method of research
     */
    public static final String METHOD_OF_RESEARCH = "http://w3id.org/meta-share/omtd-share/MethodOfResearch";

    /**
     * The main means of mass communication (broadcasting, publishing, and the Internet) regarded
     * collectively [https://en.oxforddictionaries.com/definition/media]
     */
    public static final String MEDIA = "http://w3id.org/meta-share/omtd-share/Media";

    /**
     * Theoretical frame
     */
    public static final String THEORETICAL_FRAME = "http://w3id.org/meta-share/omtd-share/TheoreticalFrame";

    /**
     * Official text
     */
    public static final String OFFICIAL_TEXT = "http://w3id.org/meta-share/omtd-share/OfficialText";

    /**
     * Any kind of annotation pertaining to entities of linguistics; the use of OLIA is recommended
     */
    public static final String LINGUISTIC_ENTITY = "http://w3id.org/meta-share/omtd-share/LinguisticEntity";

    /**
     * Any type of relation that holds between two or more entities of a specific domain
     */
    public static final String RELATION = "http://w3id.org/meta-share/omtd-share/Relation";

    /**
     * Any kind of annotation pertaining to entities of the agricultural domain; the use of the
     * AGROVOC thesaurus is recommended
     */
    public static final String AGRICULTURAL_ENTITY = "http://w3id.org/meta-share/omtd-share/AgriculturalEntity";

    /**
     * The physical appearance or biochemical characteristic of an organism as a result of the
     * interaction of its genotype and the environment
     * [http://www.biology-online.org/dictionary/Phenotype]
     */
    public static final String PHENOTYPE = "http://w3id.org/meta-share/omtd-share/Phenotype";

    /**
     * Physical and chemical property of substances
     */
    public static final String PHYSICO_CHEMICAL_PROPERTY = "http://w3id.org/meta-share/omtd-share/PhysicoChemicalProperty";

    /**
     * A protein family is a group of proteins that share a common evolutionary origin, reflected by
     * their related functions and similarities in sequence or structure
     * [https://www.ebi.ac.uk/training/online/course/introduction-protein-classification-ebi/protein-classification/what-are-protein-families]
     */
    public static final String PROTEIN_FAMILY = "http://w3id.org/meta-share/omtd-share/ProteinFamily";

    /**
     * An individual animal, plant, or single-celled life form
     * [https://en.oxforddictionaries.com/definition/organism]
     */
    public static final String ORGANISM = "http://w3id.org/meta-share/omtd-share/Organism";

    /**
     * The place or environment where an organism, plant or animal naturally or normally lives and
     * grows
     */
    public static final String HABITAT = "http://w3id.org/meta-share/omtd-share/Habitat";

    /**
     * Any of various nucleic acids that contain ribose and uracil as structural components and are
     * associated with the control of cellular chemical activities
     */
    public static final String RNA = "http://w3id.org/meta-share/omtd-share/Rna";

    /**
     * Specific sequence of nucleotides along a molecule of DNA (or, in the case of some viruses,
     * RNA) which represents functional units of heredity
     * [http://artemide.art.uniroma2.it:8081/agrovoc/agrovoc/en/page/c_3214]
     */
    public static final String GENE = "http://w3id.org/meta-share/omtd-share/Gene";

    /**
     * Marker
     */
    public static final String MARKER = "http://w3id.org/meta-share/omtd-share/Marker";

    /**
     * Wheat-related species
     */
    public static final String WHEAT_RELATED_SPECIES = "http://w3id.org/meta-share/omtd-share/WheatRelatedSpecies";

    /**
     * A type of grape
     */
    public static final String GRAPE_VARIETY = "http://w3id.org/meta-share/omtd-share/GrapeVariety";

    /**
     * A gene family is a set of several similar genes, formed by duplication of a single original
     * gene, and generally with similar biochemical functions
     * [https://en.wikipedia.org/wiki/Gene_family]
     */
    public static final String GENE_FAMILY = "http://w3id.org/meta-share/omtd-share/GeneFamily";

    /**
     * Any kind of annotation pertaining to entities of neuroscience
     */
    public static final String NEUROSCIENCE_ENTITY = "http://w3id.org/meta-share/omtd-share/NeuroscienceEntity";

    /**
     * Any kind of annotation pertaining to entities of biology
     */
    public static final String BIOLOGICAL_ENITY = "http://w3id.org/meta-share/omtd-share/BiologicalEnity";

    /**
     * A nerve cell that carries information between the brain and other parts of the body
     */
    public static final String NEURON = "http://w3id.org/meta-share/omtd-share/Neuron";

    /**
     * Model organism/species
     */
    public static final String MODEL_ORGANISM_SPECIES = "http://w3id.org/meta-share/omtd-share/ModelOrganism_species";

    /**
     * A single protein or protein complex that traverses the lipid bilayer of cell membrane and
     * form a channel to facilitate the movement of ions through the membrane according to their
     * electrochemical gradient [http://www.biology-online.org/dictionary/Ion_channel]
     */
    public static final String IONIC_CHANNEL = "http://w3id.org/meta-share/omtd-share/IonicChannel";

    /**
     * A set of animals or plants in which the members have similar characteristics to each other
     * and can breed with each other
     */
    public static final String SPECIES = "http://w3id.org/meta-share/omtd-share/Species";

    /**
     * Any substance involved in metabolism (= the chemical processes in the body needed for life)
     * [https://dictionary.cambridge.org/dictionary/english/metabolite]
     */
    public static final String METABOLITE = "http://w3id.org/meta-share/omtd-share/Metabolite";

    /**
     * Biological activity
     */
    public static final String BIOLOGICAL_ACTIVITY = "http://w3id.org/meta-share/omtd-share/BiologicalActivity";

    /**
     * Any substance (as an acid) that is formed when two or more other substances act upon one
     * another or that is used to produce a change in another substance
     * [https://www.merriam-webster.com/dictionary/chemical]
     */
    public static final String CHEMICAL = "http://w3id.org/meta-share/omtd-share/Chemical";

    /**
     * The influx and/or efflux of ions through an ion channel
     */
    public static final String IONIC_CURRENT = "http://w3id.org/meta-share/omtd-share/IonicCurrent";

    /**
     * Ionic conductance
     */
    public static final String IONIC_CONDUCTANCE = "http://w3id.org/meta-share/omtd-share/IonicConductance";

    /**
     * A specialized structure or junction that allows cell to cell communication
     * [http://www.biology-online.org/dictionary/Synapse]
     */
    public static final String SYNAPSE = "http://w3id.org/meta-share/omtd-share/Synapse";

    /**
     * Part of the brain
     */
    public static final String BRAIN_REGION = "http://w3id.org/meta-share/omtd-share/BrainRegion";

    /**
     * Any of various naturally occurring extremely complex substances that consist of amino-acid
     * residues joined by peptide bonds, contain the elements carbon, hydrogen, nitrogen, oxygen,
     * usually sulfur, and occasionally other elements (such as phosphorus or iron), and include
     * many essential biological compounds (such as enzymes, hormones, or antibodies)
     * [https://www.merriam-webster.com/dictionary/protein]
     */
    public static final String PROTEIN = "http://w3id.org/meta-share/omtd-share/Protein";

    /**
     * Any kind of annotation pertaining to entities from chemistry
     */
    public static final String CHEMICAL_ENTITY = "http://w3id.org/meta-share/omtd-share/ChemicalEntity";

    /**
     * Scientific value
     */
    public static final String SCIENTIFIC_VALUE = "http://w3id.org/meta-share/omtd-share/ScientificValue";

    /**
     * Any type of annotation that is relevant to scholarly analtyics (e.g. citations, funding
     * information etc.)
     */
    public static final String SCHOLARLY_COMMUNICATION_ANNOTATION = "http://w3id.org/meta-share/omtd-share/ScholarlyCommunicationAnnotation";

    /**
     * Any subdivision of a document, e.g. a chapter, abstract, etc.
     */
    public static final String DOCUMENT_SECTION = "http://w3id.org/meta-share/omtd-share/DocumentSection";

    /**
     * Reference to a book, paper, or author, especially in a scholarly work.
     */
    public static final String CITATION = "http://w3id.org/meta-share/omtd-share/Citation";

    /**
     * Annotation related to the funding of a resource (e.g. funder, funding project, etc.)
     */
    public static final String FUNDING = "http://w3id.org/meta-share/omtd-share/Funding";

    /**
     * A word or group of words used to describe or index the contents of a document
     */
    public static final String KEYWORD = "http://w3id.org/meta-share/omtd-share/Keyword";

    /**
     * The subject of a text or conversation, what it is about
     */
    public static final String TOPIC = "http://w3id.org/meta-share/omtd-share/Topic";

    /**
     * Scientific unit
     */
    public static final String SCIENTIFIC_UNIT = "http://w3id.org/meta-share/omtd-share/ScientificUnit";

    /**
     * Any type of annotation pertaining to the morphological level
     */
    public static final String MORPHOLOGICAL_ANNOTATION_TYPE = "http://w3id.org/meta-share/omtd-share/MorphologicalAnnotationType";

    /**
     * A single word composed of two or more free morphemes
     */
    public static final String COMPOUND = "http://w3id.org/meta-share/omtd-share/Compound";

    /**
     * The canonical or citation form used for referring to a word and its inflected forms
     */
    public static final String LEMMA = "http://w3id.org/meta-share/omtd-share/Lemma";

    /**
     * A stem is the root or roots of a word, together with any derivational affixes, to which
     * inflectional affixes are added. [http://www.glossary.sil.org/term/stem]
     */
    public static final String STEM = "http://w3id.org/meta-share/omtd-share/Stem";

    /**
     * Any feature relevant to the derivation process of a word (e.g. marking affixes, their meaning
     * etc.)
     */
    public static final String DERIVATIONAL_FEATURE = "http://w3id.org/meta-share/omtd-share/DerivationalFeature";

    /**
     * Property of a word that is expressed in its inflected form; examples include person, tense,
     * gender, case etc.
     */
    public static final String MORPHOLOGICAL_FEATURE = "http://w3id.org/meta-share/omtd-share/MorphologicalFeature";

    /**
     * A word is a unit which is a constituent at the phrase level and above. It is sometimes
     * identifiable according to such criteria as (a) being the minimal possible unit in a reply,
     * (b) having features such as a regular stress pattern, and phonological changes conditioned by
     * or blocked at word boundaries, (c) being the largest unit resistant to insertion of new
     * constituents within its boundaries, or (d) being the smallest constituent that can be moved
     * within a sentence without making the sentence ungrammatical. A word is sometimes placed, in a
     * hierarchy of grammatical constituents, above the morpheme level and below the phrase level.
     * [http://www.glossary.sil.org/term/word] In annotation, words are often used as equivalent to
     * tokens; thus, for instance, punctuation marks (traditionally not considered as words) will
     * also be annotated as "word".
     */
    public static final String WORD = "http://w3id.org/meta-share/omtd-share/Word";

    /**
     * Any type of annotation that pertains to the syntactic level
     */
    public static final String SYNTACTIC_ANNOTATION_TYPE = "http://w3id.org/meta-share/omtd-share/SyntacticAnnotationType";

    /**
     * A type of syntactic relation that holds between linguistic units, where we try to recognise
     * the head (governor) and its dependents
     */
    public static final String DEPENDENCY = "http://w3id.org/meta-share/omtd-share/Dependency";

    /**
     * A word or group of words that function as a single unit in a syntactic structure
     */
    public static final String CONSTITUENT = "http://w3id.org/meta-share/omtd-share/Constituent";

    /**
     * A link between the syntactic unit and the semantic unit (sense) of a word
     */
    public static final String SYNTACTICO_SEMANTIC_LINK = "http://w3id.org/meta-share/omtd-share/SyntacticoSemanticLink";

    /**
     * Group of words that function together; a chunk normally includes a head and some consecutive
     * (i.e. without gaps) preceding words
     */
    public static final String CHUNK = "http://w3id.org/meta-share/omtd-share/Chunk";

    /**
     * The number and types of syntactic arguments required by a certain lexical item (mainly verbs,
     * but also nouns and adjectives)
     */
    public static final String SUBCATEGORIZATION_FRAME = "http://w3id.org/meta-share/omtd-share/SubcategorizationFrame";

    /**
     * A tree that represents the dependency relations in a sentence, i.e. showing the governor
     * (head) and its dependents with directed links
     */
    public static final String DEPENDENCY_TREE = "http://w3id.org/meta-share/omtd-share/DependencyTree";

    /**
     * An ordered, rooted tree that represents the syntactic structure of a string according to a
     * constituency grammar (= phrase structure grammars). It distinguishes between terminal and
     * non-terminal nodes. The interior nodes are labeled by non-terminal categories of the grammar
     * (phrases), while the leaf nodes are labeled by terminal categories (parts of speech).
     * [adapted from https://en.wikipedia.org/wiki/Parse_tree]
     */
    public static final String CONSTITUENCY_TREE = "http://w3id.org/meta-share/omtd-share/ConstituencyTree";

    /**
     * Any type of annotation pertaining to the semantic level
     */
    public static final String SEMANTIC_ANNOTATION_TYPE = "http://w3id.org/meta-share/omtd-share/SemanticAnnotationType";

    /**
     * A thing that happens or takes place, especially one of importance
     * [https://en.oxforddictionaries.com/definition/event]
     */
    public static final String EVENT = "http://w3id.org/meta-share/omtd-share/Event";

    /**
     * A schematic representation of a situation involving various participants, props and other
     * conceptual roles, each of which is a frame element
     */
    public static final String SEMANTIC_FRAME = "http://w3id.org/meta-share/omtd-share/SemanticFrame";

    /**
     * A word or phrase referring to an entity, identified and annotated as such with a name
     * (label); examples include organizations, persons, places etc.
     */
    public static final String NAMED_ENTITY = "http://w3id.org/meta-share/omtd-share/NamedEntity";

    /**
     * A word or group of words that denotes an organization, such as company, association,
     * institution etc.
     */
    public static final String ORGANIZATION = "http://w3id.org/meta-share/omtd-share/Organization";

    /**
     * A word or group of words that refers to a person
     */
    public static final String PERSON = "http://w3id.org/meta-share/omtd-share/Person";

    /**
     * Spectral data is essentially data derived by the use of spectroscopic instruments
     */
    public static final String SPECTRAL_DATA = "http://w3id.org/meta-share/omtd-share/SpectralData";

    /**
     * A word or group of words that denotes a geographical entity
     */
    public static final String LOCATION = "http://w3id.org/meta-share/omtd-share/Location";

    /**
     * A text unit that denotes a date, a specific point in time
     */
    public static final String DATE = "http://w3id.org/meta-share/omtd-share/Date";

    /**
     * An affective state of consciousness in which joy, sorrow, fear, hate, or the like, is
     * experienced, as distinguished from cognitive and volitional states of consciousness
     * [http://www.dictionary.com/browse/emotion]
     */
    public static final String EMOTION = "http://w3id.org/meta-share/omtd-share/Emotion";

    /**
     * A semantic role is the underlying relationship that a participant has with the main verb in a
     * clause [http://www.glossary.sil.org/term/semantic-role]
     */
    public static final String SEMANTIC_ROLE = "http://w3id.org/meta-share/omtd-share/SemanticRole";

    /**
     * A linguistic expression (word, group of words, group of numbers etc.) that denotes time (a
     * point in time, duration, frequency)
     */
    public static final String TEMPORAL_EXPRESSION = "http://w3id.org/meta-share/omtd-share/TemporalExpression";

    /**
     * A relation holding between two or more words based on their meanings
     */
    public static final String LEXICAL_SEMANTIC_RELATION = "http://w3id.org/meta-share/omtd-share/LexicalSemanticRelation";

    /**
     * The affective state (judgement, feeling) of a person or group towards an entity or event
     */
    public static final String SENTIMENT = "http://w3id.org/meta-share/omtd-share/Sentiment";

    /**
     * A feature that distinguishes between positive, negative or neutral; in sentiment analysis, it
     * refers to determining whether the expressed opinion in a document, a sentence or an entity
     * feature/aspect is positive, negative, or neutral. [adapted from Wikipedia]
     */
    public static final String POLARITY = "http://w3id.org/meta-share/omtd-share/Polarity";

    /**
     * A division of words into classes based on their common semantic features
     */
    public static final String SEMANTIC_CLASS = "http://w3id.org/meta-share/omtd-share/SemanticClass";

    /**
     * The segment of a question that describes the entity about which the question is made
     */
    public static final String QUESTION_TOPICAL_TARGET = "http://w3id.org/meta-share/omtd-share/QuestionTopicalTarget";

    /**
     * Corresponds to the structural part of a lexical entry that contains the relevant semantic,
     * grammatical, and anthropological information for a lexical unit. [adapted from
     * http://www.glossary.sil.org/term/sense]
     */
    public static final String WORD_SENSE = "http://w3id.org/meta-share/omtd-share/WordSense";

    /**
     * A word or phrase used for persuasion purposes
     */
    public static final String PERSUASIVE_EXPRESSION = "http://w3id.org/meta-share/omtd-share/PersuasiveExpression";

    /**
     * The ease with which a reader can understand a written text.
     * [https://en.wikipedia.org/wiki/Readability]
     */
    public static final String READABILITY = "http://w3id.org/meta-share/omtd-share/Readability";

    /**
     * Degree of certainty about the validity of what is being asserted in the text
     */
    public static final String CERTAINTY_LEVEL = "http://w3id.org/meta-share/omtd-share/CertaintyLevel";

    /**
     * The linguistic expression of somebody\u2019s opinions, sentiments, emotions, evaluations,
     * beliefs, speculations (private states, i.e. states that are not open to objective observation
     * or verification). [http://www.mavir.net/docs/JWiebe-Subjectivity-nov2010.pdf]
     */
    public static final String SUBJECTIVITY = "http://w3id.org/meta-share/omtd-share/Subjectivity";

    /**
     * Any type of annotation that pertains to the structure of a document
     */
    public static final String STRUCTURAL_ANNOTATION_TYPE = "http://w3id.org/meta-share/omtd-share/StructuralAnnotationType";

    /**
     * A phrase is a syntactic structure that consists of more than one word but lacks the
     * subject-predicate organization of a clause. [http://www.glossary.sil.org/term/phrase]
     */
    public static final String PHRASE = "http://w3id.org/meta-share/omtd-share/Phrase";

    /**
     * A division of a text, usually about a single theme, consisting of one or more sentences and
     * marked by a new line, indentation or other conventions.
     */
    public static final String PARAGRAPH = "http://w3id.org/meta-share/omtd-share/Paragraph";

    /**
     * A combination of words that are considered as forming one semantic unit
     */
    public static final String MULTI_WORD_UNIT = "http://w3id.org/meta-share/omtd-share/MultiWordUnit";

    /**
     * A clause is a subdivision of a sentence containing a subject (argument) and predicate. It is
     * possible to have a word that implies or refers to a predicate rather than one explicitly
     * stated. [Pei and Gaynor 1980: 40, http://linguistics-ontology.org/gold/2010/Clause]
     */
    public static final String CLAUSE = "http://w3id.org/meta-share/omtd-share/Clause";

    /**
     * A set of characters surrounded by spaces or punctuation marks, as well as punctuation marks
     * themselves
     */
    public static final String TOKEN = "http://w3id.org/meta-share/omtd-share/Token";

    /**
     * A group of words, usually containing a verb, that expresses a thought in the form of a
     * statement, question, instruction, or exclamation and starts with a capital letter when
     * written [https://dictionary.cambridge.org/dictionary/english/sentence]
     */
    public static final String SENTENCE = "http://w3id.org/meta-share/omtd-share/Sentence";

    /**
     * Any type of annotation relevant to discourse
     */
    public static final String DISCOURSE_ANNOTATION_TYPE = "http://w3id.org/meta-share/omtd-share/DiscourseAnnotationType";

    /**
     * The response of the target recipients (audience) to a system, process or event
     */
    public static final String AUDIENCE_REACTION = "http://w3id.org/meta-share/omtd-share/AudienceReaction";

    /**
     * A set of statements that contradict each other (i.e. one of them asserts the truth and the
     * other the falsity of the proposition)
     */
    public static final String CONTRADICTION = "http://w3id.org/meta-share/omtd-share/Contradiction";

    /**
     * The relation that holds between two segments of discourse; e.g. causal, temporal etc.
     */
    public static final String DISCOURCE_RELATION = "http://w3id.org/meta-share/omtd-share/DiscourceRelation";

    /**
     * Coreference is the reference in one expression to the same referent in another expression.
     * [http://www.glossary.sil.org/term/coreference]
     */
    public static final String COREFERENCE = "http://w3id.org/meta-share/omtd-share/Coreference";

    /**
     * The pair of an entity and all the mentions of this entity formulated in various ways; used in
     * co-reference resolution
     */
    public static final String ENTITY_MENTION_PAIR = "http://w3id.org/meta-share/omtd-share/EntityMentionPair";

    /**
     * A speech act is an act that a speaker performs when making an utterance, including the
     * following: (a) A general act (illocutionary act) that a speaker performs, analyzable as
     * including: the uttering of words (utterance acts), making reference and predicating
     * (propositional acts), and a particular intention in making the utterance (illocutionary
     * force). (b) An act involved in the illocutionary act, including utterance acts and
     * propositional acts, (c) The production of a particular effect in the addressee
     * (perlocutionary act) [http://www.glossary.sil.org/term/speech-act]
     */
    public static final String SPEECH_ACT = "http://w3id.org/meta-share/omtd-share/SpeechAct";

    /**
     * A dialogue act has two main components: a communicative function and a semantic content. The
     * semantic content specifies the objects, relations, actions, events, etc. that the dialogue
     * act is about; the communicative function can be viewed as a specification of the way an
     * addressee uses the semantic content to update his or her information state when he or she
     * understands the corresponding stretch of dialogue.
     * [http://www.lrec-conf.org/proceedings/lrec2010/pdf/560_Paper.pdf]
     */
    public static final String DIALOGUE_ACT = "http://w3id.org/meta-share/omtd-share/DialogueAct";

    /**
     * A term is a designation consisting of one or more words representing a general concept in a
     * special language in a specific subject field [ISO 704:2009]
     */
    public static final String TERM = "http://w3id.org/meta-share/omtd-share/Term";

    private AnnotationType()
    {
        // No instances
    }
}
