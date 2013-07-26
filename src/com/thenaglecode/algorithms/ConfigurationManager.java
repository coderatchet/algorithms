package com.thenaglecode.algorithms;

import com.thenaglecode.algorithms.util.AbstractConfigurationManager;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationManager extends AbstractConfigurationManager{

    private static ConfigurationManager instance = null;

    public static ConfigurationManager getInstance(){
        if(instance == null){
            instance = new ConfigurationManager();
        }
        return instance;
    }

    /**
     * do not ctor
     */
    private ConfigurationManager(){
        init();
    }

    private void init() {
        //load the properties
    }
}
