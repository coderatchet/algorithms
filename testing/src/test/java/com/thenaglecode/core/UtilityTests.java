package com.thenaglecode.core;

import com.thenaglecode.core.util.Named;
import com.thenaglecode.core.util.Refreshable;
import com.thenaglecode.core.util.propeties.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.PropertyResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 9/4/13
 * Time: 12:25 PM
 */
@RunWith(Arquillian.class)
public class UtilityTests {

    private static final Logger log = Logger.getLogger(UtilityTests.class.getName());

    @Deployment
    public static JavaArchive createDeployment(){
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addAsResource("com/thenaglecode/core/configuration.properties")
                .addAsResource("com/thenaglecode/test/test.properties")
                        .addAsResource("test2.properties")
                        .addClasses(
                                CoreProducer.class,
                                Bundle.class,
                                RefreshablePropertyResourceBundle.class,
                                Refreshable.class,
                                Named.class,
                                Configuration.class,
                                ConfigurationUtil.class,
                                ConfigurationItem.class
                        )
                        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }


    @Inject
    @Bundle
    private PropertyResourceBundle bundle;

    /**
     * same as bundle 1
     */
    @Inject
    @Bundle(subsystem = "core")
    private PropertyResourceBundle bundle2;

    @Inject
    @Bundle(subsystem = "test", name = "test")
    private PropertyResourceBundle bundle3;

    @Test
    public void testCoreProducer(){
        log.log(Level.INFO, "testing producer for bundle resources");
        assertEquals(GLOBAL.THENAGLECODE_ROOT_URL, bundle.getString("default.base.package"));
        assertEquals(GLOBAL.THENAGLECODE_ROOT_URL, bundle2.getString("default.base.package"));
        assertEquals("billy", bundle3.getString("silly"));
    }

    @Inject
    @Bundle
    @Core
    private RefreshablePropertyResourceBundle refreshableBundle;

    @Test
    public void testRefreshablePropertyResourceBundle(){
        assertEquals(GLOBAL.THENAGLECODE_ROOT_URL, refreshableBundle.getString("default.base.package"));
    }
}
