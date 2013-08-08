package com.thenaglecode.core;

import com.thenaglecode.core.util.propeties.ConfigurationItem;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/8/13
 * Time: 11:27 AM
 */
public interface Configuration {
    public static final String DEFAULT_BASE_PACKAGE_ID = "default.base.package";
    public static final String DEFAULT_CONFIGURATION_FILENAME_ID = "default.configuration.filename";
    public static final String DEFAULT_CONFIGURATION_FILENAMES_ID = "configuration.filenames";

    public static final ConfigurationItem DEFAULT_BASE_PACKAGE = new ConfigurationItem(DEFAULT_BASE_PACKAGE_ID, "com.thenaglecode");
    public static final ConfigurationItem DEFAULT_CONFIGURATION_FILENAME = new ConfigurationItem(DEFAULT_CONFIGURATION_FILENAME_ID, "configuration");
}
