package com.thenaglecode;

import com.sun.istack.internal.NotNull;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 10/09/13
 * Time: 11:36 AM
 * //todo implement
 */
public class SettingsFile {
    public String name;
    public String baseDir;
    public int port;
    public String socket = "mypipe1";

    @Override
    public String toString() {
        return "[mysqld]\n"
                + "basedir = " + baseDir + "\n" +
                "port = " + port + "\n" +
                "enable-named-pipe\n" +
                "socket = " + socket;
    }

    public static SettingsFile fromFile(@NotNull File file) throws IOException {
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);
        String currentLine = br.readLine();
        SettingsFile settingsFile = new SettingsFile();
        while (currentLine != null) {
            currentLine = currentLine.trim();
            if(currentLine.startsWith("[")){
                settingsFile.name = currentLine.substring(1, currentLine.indexOf("]"));
            }
            String[] split = currentLine.split("=");
            if (split.length == 2) {
                switch (split[0].trim()) {
                    case "basedir":
                        settingsFile.baseDir = split[1].trim();
                        break;
                    case "port":
                        settingsFile.port = Integer.valueOf(split[1].trim());
                        break;
                    case "socket":
                        settingsFile.socket = split[1].trim();
                        break;
                }
            }
            currentLine = br.readLine();
        }
        return settingsFile.isValid() ? settingsFile : null;
    }


    public boolean isValid() {
        return
                name != null && !name.isEmpty()
                        && baseDir != null && !baseDir.isEmpty()
                        && port > 0
                        && socket != null && !socket.isEmpty();
    }
}
