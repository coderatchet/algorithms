package com.thenaglecode.core;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/6/13
 * Time: 3:46 PM
 *
 * place all initialization code in here.
 */

@WebServlet(name = "startup", loadOnStartup = 1)
public class StartUp extends HttpServlet{

    @Override
    public void init() throws ServletException {
        super.init();
        //todo read the core configuration file and create configuration managers for every property file with that format
    }
}
