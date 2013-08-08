package com.thenaglecode.algorithms.ejb.session.singletons;

import com.thenaglecode.core.util.propeties.*;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/2/13
 * Time: 4:53 PM
 * used to find the resources associated with these annotations
 */
@SuppressWarnings("UnusedDeclaration")
@Singleton
public class ResourceLocatorBean {

    private static final Logger logger = Logger.getLogger(ResourceLocatorBean.class.getName());

    @EJB
    private ConfigurationBean configurationBean;

    @Produces
    @PropertyBundleResource(name = "")
    private RefreshablePropertyResourceBundle loadPropertiesResource(InjectionPoint ip) throws IOException {
        logger.log(Level.FINE, "-- called PropertiesResourceBundle loader");
        PropertiesResource annotation = ip.getAnnotated().getAnnotation(PropertiesResource.class);
        return new RefreshablePropertyResourceBundle(annotation.name());
    }

    @Produces
    @PropertiesResource(name = "")
    private Properties loadProperties(InjectionPoint ip) throws IOException {
        logger.log(Level.FINE, "-- called PropertiesResource loader");
        PropertiesResource annotation = ip.getAnnotated().getAnnotation(PropertiesResource.class);
        String fileName = annotation.name();
        Properties props = null;

        URL url = null;
        url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        if(url != null){
            props = new Properties();
            try (InputStream in = url.openStream()) {
                props.load(in);
            }
        }
        return props;
    }

    @Produces
    @InjectConfiguration(subsystem = "", file = "")
    private ConfigurationManager loadConfigurationManager(InjectionPoint ip){
        logger.log(Level.FINE, "-- called Load ConfigurationManager");
        InjectConfiguration annotation = ip.getAnnotated().getAnnotation(InjectConfiguration.class);

        logger.log(Level.FINE, "-- subsystem=" + annotation.subsystem() + " file=" + annotation.file());
        ConfigurationManager manager = configurationBean.getConfigurationManager(annotation.subsystem(), annotation.file());
        if(manager == null) logger.log(Level.FINE, "could not find manager in cache, creating new one");
        return manager == null ? new ConfigurationManager(annotation.subsystem(), annotation.file()) : manager;
    }
}
