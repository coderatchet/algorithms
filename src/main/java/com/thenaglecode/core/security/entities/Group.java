package com.thenaglecode.core.security.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @GeneratedValue(strategy = GenerationType.TABLE)
    @NotNull
    long id;

    String name;
}
