package com.thenaglecode;

import javafx.application.Platform;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Macindows
 * Date: 9/22/13
 * Time: 8:19 PM
 */
public class CommandHelper {
    public static void runCommandDisplayInOutput(OutputStream messageStream, String command, String[] arguments, String workingDirectory) throws IOException {
        Process p = Runtime.getRuntime().exec(new String[]{"cmd"});
        Thread runs = new Thread(new SyncPipe(p.getInputStream(), messageStream));
        try (PrintWriter pw = new PrintWriter(p.getOutputStream())) {
            pw.println("cd " + (workingDirectory == null ? "." : workingDirectory));
            pw.print(command);
            for (String arg : arguments) {
                pw.print(" " + arg);
            }
            pw.println(); //enter
            Platform.runLater(runs);
        }
    }

    public static void serviceMunchCommandOutput(String command, String[] arguments, String workingDirectory) throws IOException {
        Process p = Runtime.getRuntime().exec(new String[]{"cmd"});
        ServiceMuncher muncher = new ServiceMuncher(p.getInputStream());
        Thread runLater = new Thread(muncher);
        try (PrintWriter pw = new PrintWriter(p.getOutputStream())){
            pw.println("cd " + (workingDirectory == null ? "." : workingDirectory));
            pw.print(command);
            for(String arg : arguments){
                pw.print(" " + arg);
            }
            pw.println();
            Platform.runLater(runLater);
        }
    }
}
