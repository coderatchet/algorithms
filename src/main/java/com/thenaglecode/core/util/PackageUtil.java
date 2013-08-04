package com.thenaglecode.core.util;

import com.thenaglecode.algorithms.Configuration;
import com.thenaglecode.algorithms.ConfigurationManager;
import com.thenaglecode.core.util.propeties.PropertyBundleResource;

import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/2/13
 * Time: 4:44 PM
 *
 * Class for package related operations such as retrieving the fully qualified name of a subsystem.
 */
public class PackageUtil {
    @PropertyBundleResource(name = "com.thenaglecode.configuration")
    private static ResourceBundle bundle;

    public static String buildStandardUrl(String subSystemName) {
            String base = bundle.getString(ConfigurationManager.getInstance().getValue(Configuration.STANDARD_BASE_PACKAGE));
            return base + "." + subSystemName;
    }
}
