package com.thenaglecode.core.util.propeties;

import com.thenaglecode.core.util.Refreshable;

import javax.validation.constraints.NotNull;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/6/13
 * Time: 12:23 PM
 * Wrapper class for the Resource bundle that allows the bundle to be refreshed.
 */
public class RefreshablePropertyResourceBundle implements Refreshable {

    private String propertyFileFQN;
    private ResourceBundle bundle;

    /**
     * Accepts a fully qualified file name with out the .properties extension and loads the bundle into the class.
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

    public String getPropertyFileFQN() {
        return propertyFileFQN;
    }

    private void setPropertyFileFQN(String propertyFileFQN) {
        this.propertyFileFQN = propertyFileFQN;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    private void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
}
