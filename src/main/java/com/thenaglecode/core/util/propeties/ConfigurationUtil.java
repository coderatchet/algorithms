package com.thenaglecode.core.util.propeties;

import com.thenaglecode.core.util.propeties.ConfigurationManager;
import com.thenaglecode.core.util.propeties.PropertyBundleResource;
import com.thenaglecode.core.util.propeties.RefreshablePropertyResourceBundle;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.HashedMap;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/6/13
 * Time: 3:54 PM
 */
public class ConfigurationUtil {

    @NotNull
    private static final HashedMap configurationManagers = new HashedMap();

    /**
     * returns true if the configuration was successfully refreshed, false if the configuration was not found.
     * is equivalent to calling refreshConfigurationManager(fqn, "configuration");
     * @param fqn the fully qualified name of the ConfigurationManager e.g. com.thenaglecode.core
     * @return true if it was successfully refreshed, false if not.
     */
    public static boolean refreshConfigurationManager(@NotNull String fqn){
        return refreshConfigurationManager(fqn, "configuration");
    }

    public static boolean refreshConfigurationManager(@NotNull String subsystem, @NotNull String fileName){
        ConfigurationManager manager = (ConfigurationManager) configurationManagers.get(subsystem + "." + fileName);
        if(manager == null) return false;
        manager.refresh();
        return true;
    }

    public static void refreshAllConfigurationManagers(){
        MapIterator it = configurationManagers.mapIterator();
        while (it.hasNext()){
            ((ConfigurationManager)it.getValue()).refresh();
        }
    }

    public static void registerConfigurationManager(ConfigurationManager configurationManager){
        configurationManagers.put(configurationManager.getName(), configurationManager);
    }
}
