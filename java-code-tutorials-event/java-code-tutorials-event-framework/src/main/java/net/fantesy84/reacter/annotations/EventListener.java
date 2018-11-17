/**
 * Created: 2017-01-05
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.reacter.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author junjie.ge
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface EventListener {
	String name() default "";
}
