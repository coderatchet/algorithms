package com.thenaglecode.jsf;

import com.thenaglecode.core.ui.Configuration;
import com.thenaglecode.core.util.propeties.ConfigurationManager;
import com.thenaglecode.core.util.propeties.InjectConfiguration;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/2/13
 * Time: 5:44 PM
 */

@ManagedBean(name = "constants", eager = true)
@ApplicationScoped
public class Constants implements Configuration{

    @InjectConfiguration(subsystem = "core.ui", file = "coreUI")
    private static ConfigurationManager uiConfigurationManager;

    public String getBOOTSTRAP_CDN_CSS(){
        return uiConfigurationManager.getValue(BOOTSTRAP_API_CDN_CSS);
    }

    public String getBOOTSTRAP_CDN_JAVASCRIPT(){
        return uiConfigurationManager.getValue(BOOTSTRAP_API_CDN_JAVASCRIPT);
    }

    public String getJQUERY_VERSION(){
        return uiConfigurationManager.getValue(JQUERY_VERSION);
    }
}
