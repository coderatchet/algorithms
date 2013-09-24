package com.thenaglecode;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Macindows
 * Date: 9/23/13
 * Time: 8:35 AM
 */
public interface Muncher extends Runnable {
    void munch() throws IOException;
}
