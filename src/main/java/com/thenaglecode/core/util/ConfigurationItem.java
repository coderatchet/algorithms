package com.thenaglecode.core.util;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationItem {
    String key;
    String defaultValue;

    public ConfigurationItem(String key, String defaultValue){
        setKey(key);
        setDefaultValue(defaultValue);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
