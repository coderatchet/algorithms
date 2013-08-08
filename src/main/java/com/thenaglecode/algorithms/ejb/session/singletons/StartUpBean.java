package com.thenaglecode.algorithms.ejb.session.singletons;


import com.thenaglecode.core.Configuration;
import com.thenaglecode.core.util.propeties.ConfigurationManager;
import com.thenaglecode.core.util.propeties.InjectConfiguration;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.http.HttpServlet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/6/13
 * Time: 3:46 PM
 * <p/>
 * place all initialization code that needs to run at the startup of the server here
 */

@Singleton
@Startup
public class StartUpBean extends HttpServlet implements Configuration {

    private static final Logger logger = Logger.getLogger(StartUpBean.class.getName());

    @InjectConfiguration(subsystem = "core")
    private static final ConfigurationManager coreConfiguration = null;
    public static final String[] DEFAULT_FILENAMES = coreConfiguration.getValueArray(DEFAULT_CONFIGURATION_FILENAMES_ID);
    public static final String DEFAULT_BASE = coreConfiguration.getValue(DEFAULT_BASE_PACKAGE);

    @EJB
    private ConfigurationBean configurationBean;

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "---- Initializing App ----");

        logger.log(Level.FINE, "loading configuration managers");
        initialiseConfigurationManagers();

        logger.log(Level.FINE, "---- Initialization complete ----");
    }

    /**
     * finds every configuration file in the project and creates a configuration manager for it if
     * it is in the array
     */
    private void initialiseConfigurationManagers() {
        logger.log(Level.FINE, "finding config files and registering config managers");
        for (Package p : Package.getPackages()) {
            if (p.getName().startsWith(DEFAULT_BASE + ".")) {
                logger.log(Level.FINER, " -found valid package: " + p.getName());
                for (String configFileName : DEFAULT_FILENAMES) {
                    String fileName = p.getName() + "." + configFileName;
                    try {
                        ResourceBundle.getBundle(fileName);
                        logger.log(Level.FINER, "found config file: " + fileName + " registering new configuration manager");
                        String subsystem = p.getName().substring(DEFAULT_BASE.length() + 1); // +1 accounts for the '.'
                        logger.log(Level.FINER, "subsystem=" + subsystem);
                        ConfigurationManager configurationManager = new ConfigurationManager(subsystem, configFileName);
                        configurationBean.registerConfigurationManager(configurationManager);
                    } catch (MissingResourceException ignore) {
                    }
                }
            }
        }
    }
}
