package com.thenaglecode.core.util.propeties;

import com.thenaglecode.core.Configuration;
import com.thenaglecode.core.util.Named;
import com.thenaglecode.core.util.Refreshable;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/6/13
 * Time: 12:23 PM
 * Wrapper class for the Resource bundle that allows the bundle to be refreshed
 */
public class RefreshablePropertyResourceBundle implements Refreshable, Named, Configuration {

    private static final ResourceBundle coreBundle = ResourceBundle.getBundle("com.thenaglecode.core.configuration");

    @NotNull
    private String propertyFileFQN;
    @NotNull
    private ResourceBundle bundle;

    /**
     * Accepts a fully qualified file name with out the .properties extension and loads the bundle into the class.
     *
     * @param propertyFileFQN the fully qualified name of the resource.
     * @throws MissingResourceException if the given properties file could not be found.
     */
    public RefreshablePropertyResourceBundle(@NotNull String propertyFileFQN) throws MissingResourceException {
        setPropertyFileFQN(propertyFileFQN);
        setBundle(PropertyResourceBundle.getBundle(propertyFileFQN));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        setBundle(PropertyResourceBundle.getBundle(getPropertyFileFQN()));
    }

    /**
     * gets the property from the resource bundle, else returns the default value
     * @param configurationItem
     * @return
     */
    @NotNull
    public String getString(@NotNull ConfigurationItem configurationItem){
        String value = getString(configurationItem.getKey());
        return (value == null) ? configurationItem.getDefaultValue() : value;
    }

    @Override
    public String getName() {
        return getPropertyFileFQN();
    }

    //-------------------------------------------------------------
    //                      GETTERS/SETTERS                       |
    //-------------------------------------------------------------

    public String getPropertyFileFQN() {
        return propertyFileFQN;
    }

    private void setPropertyFileFQN(String propertyFileFQN) {
        this.propertyFileFQN = propertyFileFQN;
    }

    @NotNull
    private ResourceBundle getBundle() {
        return bundle;
    }

    private void setBundle(@NotNull ResourceBundle bundle) {
        this.bundle = bundle;
    }

    //-------------------------------------------------------------
    //      WRAPPER methods for bundle for ease of use            |
    //-------------------------------------------------------------

    public String getString(String key) {
        return getBundle().getString(key);
    }

    public String[] getStringArray(String key) {
        return getBundle().getStringArray(key);
    }

    public boolean containsKey(String key) {
        return getBundle().containsKey(key);
    }

    public Enumeration<String> getKeys() {
        return getBundle().getKeys();
    }

    public Object getObject(String key) {
        return getBundle().getObject(key);
    }

    public Locale getLocale() {
        return getBundle().getLocale();
    }

    public Set<String> keySet() {
        return getBundle().keySet();
    }
}
