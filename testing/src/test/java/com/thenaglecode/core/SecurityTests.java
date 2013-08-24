package com.thenaglecode.core;

import com.thenaglecode.core.security.ejb.SecurityProcessorBean;
import com.thenaglecode.core.security.entities.Group;
import com.thenaglecode.core.security.entities.User;
import com.thenaglecode.core.util.TransactionUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/16/13
 * Time: 1:16 PM
 *
 * tests the usexzcr management functionality.
 */
@RunWith(Arquillian.class)
public class SecurityTests {

    String[] USER_NAMES = new String[]{
            "jarednagle@gmail.com",
            "test@test.com"
    };

    String[] GROUP_NAMES = new String[]{
            "admin",
            "user"
    };

    private static final Set<User> USERS = new HashSet<>();

    @EJB
    TransactionUtil txUtil;

    @EJB
    SecurityProcessorBean securityProcessor;

    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(TransactionUtil.class, Group.class, User.class)
                .addAsResource("tests/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void init() {
        deleteAllUsers();
        insertTestUsersData();
    }

    private void deleteAllUsers() {
        txUtil.wrapInTx(new TransactionUtil.Invokable() {
            @Override
            public void invoke() {
                em.createQuery("delete from User").executeUpdate();
            }
        });
    }

    private void insertTestUsersData() {
        txUtil.wrapInTx(new TransactionUtil.Invokable() {
            @Override
            public void invoke() {
                User[] users = new User[USER_NAMES.length];
                int i = 0;
                for (String username : USER_NAMES) {
                    users[i++] = new User(username);
                }
                for (User user : users) {
                    em.persist(user);
                    USERS.add(user);
                }
            }
        });
    }

    @Test
    public void user_data_should_exist() {
        txUtil.wrapInTx(new TransactionUtil.Invokable() {
            @Override
            public void invoke() {
                for (String username : USER_NAMES) {
                    User u = (User) em.createQuery("select u from User u where u.username = :d")
                            .setParameter("d", username)
                            .getSingleResult();
                    assertNotNull(u);
                }
            }
        });
    }
}
