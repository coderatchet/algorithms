package com.thenaglecode;

import com.sun.istack.internal.NotNull;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;

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

    public static SettingsFile fromFile(@NotNull FileObject file) throws IOException {
        StringWriter wr = new StringWriter();
        IOUtils.copy(file.getContent().getInputStream(), wr, "UTF-8");
        String[] lines = wr.toString().split("\n");

        SettingsFile settingsFile = new SettingsFile();
        for(String line : lines) {
            line = line.trim();
            if(line.startsWith("[")){
                settingsFile.name = line.substring(1, line.indexOf("]"));
            }
            String[] split = line.split("=");
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
