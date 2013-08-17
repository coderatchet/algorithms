package com.thenaglecode.core.util.propeties;

import javax.enterprise.util.Nonbinding;
import javax.xml.ws.BindingType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/2/13
 * Time: 4:52 PM
 */

@BindingType
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD,
        ElementType.PARAMETER })
public @interface PropertyBundleResource {

    public static final String DEFAULT_FILE = "configuration";

    @Nonbinding
    public String subsystem();

    @Nonbinding
    public String file() default DEFAULT_FILE;
}
