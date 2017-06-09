package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import eu.openminted.registry.domain.ComponentTypeEnum;

/**
 * Marks a class as an OpenMinTeD-compatible TDM component.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Component
{
    /**
     * Component classes from the OpenMinTeD-SHARE inventory that this component is associated with.
     */
    ComponentTypeEnum[] classes() default {};
}
