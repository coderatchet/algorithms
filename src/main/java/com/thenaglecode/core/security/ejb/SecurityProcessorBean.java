package com.thenaglecode.core.security.ejb;

import com.thenaglecode.core.security.entities.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: Jared Nagle
 * Date: 14/08/13
 * Time: 9:59 PM
 */
@Stateless(name = "SecurityProcessorEJB")
@LocalBean
public class SecurityProcessorBean {

    @PersistenceContext
    private EntityManager em;

    public SecurityProcessorBean() {
    }

    public User createUser(String username){
        User user = new User(username);
        em.persist(user);
        return user;
    }
}
