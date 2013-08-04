package com.thenaglecode.core.util.propeties;

import com.thenaglecode.core.util.ConfigurationItem;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:12 PM
 *
 * extend this class to create a configuration manager for the residing package. the configuration
 * properties fileName specified in the constructor should also reside in the same package as the
 * <tt>AbstractConfigurationManager</tt> implementation. if a filename is not specified, the default of
 * <tt>"configuration"</tt> is used.
 */
public abstract class AbstractConfigurationManager {

    ResourceBundle bundle;
    private String propertyFile;
    private static final String DEFAULT_CONFIG_BUDNLE_NAME = "configuration";

    /**
     * do not ctor
     */
    protected AbstractConfigurationManager(){
        this(DEFAULT_CONFIG_BUDNLE_NAME);
    }

    protected AbstractConfigurationManager(String propertyFile){
        init(propertyFile);
    }

    private final String getPropertyFile() {
        return propertyFile;
    }

    private final void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile;
    }

    public String getValue(String key){
        if(bundle != null){
            return bundle.getString(key);
        } else {
            return null;
        }
    }

    public String getValue(ConfigurationItem configurationItem){
        String value = getValue(configurationItem.getKey());
        return (value == null) ? configurationItem.getDefaultValue() : value;
    }

    protected void init(){
        init(getPropertyFile());
    }

    protected void init(String propertyFile) {
        setPropertyFile(propertyFile);
        String baseName = getClass().getPackage().getName() + "." + propertyFile;

        bundle = PropertyResourceBundle.getBundle(baseName);
    }
}
