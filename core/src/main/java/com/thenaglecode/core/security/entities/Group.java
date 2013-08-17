package com.thenaglecode.core.security.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Jared Nagle
 * Date: 14/08/13
 * Time: 9:08 PM
 */

@Entity
@Table(name = "COGROUP")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUPID")
    @NotNull
    long id;

    @NotNull
    String name;

    @ManyToMany
    @JoinTable(
            name = "COGROUPUSERLINK",
            joinColumns = {@JoinColumn(name="GROUPID")},
            inverseJoinColumns = {@JoinColumn(name = "USERID")}
    )
    private Set<User> users;

    public Group(String name) {
        setName(name);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    private void setUsers(Set<User> users) {
        this.users = users;
    }
}
