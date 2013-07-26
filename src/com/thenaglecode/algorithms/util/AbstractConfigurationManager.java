package com.thenaglecode.algorithms.util;

import java.util.PropertyResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractConfigurationManager {

    PropertyResourceBundle bundle;
    private static final String DEFAULT_CONFIG_BUDNLE_NAME = "configuration";

    protected AbstractConfigurationManager(){
        this(DEFAULT_CONFIG_BUDNLE_NAME);
    }

    protected AbstractConfigurationManager(String propertyFile){
        init(propertyFile);
    }

    public String getValue(String key){
        if(bundle != null){
            return bundle.getString(key);
        } else {
            return null;
        }
    }

    public String getValue(ConfigurationItem configurationItem){
        if(bundle != null){
            String value = bundle.getString(configurationItem.getKey());
            return (value == null) ? configurationItem.getDefaultValue() : value;
        } else return configurationItem.getDefaultValue();
    }

    private void init(String propertyFile) {
        String baseName = getClass().getPackage().getName() + "." + propertyFile;
        bundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle(baseName);
    }
}
