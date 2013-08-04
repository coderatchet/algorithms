package com.thenaglecode.core.ui;

import com.thenaglecode.core.util.ConfigurationItem;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/2/13
 * Time: 5:39 PM
 */
public class Configuration {
    public static final String BOOTSTRAP_API_CDN_CSS_ID = "bootstrap.cdn.css";
    public static final String BOOTSTRAP_API_CDN_JAVASCRIPT_ID = "bootstrap.cdn.javascript";

    public static final ConfigurationItem BOOTSTRAP_API_CDN_CSS = new ConfigurationItem(BOOTSTRAP_API_CDN_CSS_ID, "//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/css/bootstrap.min.css");
    public static final ConfigurationItem BOOTSTRAP_API_CDN_JAVASCRIPT = new ConfigurationItem(BOOTSTRAP_API_CDN_JAVASCRIPT_ID, "//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/js/bootstrap.min.js");
}
