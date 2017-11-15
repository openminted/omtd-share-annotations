package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapping of language from OMTD-SHARE schema http://www.meta-share.org/OMTD-SHARE_XMLSchema/v201/OMTD-SHARE-ResourceCommon.xsd
 * <br/><br/>
 * The language that is used in the resource or supported by the component, as specified in the BCP47 guidelines
 * (https://tools.ietf.org/html/bcp47); the language tag is built upon (a) language tag according to ISO 639-1 and for
 * languages not covered by this, the ISO 639-3; (b) the script tag according to ISO 15924; (c) the region tag
 * according to ISO 3166-1; (d) the variant subtag
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Language
{
    String languageTag();
    String languageId() default "";
    String scriptId() default "";
    String regionId() default "";
    String variantId() default "";
}
