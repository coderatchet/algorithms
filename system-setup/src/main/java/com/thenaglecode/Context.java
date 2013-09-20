package com.thenaglecode;

import javafx.scene.control.ListView;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Macindows
 * Date: 18/09/13
 * Time: 18:52
 */
public class Context {

    private static final Context INSTANCE = new Context();

    public static Context getInstance(){
        return INSTANCE;
    }


    private List<SettingsFile> settings = new ArrayList<>();
    private List<File> mySqlHomes = new ArrayList<>();

    public List<SettingsFile> getSettings(){
        if(settings == null) {
            settings = initializeSettings();
        }
        return settings;
    }

    public void refreshSettings(){
        settings = initializeSettings();
    }

    private List<SettingsFile> initializeSettings() {
        List<SettingsFile> settings = new ArrayList<>();
        try {
            FileObject file = VFS.getManager().resolveFile("file://" + SettingsFileUtil.CONFIG_FOLDER_ABSOLUTE_PATH);
            if(file.getChildren() != null){
                for(FileObject child : file.getChildren()){
                    if(child.getName().getBaseName().endsWith(".cnf")){
                        SettingsFile settingsFile = SettingsFile.fromFile(child);
                        settings.add(settingsFile);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return settings;
    }

    public List<File> getMySqlHomes() throws FileSystemException {
        return SettingsFileUtil.getAllMySqlHomes();
    }

    public void reloadMySqlHomes() throws FileSystemException {
        SettingsFileUtil.reloadAllMySqlHomes();
    }

    /**
     * do not ctor
     */
    private Context(){
    }
}
