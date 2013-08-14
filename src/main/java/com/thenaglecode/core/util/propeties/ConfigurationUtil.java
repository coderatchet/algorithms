package com.thenaglecode.core.util.propeties;

import com.thenaglecode.core.Configuration;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/6/13
 * Time: 3:54 PM
 */
public class ConfigurationUtil implements Configuration {

    private static final Logger logger = Logger.getLogger(ConfigurationUtil.class.getName());

    @NotNull
    private final static Map<String, RefreshablePropertyResourceBundle> bundles = new HashMap<>();

    private static final ResourceBundle coreBundle = ResourceBundle.getBundle("com.thenaglecode.core.configuration");
    private static final String DEFAULT_BASE = coreBundle.getString(DEFAULT_BASE_PACKAGE_ID);
    private static final String DEFAULT_FILE = coreBundle.getString(DEFAULT_CONFIGURATION_FILENAME_ID);
    /**
     *
     */
    public static boolean refreshConfigurationManager(@NotNull String subsystem) {
        return refreshConfigurationManager(subsystem, DEFAULT_FILE);
    }

    public static boolean refreshConfigurationManager(@NotNull String subsystem, @NotNull String fileName) {
        RefreshablePropertyResourceBundle bundle = getRefreshablePropertyResourceBundle(subsystem, fileName);
        if (bundle == null) {
            return false;
        } else {
            bundle.refresh();
        }
        return true;
    }

    public static void refreshAllConfigurationManagers() {
        for (String key : bundles.keySet()) {
            bundles.get(key).refresh();
        }
    }

    public static RefreshablePropertyResourceBundle getRefreshablePropertyResourceBundle(@NotNull String subsystem) {
        return getRefreshablePropertyResourceBundle(subsystem, DEFAULT_FILE);
    }

    public static RefreshablePropertyResourceBundle getRefreshablePropertyResourceBundle(@NotNull String subsystem, @NotNull String fileName) {
        String baseName = DEFAULT_BASE + "." + subsystem + "." + fileName;
                RefreshablePropertyResourceBundle bundle = bundles.get(baseName);
        if(bundle == null){
            //lazy instantiation
            try {
                bundle = new RefreshablePropertyResourceBundle(baseName);
                registerRefreshablePropertyResourceBundle(bundle);
            } catch (MissingResourceException e){
                logger.warning("could not find properties file with ");
            }
        }
        return bundle;
    }

    public static void registerRefreshablePropertyResourceBundle(RefreshablePropertyResourceBundle bundle) {
        bundles.put(bundle.getName(), bundle);
    }
}