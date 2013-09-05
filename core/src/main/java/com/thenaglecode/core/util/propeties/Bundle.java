package com.thenaglecode.core.util.propeties;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 9/3/13
 * Time: 5:11 PM
 *
 * @see CoreProducer#produceResourceBundle(javax.enterprise.inject.spi.InjectionPoint)
 */
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface Bundle {
    public String subsystem() default "";
    public String name() default "configuration";
    public String locale() default "";
}
