package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Language
{
    String languageTag();
    String languageId() default "";
    String scriptId() default "";
    String regiontId() default "";
    String variantId() default "";
}
