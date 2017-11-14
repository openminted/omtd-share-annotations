package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapping of inputContentResourceInfo from OMTD-SHARE schema.
 * Groups together information on the requirements set on the input resource of a component.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface ResourceInput
{
    /**
     * The type of the resource that a component takes as input or produces as output
     * @see eu.openminted.share.annotations.api.constants.ProcessingResourceConstants.ResourceTypeConstants ResourceType permitted values
     */
    String[] type();
    String[] encoding() default {};
    Language[] language() default {};
    String[] annotationLevel() default {};
    String[] keyword() default {};
    DataFormat[] dataFormat() default {};
}
