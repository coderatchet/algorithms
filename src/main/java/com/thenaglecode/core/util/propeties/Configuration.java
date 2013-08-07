package com.thenaglecode.core.util.propeties;

import javax.enterprise.util.Nonbinding;

/**
 * Created with IntelliJ IDEA.
 * User: Jared Nagle
 * Date: 7/08/13
 * Time: 7:50 PM
 * To change this template use File | Settings | File Templates.
 */
public @interface Configuration {

    public String subsystem();

    public String file() default "configuration";
}
