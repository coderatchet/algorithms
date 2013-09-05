package com.thenaglecode.core.util.propeties;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 9/3/13
 * Time: 5:11 PM
 *
 * stores all the producers for core functionality. e.g. ResourceBundles.
 *
 * @see Bundle
 */
public class CoreProducer {

    public static final String DEFAULT_CONFIGURATION_BUNDLE_NAME = "configuration";

    /**
     * produces a ResourceBundle using the details provided. each bundle name is an extension of the
     * root prefix of <code>'{@value GLOBAL#THENAGLECODE_ROOT_URL}'</code>
     *
     * @param ip the InjectionPoint that the annotation applies to. i.e. the field to be injected into.
     * @return a ResourceBundle if found or null if none found.
     */
    @Produces
    @Core
    private PropertyResourceBundle produceResourceBundle(InjectionPoint ip) {
        Bundle b;
        if (ip.getAnnotated().isAnnotationPresent(Bundle.class)) {
            b = ip.getAnnotated().getAnnotation(Bundle.class);
        } else return null;
        Class container = ip.getMember().getDeclaringClass();
        String subsystem = b.subsystem() != null && !b.subsystem().equals("") ? b.subsystem() : container.getPackage().getName();
        if(subsystem.startsWith(GLOBAL.THENAGLECODE_ROOT_URL))
            subsystem = subsystem.substring(GLOBAL.THENAGLECODE_ROOT_URL.length() + 1);
        String name = b.name() != null && !b.name().equals("") ? b.name() : DEFAULT_CONFIGURATION_BUNDLE_NAME;

        String baseName = GLOBAL.THENAGLECODE_ROOT_URL + "." + subsystem + "." + name;

        Locale locale;
        List<String> localPieces = new ArrayList<>();
        if (b.locale().equals("") || b.locale().equals("default")) {
            locale = Locale.getDefault();
        } else {
            String[] lpieces = b.locale().split("_", 3);
            switch (lpieces.length) {
                case 0:
                    locale = Locale.getDefault();
                    break;
                case 1:
                    locale = new Locale(lpieces[0]);
                    break;
                case 2:
                    locale = new Locale(lpieces[0], lpieces[1]);
                    break;
                case 3:
                    locale = new Locale(lpieces[0], lpieces[1], lpieces[2]);
                    break;
                default:
                    locale = Locale.getDefault();
                    break;
            }
        }
        baseName = baseName.replace(".", "/");
        return (PropertyResourceBundle) PropertyResourceBundle.getBundle(baseName, locale);
    }

    @Produces
    @Core
    private RefreshablePropertyResourceBundle produceRefreshablePropertyResourceBundle (InjectionPoint ip){
        Bundle b;
        if (ip.getAnnotated().isAnnotationPresent(Bundle.class)) {
            b = ip.getAnnotated().getAnnotation(Bundle.class);
        } else return null;
        Class container = ip.getMember().getDeclaringClass();
        String subsystem = b.subsystem() != null && !b.subsystem().equals("") ? b.subsystem() : container.getPackage().getName();
        if(subsystem.startsWith(GLOBAL.THENAGLECODE_ROOT_URL))
            subsystem = subsystem.substring(GLOBAL.THENAGLECODE_ROOT_URL.length() + 1);
        String name = b.name() != null && !b.name().equals("") ? b.name() : DEFAULT_CONFIGURATION_BUNDLE_NAME;
        String baseName = GLOBAL.THENAGLECODE_ROOT_URL + "." + subsystem;
        return ConfigurationUtil.getRefreshablePropertyResourceBundle(subsystem, name);
    }
}
