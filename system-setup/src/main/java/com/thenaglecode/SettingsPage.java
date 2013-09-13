package com.thenaglecode;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 10/09/13
 * Time: 1:56 PM
 * //todo implement
 */
public class SettingsPage {

    private static Map<String, SettingsFile> settings;
    private static FileObject configFolder;
    private static final StackPane flipPane = new StackPane();
    private static VBox side1;
    private static VBox side2;
    private static final TextArea output = new TextArea();
    private static String selectedSetting;

    static {
        output.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public static GridPane render(){
        GridPane main = new GridPane();
        side1 = new VBox();
        flipPane.getChildren().add(side1);
        try {
            configFolder = VFS.getManager().resolveFile("file://" + SettingsFileUtil.CONFIG_FOLDER_ABSOLUTE_PATH);
            settings = SettingsFileUtil.getAllSettings();
            if(!configFolder.exists()){
                throw new IOException("config folder is missing!");
            }
        } catch (IOException e) {
            side1.getChildren().add(new TextArea("Error! " + e.getMessage()));
            main.add(side1, 0, 0);
            return main;
        }
        side1.setSpacing(10);
        side1.setPadding(new Insets(10));
        HBox editOptions = new HBox();
        editOptions.setSpacing(10);
        Button addButton = new Button("add");
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SettingsFile settingsFile = new SettingsFile();
                doEditSettingsPage(settingsFile);
            }
        });

        Button removeButton = new Button("remove");
        removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // todo implement remove
                // check whether the service has been uninstalled yet
                // remove the configuration from the config folder
                // refresh the list
            }
        });

        editOptions.getChildren().addAll(addButton, removeButton);

        final ListView<String> databaseConfigurationListView = new ListView<>();
        List<String> listItems = new ArrayList<>();
        for(String settingName : settings.keySet()){
            if(selectedSetting != null) {
                databaseConfigurationListView.getSelectionModel().selectFirst();
                selectedSetting = settingName;
            }
            listItems.add(settingName);
        }
        databaseConfigurationListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        databaseConfigurationListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                        selectedSetting = s2;
                    }
                }
        );

        ObservableList<String> items = FXCollections.observableArrayList(listItems);
        databaseConfigurationListView.setItems(items);
        databaseConfigurationListView.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        databaseConfigurationListView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        databaseConfigurationListView.setPrefSize(150, 200);
        side1.getChildren().addAll(editOptions, databaseConfigurationListView);

        // add the buttons on the side
        Button install = new Button("Install");
        install.setMaxWidth(Double.MAX_VALUE);
        Button uninstall = new Button("Uninstall");
        uninstall.setMaxWidth(Double.MAX_VALUE);
        Button start = new Button("Start");
        start.setMaxWidth(Double.MAX_VALUE);
        Button stop = new Button("Stop");
        stop.setMaxWidth(Double.MAX_VALUE);
        Button edit = new Button("Edit");
        edit.setMaxWidth(Double.MAX_VALUE);

        //todo set on mouse clicked events

        edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(selectedSetting == null) return;
                final SettingsFile selectedSettingsFile = settings.get(selectedSetting);
                doEditSettingsPage(selectedSettingsFile);
            }
        });

        VBox options = new VBox();
        options.setMinWidth(80);
        options.setFillWidth(true);
        options.setSpacing(10);
        options.getChildren().addAll(install, uninstall, start, stop, edit);

        main.setHgap(10);
        main.setPadding(new Insets(10));
        main.add(flipPane, 0, 0);
        main.add(options, 1, 0);
        main.add(output, 2, 0);

        return main;
    }

    private static void doEditSettingsPage(final SettingsFile selectedSettingsFile) {
        side2 = new VBox();
        side2.setSpacing(10);
        side2.setPadding(new Insets(10));
        Button back = new Button("Back");
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                flipPane.getChildren().clear();
                flipPane.getChildren().add(side1);
                side2 = null;
            }
        });
        Button save = new Button("Save");
        final Label message = new Label();
        save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (selectedSettingsFile.isValid()) {
                    try {
                        FileObject toCreate = configFolder.resolveFile("./" + selectedSettingsFile.name + ".cnf");
                        if (toCreate.exists()) {
                            toCreate.delete();
                        }
                        toCreate.createFile();
                        BufferedWriter wr = new BufferedWriter(new FileWriter(toCreate.getName().getPath()));
                        wr.write(selectedSettingsFile.toString());
                        message.setText("Saved!");
                        message.setTextFill(Color.GREENYELLOW);
                    } catch (IOException e) {
                        message.setText("Error saving file");
                        message.setTextFill(Color.FIREBRICK);
                    }
                } else {
                    message.setText("Settings file not valid");
                    message.setTextFill(Color.FIREBRICK);
                }
            }
        });

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        try {
            buttons.getChildren().addAll(back, save);
            side2.getChildren().addAll(SettingsFileUtil.render(selectedSettingsFile), buttons, message);
            flipPane.getChildren().clear();
            flipPane.getChildren().add(side2);
        } catch (FileSystemException e) {
            Label errorMessage = new Label("Could not read settings!");
            errorMessage.setTextFill(Color.FIREBRICK);
            buttons.getChildren().add(back);
            side2.getChildren().addAll(errorMessage, buttons);
        }
    }

    private static void refreshList() throws IOException {
        settings = SettingsFileUtil.getAllSettings();
    }
}
