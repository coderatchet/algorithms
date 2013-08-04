package com.thenaglecode.core.util.propeties;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/2/13
 * Time: 4:53 PM
 */
public class PropertiesResourceBundleLocator {

    private static final Logger logger = Logger.getLogger(PropertiesResourceBundleLocator.class.getName());

    @Produces
    @PropertyBundleResource(name = "")
    ResourceBundle loadPropertiesResource(InjectionPoint ip) throws IOException {
        logger.log(Level.FINE, "-- called PropertiesResourceBundle loader");
        PropertiesResource annotation = ip.getAnnotated().getAnnotation(PropertiesResource.class);
        return PropertyResourceBundle.getBundle(annotation.name());
    }
}
