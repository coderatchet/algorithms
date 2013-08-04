package com.thenaglecode.algorithms;

import com.thenaglecode.core.util.propeties.AbstractConfigurationManager;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:08 PM
 */
public class ConfigurationManager extends AbstractConfigurationManager{

    private static ConfigurationManager instance = null;

    public static ConfigurationManager getInstance(){
        return instance == null ? instance = new ConfigurationManager() : instance;
    }

    /**
     * do not ctor
     */
    private ConfigurationManager(){}
}
