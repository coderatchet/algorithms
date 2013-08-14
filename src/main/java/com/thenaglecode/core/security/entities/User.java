package com.thenaglecode.core.security.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Principal;

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
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "USERID")
    long id;

    @NotNull
    String username;
    String password;

    @ManyToMany
    @JoinTable(
            name = ""
    )

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

    public User(String username){
        this.username = username;
    }
}
