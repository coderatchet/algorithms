package com.thenaglecode;

import com.sun.istack.internal.NotNull;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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
    @NotNull private SimpleStringProperty nameProperty = new SimpleStringProperty();
    public String baseDir;
    public int port;
    public String socket = "mypipe1";

    final public String getName(){
        return nameProperty.get();
    }

    final public void setName(String name){
        nameProperty.setValue(name);
    }

    public SimpleStringProperty nameProperty(){
        return nameProperty;
    }

    public void addChangeListener(ChangeListener<String> changeListener){
        nameProperty.addListener(changeListener);
    }

    @Override
    public String toString() {
        return "["+ getName() +"]\n"
                + "basedir = " + baseDir + "\n" +
                "port = " + port + "\n" +
                "enable-named-pipe\n" +
                "socket = " + socket;
    }

    public static SettingsFile fromFile(@NotNull FileObject file) throws IOException {
        StringWriter wr = new StringWriter();
        IOUtils.copy(file.getContent().getInputStream(), wr, "UTF-8");
        String[] lines = StringUtils.split(wr.toString(), '\n');

        SettingsFile settingsFile = new SettingsFile();
        for(String line : lines) {
            line = line.trim();
            if(line.startsWith("[")){
                settingsFile.setName(line.substring(1, line.indexOf("]")));
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
                getName() != null && !getName().isEmpty()
                        && baseDir != null && !baseDir.isEmpty()
                        && port > 0
                        && socket != null && !socket.isEmpty();
    }
}
