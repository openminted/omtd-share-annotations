package eu.openminted.share.annotations.api;

import eu.openminted.registry.domain.ComponentTypeEnum;

@Component(value=ComponentTypeEnum.READER)
@ResourceInput(dataFormats = @DataFormat(dataFormat = "conll2000", mimeType = "text/tab-separated-values", fileExtension = ".conll"))
@ResourceOutput(dataFormats = @DataFormat(dataFormat = "conll2000", mimeType = "text/tab-separated-values", fileExtension = ".conll"))
public class DummyComponent
{

}
