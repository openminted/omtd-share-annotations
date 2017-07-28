package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import eu.openminted.registry.domain.ProcessingResourceTypeEnum;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface ResourceOutput
{
    ProcessingResourceTypeEnum type();
    String[] encoding() default {};
    Language[] language() default {};
    String[] annotationLevel() default {};
    String[] keyword() default {};
    DataFormat[] dataFormat() default {};
}
