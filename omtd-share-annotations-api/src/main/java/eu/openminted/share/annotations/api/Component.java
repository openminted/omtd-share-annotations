package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as an OpenMinTeD-compatible TDM component.
 * A piece of software typically for a specific technical purpose, such as a particular implementation of a
 * part-of-speech tagger (e.g TreeTagger), a tree parsing program (e.g. mstparser), etc.; it is wrapped in a standard
 * way within a particular component-oriented framework such as UIMA, GATE, etc. or as a specific type of web service.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Component
{
    /**
     * Component type from the OpenMinTeD-SHARE inventory that is associated with this component.
     * @see eu.openminted.share.annotations.api.constants.ComponentConstants.ComponentTypeConstants ComponentType permitted values
     */
    String value() default "";

    /**
     * Indicates whether the component can be used as an integrated end-user application
     */
    boolean application() default false;
    
    // String applicationFunction();
}
