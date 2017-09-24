package eu.openminted.share.annotations.api;

@Component(value="reader")
@ResourceInput(
        type = "corpus",
        encoding = "UTF-8",
        keyword = "some keyword",
        annotationLevel = "lemmatization",
        language = @Language(languageId="en", languageTag="en", scriptId="Latn", regiontId="154", variantId="fonipa"),
        dataFormat = @DataFormat(dataFormat = "conll2000", mimeType = "text/tab-separated-values", fileExtension = ".conll"))
@ResourceOutput(
        type = "corpus",
        encoding = "UTF-8",
        keyword = "some keyword",
        annotationLevel = "lemmatization",
        language = @Language(languageId="en", languageTag="en", scriptId="Latn", regiontId="154", variantId="fonipa"),
        dataFormat = @DataFormat(dataFormat = "conll2000", mimeType = "text/tab-separated-values", fileExtension = ".conll"))
public class DummyComponent
{
    // Nothing
}
