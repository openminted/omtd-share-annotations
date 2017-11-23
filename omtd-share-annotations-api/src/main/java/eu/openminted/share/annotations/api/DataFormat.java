package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface DataFormat
{
    String dataFormat() default "";
    String mimeType() default "";
    @Deprecated String fileExtension() default "";
    @Deprecated String description() default "";
    @Deprecated String documentationURL() default "";
}
