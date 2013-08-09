package com.thenaglecode.jsf;

import com.thenaglecode.core.ui.Configuration;
import com.thenaglecode.core.util.propeties.PropertyBundleResource;
import com.thenaglecode.core.util.propeties.RefreshablePropertyResourceBundle;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/2/13
 * Time: 5:44 PM
 */

@ManagedBean(name = "constants", eager = true)
@ApplicationScoped
public class Constants implements Configuration{

    @Inject @PropertyBundleResource(subsystem = "core.ui", file = "coreUI")
    RefreshablePropertyResourceBundle uiBundle;

    public String getBOOTSTRAP_CDN_CSS(){
        return uiBundle.getString(BOOTSTRAP_API_CDN_CSS);
    }

    public String getBOOTSTRAP_CDN_JAVASCRIPT(){
        return uiBundle.getString(BOOTSTRAP_API_CDN_JAVASCRIPT);
    }

    public String getJQUERY_VERSION(){
        return uiBundle.getString(JQUERY_VERSION);
    }
}
