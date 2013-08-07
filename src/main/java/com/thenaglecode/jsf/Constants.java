package com.thenaglecode.jsf;

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
public class Constants {

    public String getBOOTSTRAP_CDN_CSS(){
        return uiManager.getValue(Configuration.BOOTSTRAP_API_CDN_CSS);
    }

    public String getBOOTSTRAP_CDN_JAVASCRIPT(){
        return uiManager.getValue(Configuration.BOOTSTRAP_API_CDN_JAVASCRIPT);
    }

    public String getJQUERY_VERSION(){
        return uiManager.getValue(Configuration.JQUERY_VERSION);
    }
}
