package com.thenaglecode.core.util;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/6/13
 * Time: 12:21 PM
 * This interface defines a class whose state may be refreshed. an example would be a class that manages
 * a list of properties contained in a properties file.
 */
public interface Refreshable {

    /**
     * Refresh this object's state.
     */
    public void refresh();
}
