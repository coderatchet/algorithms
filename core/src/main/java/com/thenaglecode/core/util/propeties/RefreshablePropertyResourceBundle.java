package com.thenaglecode.core.util.propeties;

import com.thenaglecode.core.Configuration;
import com.thenaglecode.core.util.Named;
import com.thenaglecode.core.util.Refreshable;

import javax.validation.constraints.NotNull;
import java.io.IOException;
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
        setDelegate(ResourceBundle.getBundle(propertyFileFQN, Locale.getDefault(), ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_PROPERTIES)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean refresh() {
        try {
            setDelegate(ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_PROPERTIES).newBundle(getPropertyFileFQN(), getLocale(), ResourceBundle.Control.FORMAT_PROPERTIES.get(0), ClassLoader.getSystemClassLoader(), true));
        } catch (IllegalAccessException | InstantiationException | IOException e) {
            return false;
        }
        return true;
    }

    /**
     * gets the property from the resource bundle, else returns the default value
     *
     * @param configurationItem
     * @return
     */
    @NotNull
    public String getString(@NotNull ConfigurationItem configurationItem) {
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

    /**
     * the wrapped ResourceBundle used in the
     *
     * @return
     */
    @NotNull
    public ResourceBundle getDelegate() {
        return bundle;
    }

    private void setDelegate(@NotNull ResourceBundle bundle) {
        this.bundle = bundle;
    }

    //-------------------------------------------------------------
    //      WRAPPER methods for bundle for ease of use            |
    //-------------------------------------------------------------

    public String getString(String key) {
        return getDelegate().getString(key);
    }

    public String[] getStringArray(String key) {
        return getDelegate().getStringArray(key);
    }

    public boolean containsKey(String key) {
        return getDelegate().containsKey(key);
    }

    public Enumeration<String> getKeys() {
        return getDelegate().getKeys();
    }

    public Object getObject(String key) {
        return getDelegate().getObject(key);
    }

    public Locale getLocale() {
        return getDelegate().getLocale();
    }

    public Set<String> keySet() {
        return getDelegate().keySet();
    }
}
