package com.thenaglecode.core.util.propeties;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:57 PM
 */
public class ConfigurationItem {

    @NotNull
    String key;
    @NotNull
    String defaultValue;

    public ConfigurationItem(@NotNull String key, @NotNull String defaultValue){
        setKey(key);
        setDefaultValue(defaultValue);
    }

    @NotNull
    public String getKey() {
        return key;
    }

    public void setKey(@NotNull String key) {
        this.key = key;
    }

    @NotNull
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(@NotNull String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
