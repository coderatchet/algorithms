package com.thenaglecode.core.ui;

import com.thenaglecode.core.util.propeties.ConfigurationItem;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/8/13
 * Time: 11:31 AM
 */
public interface Configuration {
    /*
        IDS
     */
    public static final String BOOTSTRAP_API_CDN_CSS_ID = "bootstrap.cdn.css";
    public static final String BOOTSTRAP_API_CDN_JAVASCRIPT_ID = "bootstrap.cdn.javascript";
    public static final String JQUERY_VERSION_ID = "jquery.version";

    /*
        CONFIG ITEMS
     */
    public static final ConfigurationItem BOOTSTRAP_API_CDN_CSS = new ConfigurationItem(BOOTSTRAP_API_CDN_CSS_ID, "//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/css/bootstrap.min.css");
    public static final ConfigurationItem BOOTSTRAP_API_CDN_JAVASCRIPT = new ConfigurationItem(BOOTSTRAP_API_CDN_JAVASCRIPT_ID, "//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/js/bootstrap.min.js");
    public static final ConfigurationItem JQUERY_VERSION = new ConfigurationItem(JQUERY_VERSION_ID, "2.0.3");
}
