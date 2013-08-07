package com.thenaglecode.algorithms.ejb.session.singletons;


import com.thenaglecode.core.util.propeties.PropertyBundleResource;
import com.thenaglecode.core.util.propeties.RefreshablePropertyResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/6/13
 * Time: 3:46 PM
 *
 * place all initialization code in here.
 */

@Singleton
@Startup
public class StartUp extends HttpServlet{

    private static final Logger log = Logger.getLogger(StartUp.class.getName());

    @PropertyBundleResource(name = "com.thenaglecode.core.configuration")
    RefreshablePropertyResourceBundle coreConfiguration;

    //todo setup web security with ldap server hava admin access to this method. (i.e. the system)
    @PostConstruct
    public void init() {
        log.log(Level.INFO, "---- Initialising App ----");
        log.log(Level.FINE, "loading configuration managers");



        //todo read the core configuration file and create configuration managers for every property file with that format
    }

    private void initialiseConfigurationManagers(){
        String[] configFileNames = coreConfiguration.getBundle().getStringArray("configuration.filenames");
    }
}
