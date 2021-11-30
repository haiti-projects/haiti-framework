package dev.struchkov.godfather.context.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Используется для описания полей в классах.
 *
 * @author upagge [08/07/2019]
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface Description {

    String value();

    String example() default "";

}
