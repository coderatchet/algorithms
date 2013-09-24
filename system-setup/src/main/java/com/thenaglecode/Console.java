package com.thenaglecode;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Macindows
 * Date: 9/21/13
 * Time: 12:28 PM
 */
public class Console extends OutputStream {
    TextArea output;

    Console (TextArea ta){
        output = ta;
    }

    @Override
    public void write(int b) throws IOException {
        output.appendText(String.valueOf((char) b));
    }
}
