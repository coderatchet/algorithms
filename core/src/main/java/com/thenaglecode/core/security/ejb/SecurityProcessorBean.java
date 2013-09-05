package com.thenaglecode.core.security.ejb;

import com.thenaglecode.core.security.Roles;
import com.thenaglecode.core.security.entities.Group;
import com.thenaglecode.core.security.entities.User;
import org.apache.commons.lang3.ObjectUtils;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Jared Nagle
 * Date: 14/08/13
 * Time: 9:59 PM
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless(name = "SecurityProcessorEJB")
@LocalBean
@DeclareRoles({Roles.ADMIN, Roles.SYSTEM})
public class SecurityProcessorBean {


    private static final Logger log = Logger.getLogger(SecurityProcessorBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Resource
    SessionContext context;

    public SecurityProcessorBean() {
    }

    /**
     * Create a user with the given name
     * @param username the name of the user
     * @return the newly created User
     */
    public User createUser(String username){
        User user = new User(username);
        em.persist(user);
        return user;
    }

    /**
     * Update the user's details
     * @param user the user object to update. if the id doesn't exist, one is created
     * @return the updated User
     */
    @RolesAllowed({Roles.ADMIN, Roles.SYSTEM, Roles.USER})
    public User updateUser(@NotNull User user){
        Principal currentPrincipal = context.getCallerPrincipal();
        if(!ObjectUtils.equals(currentPrincipal.getName(), user.getName())){
            //todo MessageFormat to create resource string.
            /*String warning = "User '" + currentPrincipal.getName() + "' Attempted to access"
            log.warning();*/
        }
        return em.merge(user);
    }



    /**
     * Group group
     * @param name the name of the group, must be unique
     * @return
     */
    @RolesAllowed(Roles.ADMIN)
    public Group createGroup(@NotNull String name){
        Group group = new Group(name);
        em.persist(group);
        return group;
    }
}
