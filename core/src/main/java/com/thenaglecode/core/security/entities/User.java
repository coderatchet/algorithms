package com.thenaglecode.core.security.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/9/13
 * Time: 2:04 PM
 */

@Entity
@Table(name = "COUSER")
public class User implements Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERID")
    @NotNull
    long id;

    @NotNull
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Group> groups;

    /**
     * Getter for id
     * @return the user's unique database identifier
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id the user's unique database identifier
     */
    private void setId(long id) {
        this.id = id;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    /**
     * Setter for username
     * @param username the user's unique username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for username;
     * @return the user's unique username
     */
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return getUsername();
    }

    public User(){}

    public User(String username){
        this();
        this.username = username;
    }
}
