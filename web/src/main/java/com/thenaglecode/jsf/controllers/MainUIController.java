package com.thenaglecode.jsf.controllers;

import javax.faces.bean.ManagedBean;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/31/13
 * Time: 1:58 PM
 */

@ManagedBean(name = "main")
public class MainUIController {

    private String magic;

    public String getHelloWorld(){
        return "hello world";
    }
}
