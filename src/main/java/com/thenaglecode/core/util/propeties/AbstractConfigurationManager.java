package com.thenaglecode.core.util.propeties;

import com.thenaglecode.core.util.ConfigurationItem;
import com.thenaglecode.core.util.ConfigurationUtil;
import com.thenaglecode.core.util.Named;
import com.thenaglecode.core.util.Refreshable;

import javax.validation.constraints.NotNull;

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
public abstract class AbstractConfigurationManager implements Refreshable, Named {

    @NotNull
    private String propertyFile;
    @NotNull
    RefreshablePropertyResourceBundle bundle;
    @NotNull
    private static final String DEFAULT_CONFIG_BUDNLE_NAME = "configuration";

    /**
     * do not ctor
     */
    protected AbstractConfigurationManager(){
        this(DEFAULT_CONFIG_BUDNLE_NAME);
    }

    protected AbstractConfigurationManager(String propertyFile){
        init(propertyFile);
        ConfigurationUtil.registerConfigurationManager(this);
    }

    private String getPropertyFile() {
        return propertyFile;
    }

    private void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile;
    }

    public String getValue(String key){
        if(bundle != null){
            return bundle.getBundle().getString(key);
        } else {
            return null;
        }
    }

    public String getValue(ConfigurationItem configurationItem){
        String value = getValue(configurationItem.getKey());
        return (value == null) ? configurationItem.getDefaultValue() : value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    synchronized public void refresh() {
        bundle.refresh();
    }

    protected void init(){
        init(getPropertyFile());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getFullyQualifiedPropertyFileReference();
    }

    /**
     * returns the fully qualified name of the properties file without the .properties
     * @return the fully qualified name of the properties file without the .properties
     */
    private String getFullyQualifiedPropertyFileReference(){
        return getClass().getPackage().getName() + "." + propertyFile;
    }

    protected void init(String propertyFile) {
        setPropertyFile(propertyFile);
        String baseName = getFullyQualifiedPropertyFileReference();
        bundle = new RefreshablePropertyResourceBundle(baseName);
    }
}
