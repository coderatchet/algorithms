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
 * Time: 4:37 PM
 */
public class PropertiesResourceLocator {

    private static final Logger logger = Logger.getLogger(PropertiesResourceLocator.class.getName());

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
}
