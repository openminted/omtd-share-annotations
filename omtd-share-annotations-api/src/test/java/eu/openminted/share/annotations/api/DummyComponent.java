package eu.openminted.share.annotations.api;

import eu.openminted.registry.domain.ComponentTypeEnum;

@Component(value=ComponentTypeEnum.READER)
@ResourceInput(
        encoding = "UTF-8",
        keyword = "some keyword",
        annotationLevel = "lemmatization",
        language = @Language(languageId="en", languageTag="en", scriptId="Latn", regiontId="154", variantId="fonipa"),
        dataFormat = @DataFormat(dataFormat = "conll2000", mimeType = "text/tab-separated-values", fileExtension = ".conll"))
@ResourceOutput(
        encoding = "UTF-8",
        keyword = "some keyword",
        annotationLevel = "lemmatization",
        language = @Language(languageId="en", languageTag="en", scriptId="Latn", regiontId="154", variantId="fonipa"),
        dataFormat = @DataFormat(dataFormat = "conll2000", mimeType = "text/tab-separated-values", fileExtension = ".conll"))
public class DummyComponent
{

}
