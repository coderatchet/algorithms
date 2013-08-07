package com.thenaglecode.core.util.propeties;

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
public class ResourceLocator {
    private static final Logger logger = Logger.getLogger(ResourceLocator.class.getName());

    @Produces
    @PropertyBundleResource(name = "")
    RefreshablePropertyResourceBundle loadPropertiesResource(InjectionPoint ip) throws IOException {
        logger.log(Level.FINE, "-- called PropertiesResourceBundle loader");
        PropertiesResource annotation = ip.getAnnotated().getAnnotation(PropertiesResource.class);
        return new RefreshablePropertyResourceBundle(annotation.name());
    }

    @Produces
    @PropertiesResource(name = "")
    Properties loadProperties(InjectionPoint ip) throws IOException {
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
    @Configuration(subsystem = "")
    ConfigurationManager loadConfigurationManager(InjectionPoint ip){
        logger.log(Level.FINE, "-- called Load ConfigurationManager");
        Configuration annotation = ip.getAnnotated().getAnnotation(Configuration.class);
        logger.log(Level.FINE, "-- subsystem=" + annotation.subsystem() + " file=" + annotation.file());
        return new ConfigurationManager(annotation.subsystem(), annotation.file());
    }

}
