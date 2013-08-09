package com.thenaglecode.algorithms.ejb.session.singletons;


import com.thenaglecode.core.Configuration;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.http.HttpServlet;
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

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "---- Initializing App ----");
        logger.log(Level.FINE, "---- Initialization complete ----");
    }
}
