package com.thenaglecode;

import javafx.scene.control.ListView;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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

    public List<SettingsFile> getSettings() throws IOException {
        return SettingsFileUtil.getSettings();
    }

    public void reloadMySqlHomes() throws FileSystemException, URISyntaxException {
        SettingsFileUtil.reloadAllMySqlHomes();
    }

    /**
     * do not ctor
     */
    private Context(){
    }
}
