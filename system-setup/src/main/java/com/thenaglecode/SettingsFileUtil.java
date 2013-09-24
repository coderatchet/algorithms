package com.thenaglecode;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.provider.local.LocalFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 9/09/13
 * Time: 5:16 PM
 * //todo implement
 */
public class SettingsFileUtil {

    public static final String CONFIG_FILE_FOLDER_RESOURCE = "com/thenaglecode/config";
    public static String CONFIG_FOLDER_ABSOLUTE_PATH;

    private static List<File> mySqlHomeFolders;

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url1 = classLoader.getResource("");
        if (url1 != null) {
            File file = new File(url1.getPath() + CONFIG_FILE_FOLDER_RESOURCE);
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.mkdirs();
            }
            CONFIG_FOLDER_ABSOLUTE_PATH = file.getAbsolutePath();
        }
    }

    public static List<SettingsFile> settings;

    public static void reloadSettings() throws IOException {
        if(settings != null) settings.clear();
        else settings = new ArrayList<>();
        FileObject folder = VFS.getManager().resolveFile("file://" + CONFIG_FOLDER_ABSOLUTE_PATH);
        if (folder.getType().hasChildren()) {
            for (FileObject file : folder.getChildren()) {
                if (file.getName().getBaseName().endsWith(".cnf")) {
                    SettingsFile setting = SettingsFile.fromFile(file);
                    settings.add(setting);
                }
            }
        }
    }

    public static List<SettingsFile> getSettings() throws IOException {
        if(settings == null) {
            reloadSettings();
        }
        return settings;
    }

    public static List<File> getAllMySqlHomes() throws FileSystemException, URISyntaxException {
        if(mySqlHomeFolders == null) {
            reloadAllMySqlHomes();
        }
        return mySqlHomeFolders;
    }

    public static void reloadAllMySqlHomes() throws FileSystemException, URISyntaxException {
        if(mySqlHomeFolders == null){
            mySqlHomeFolders = new ArrayList<>();
        } else {
            mySqlHomeFolders.clear();
        }
        String BASE_DIR = "C:/Program Files";
        FileObject programFiles = VFS.getManager().resolveFile("file://" + BASE_DIR);
        for (FileObject file : programFiles.getChildren()) {
            if (file.getName().getBaseName().startsWith("mysql")
                    && file.getType().hasChildren()) {
                String justTheGoods = file.getName().getURI();
                justTheGoods = justTheGoods.substring("file:///".length());
                mySqlHomeFolders.add(new File(justTheGoods));
            }
        }
    }
}
