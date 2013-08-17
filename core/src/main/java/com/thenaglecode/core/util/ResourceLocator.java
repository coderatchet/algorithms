package com.thenaglecode.core.util;

import com.thenaglecode.core.Configuration;
import com.thenaglecode.core.util.propeties.PropertiesResource;
import com.thenaglecode.core.util.propeties.PropertyBundleResource;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
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
public class ResourceLocator {

    private static final Logger logger = Logger.getLogger(ResourceLocator.class.getName());
    private static final ResourceBundle bundle = PropertyResourceBundle.getBundle("com.thenaglecode.core.configuration");

    @Produces
    @PropertyBundleResource(subsystem = "", file = "")
    private ResourceBundle loadResourceBundle(InjectionPoint ip) throws IOException {
        logger.log(Level.FINE, "-- called PropertiesResourceBundle loader");
        PropertyBundleResource annotation = ip.getAnnotated().getAnnotation(PropertyBundleResource.class);
        logger.log(Level.FINE, "subsystem=" + annotation.subsystem() + " file=" + annotation.file());
        String baseName = bundle.getString(Configuration.DEFAULT_BASE_PACKAGE_ID) + "."
                + annotation.subsystem() + "." + annotation.file();
        try {
            logger.log(Level.FINE, "full name=" + baseName);
            return PropertyResourceBundle.getBundle(baseName);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    @Produces
    @PropertiesResource(name = "")
    private Properties loadProperties(InjectionPoint ip) throws IOException {
        logger.log(Level.FINE, "-- called PropertiesResource loader");
        PropertiesResource annotation = ip.getAnnotated().getAnnotation(PropertiesResource.class);
        String fileName = annotation.name();
        Properties props = null;

        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        if (url != null) {
            props = new Properties();
            try (InputStream in = url.openStream()) {
                props.load(in);
            }
        }
        return props;
    }
}
