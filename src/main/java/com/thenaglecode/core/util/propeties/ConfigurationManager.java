package com.thenaglecode.core.util.propeties;

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
 * <tt>ConfigurationManager</tt> implementation. if a filename is not specified, the default of
 * <tt>"configuration"</tt> is used.
 */
public class ConfigurationManager implements Refreshable, Named {

    @NotNull
    private String subsystem;
    @NotNull
    private String propertyFile;
    @NotNull
    RefreshablePropertyResourceBundle bundle;
    @NotNull
    private static final String DEFAULT_CONFIG_BUDNLE_NAME = "configuration";

    /**
     * do not ctor
     */
    protected ConfigurationManager(){
        this(DEFAULT_CONFIG_BUDNLE_NAME);
    }

    protected ConfigurationManager(String subSystem){
        this(subSystem, DEFAULT_CONFIG_BUDNLE_NAME);
    }

    protected ConfigurationManager(String subSystem, String fileName){
        init(subSystem, fileName);
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

    protected void init(@NotNull String subSystem, @NotNull String fileName){
        setPropertyFile(fileName);
        String baseName = subSystem + "." + fileName;
        bundle = new RefreshablePropertyResourceBundle(baseName);
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
}
