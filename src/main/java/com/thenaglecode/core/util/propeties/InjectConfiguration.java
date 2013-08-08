package com.thenaglecode.core.util.propeties;

import javax.xml.ws.BindingType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: Jared Nagle
 * Date: 7/08/13
 * Time: 7:50 PM
 */
@BindingType
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface InjectConfiguration {

    public static final String DEFAULT_FILE = "configuration";

    /**
     * The subsystem that the configuration file belongs to. this is without
     * the base domain prefix. e.g. to get a property file in the subsystem
     * "com.thenaglecode.algorithms.somesub" you would set subsystem to
     * "algorithms.somesub"
     *
     * @return The subsystem that the configuration file belongs to.
     */
    public String subsystem();

    /**
     * the name of the properties file without the .properties on the end.
     *
     * @return name of the properties file without the .properties on the end.
     *         defaults to "configuration"
     */
    public String file() default DEFAULT_FILE;
}
