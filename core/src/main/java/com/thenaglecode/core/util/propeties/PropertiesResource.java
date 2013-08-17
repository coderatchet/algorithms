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
 * Time: 4:36 PM
 */

@BindingType
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD,
        ElementType.PARAMETER })
public @interface PropertiesResource {

    /**
     * The name of the properties file that is visible by the callers class loader.
     * Excludes the '.properties' extension.
     * @return the name of the properties file without the .properties on the end.
     */
    @Nonbinding
    public String name();

}