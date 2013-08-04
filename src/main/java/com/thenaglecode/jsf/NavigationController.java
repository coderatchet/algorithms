package com.thenaglecode.jsf;

import javax.faces.bean.ManagedBean;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/31/13
 * Time: 2:48 PM
 */
@ManagedBean(name = "navigationController")
public class NavigationController {
    public String moveToPage1(){
        return "page1";
    }

    public String moveToRandomGeneratorPage(){
        return "random";
    }

    public String moveToHome(){
        return "home";
    }
}
