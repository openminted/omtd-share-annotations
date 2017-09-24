package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface ResourceOutput
{
    String[] type();
    String[] encoding() default {};
    Language[] language() default {};
    String[] annotationLevel() default {};
    String[] keyword() default {};
    DataFormat[] dataFormat() default {};
}
