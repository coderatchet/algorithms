package com.thenaglecode.algorithms.ejb.session.singletons;

import com.thenaglecode.core.Configuration;
import com.thenaglecode.core.security.Roles;
import com.thenaglecode.core.util.propeties.ConfigurationManager;
import com.thenaglecode.core.util.propeties.InjectConfiguration;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/6/13
 * Time: 3:54 PM
 */
@DeclareRoles({Roles.SYSTEM})
@RolesAllowed(Roles.SYSTEM)
@Singleton
@LocalBean
@ApplicationScoped
public class ConfigurationBean implements Configuration {

    @Inject @InjectConfiguration(subsystem = "core", file = "configuration")
    private ConfigurationManager coreConfiguration;


    @NotNull
    private final Map<String, ConfigurationManager> configurationManagers = new HashMap<>();

    /**
     *
     */
    public boolean refreshConfigurationManager(@NotNull String subsystem){
        return refreshConfigurationManager(subsystem, coreConfiguration.getValue(DEFAULT_CONFIGURATION_FILENAME));
    }

    public boolean refreshConfigurationManager(@NotNull String subsystem, @NotNull String fileName){
        ConfigurationManager manager = configurationManagers.get(subsystem + "." + fileName);
        if(manager == null) return false;
        manager.refresh();
        return true;
    }

    public void refreshAllConfigurationManagers(){
        for(String key : configurationManagers.keySet()){
            configurationManagers.get(key).refresh();
        }
    }

    public ConfigurationManager getConfigurationManager(@NotNull String subsystem){
        return getConfigurationManager(subsystem, coreConfiguration.getValue(DEFAULT_CONFIGURATION_FILENAME));
    }

    public ConfigurationManager getConfigurationManager(@NotNull String subsystem, @NotNull String fileName){
        return configurationManagers.get(coreConfiguration.getValue(DEFAULT_BASE_PACKAGE)
                + "." + subsystem + "." + fileName);
    }

    public void registerConfigurationManager(ConfigurationManager configurationManager){
        configurationManagers.put(configurationManager.getName(), configurationManager);
    }
}
