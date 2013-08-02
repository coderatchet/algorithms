package com.thenaglecode.core.ui;

import com.thenaglecode.algorithms.util.propeties.AbstractConfigurationManager;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:08 PM
 */
public class CoreUIConfigurationManager extends AbstractConfigurationManager{

    private static CoreUIConfigurationManager instance = null;
    private static final String FILE_NAME = "coreUI";

    public static CoreUIConfigurationManager getInstance(){
        return instance == null ? instance = new CoreUIConfigurationManager() : instance;
    }

    /**
     * do not ctor
     */
    private CoreUIConfigurationManager(){super(FILE_NAME);}
}
