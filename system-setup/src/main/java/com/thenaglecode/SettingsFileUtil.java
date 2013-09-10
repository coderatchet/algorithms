package com.thenaglecode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 9/09/13
 * Time: 5:16 PM
 * //todo implement
 */
public class SettingsFileUtil {

    public static final String CONFIG_FILE_FOLDER = "com/thenaglecode/config";

    public static Map<String, SettingsFile> settings = new HashMap<>();

    public static void reloadSettings() throws IOException {
        settings.clear();
        FileObject folder = VFS.getManager().resolveFile("file://" + CONFIG_FILE_FOLDER);
        if (folder.getType().hasChildren()) {
            for (FileObject file : folder.getChildren()) {
                if(file.getName().getBaseName().endsWith(".cnf")){
                    SettingsFile setting = SettingsFile.fromFile(new File(file.getName().getPath()));
                    settings.put(setting.name, setting);
                }
            }
        }
    }

    public static Map<String, SettingsFile> getAllSettings() throws IOException {
        reloadSettings();
        return settings;
    }

    public static Map<String, FileObject> getAllMysqlHomes() throws FileSystemException {
        Map<String, FileObject> files = new HashMap<>();
        String BASE_DIR = "C:/Program Files";
        FileObject programFiles = VFS.getManager().resolveFile("file://" + BASE_DIR);
        for (FileObject file : programFiles.getChildren()) {
            if (file.getName().getBaseName().startsWith("mysql")
                    && file.getType().hasChildren()) {
                files.put(file.getName().getBaseName(), file);
            }
        }
        return files;
    }

    public static Node render(final SettingsFile settings) throws FileSystemException {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        int row = 0;

        Label mysqlInstall = new Label("Mysql version:");
        grid.add(mysqlInstall, 0, row);

        ArrayList<String> listOfInstalls = new ArrayList<>();
        for (String name : getAllMysqlHomes().keySet()) {
            listOfInstalls.add(name);
        }
        ObservableList<String> mysqlInstallOptions =
                FXCollections.observableArrayList(
                        listOfInstalls
                );
        ComboBox<String> comboBox = new ComboBox<>(mysqlInstallOptions);
        grid.add(comboBox, 1, row);

        Label name = new Label("Name");
        grid.add(name, 0, ++row);

        final TextField nameTxt = new TextField(settings.name);
        grid.add(nameTxt, 1, row);
        nameTxt.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                settings.name = nameTxt.getText();
            }
        });

        Label port = new Label("Port");
        grid.add(port, 0, ++row);

        final TextField portTxt = new TextField(String.valueOf(settings.port));
        portTxt.lengthProperty().addListener(new NumericOnlyTextFieldChangeListener(portTxt));
        grid.add(portTxt, 1, row);
        portTxt.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                try {
                    settings.port = Integer.valueOf(portTxt.getText());
                } catch (NumberFormatException ignore){
                }
            }
        });

        Label socket = new Label("Socket");
        grid.add(socket, 0, ++row);

        final TextField socketTxt = new TextField(settings.socket);
        grid.add(socketTxt, 1, row);
        socketTxt.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                settings.socket = socketTxt.getText();
            }
        });

        grid.setVgap(10);
        grid.setHgap(10);

        return grid;
    }
}
