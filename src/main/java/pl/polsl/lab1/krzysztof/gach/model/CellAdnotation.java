package pl.polsl.lab1.krzysztof.gach.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define a default value for a class.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CellAdnotation {
    /**
     * Specifies the default value for the class.
     * @return 
     */
    String value();
}
