package com.thenaglecode;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    private static GridPane side1;
    private static GridPane side2;
    private static final TextArea output = new TextArea();
    private static String selectedSetting;

    public static GridPane render(){
        GridPane main = new GridPane();
        side1 = new GridPane();
        flipPane.getChildren().add(side1);
        try {
            configFolder = VFS.getManager().resolveFile("file://" + SettingsFileUtil.CONFIG_FILE_FOLDER);
            settings = SettingsFileUtil.getAllSettings();
        } catch (IOException e) {
            side1.add(new TextArea("Error! " + e.getMessage()), 0, 0);
        }
        side1.setHgap(10);
        side1.setVgap(10);
        Button addButton = new Button("add");
        side1.add(addButton, 0, 0);
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        Button removeButton = new Button("remove");
        side1.add(removeButton, 1, 0);

        final ListView<String> databaseConfigurationListView = new ListView<String>();
        List<String> listItems = new ArrayList<>();
        for(String settingName : settings.keySet()){
            if(selectedSetting != null) {
                databaseConfigurationListView.getSelectionModel().selectFirst();
                selectedSetting = settingName;
            }
            listItems.add(settingName);
        }
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
        databaseConfigurationListView.setPrefSize(100, 200);
        side1.add(databaseConfigurationListView, 0, 1, 4, 6);

        // add the buttons on the side
        Button install = new Button("Install");
        install.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(install, Priority.ALWAYS);
        Button uninstall = new Button("Uninstall");
        uninstall.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(uninstall, Priority.ALWAYS);
        Button start = new Button("Start");
        start.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(start, Priority.ALWAYS);
        Button stop = new Button("Stop");
        stop.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(stop, Priority.ALWAYS);
        Button edit = new Button("Edit");
        edit.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(edit, Priority.ALWAYS);

        //todo set on mouse clicked events

        edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(selectedSetting == null) return;
                final SettingsFile selectedSettingsFile = settings.get(selectedSetting);
                side2 = new GridPane();
                Button back = new Button("Back");
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        flipPane.getChildren().clear();
                        flipPane.getChildren().add(side1);
                    }
                });
                Button save = new Button("Save");
                final Label warning = new Label();
                save.setOnKeyTyped(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if(selectedSettingsFile.isValid()){
                            try {
                                FileObject toCreate = configFolder.resolveFile("./" + selectedSettingsFile.name + ".cnf");
                                if(toCreate.exists()){
                                    toCreate.delete();
                                }
                                toCreate.createFile();
                                BufferedWriter wr = new BufferedWriter(new FileWriter(toCreate.getName().getPath()));
                                wr.write(selectedSettingsFile.toString());
                            } catch (IOException e) {
                                warning.setText("Error saving file");
                                warning.setTextFill(Color.FIREBRICK);
                            }
                        } else {
                            warning.setText("Settings file not valid");
                            warning.setTextFill(Color.FIREBRICK);
                        }
                    }
                });

                side2.add(back, 0, 1);
                side2.add(save, 1, 1);
                side2.add(warning, 0, 2, 2, 1);
                try {
                    side2.add(SettingsFileUtil.render(selectedSettingsFile), 0, 0, 2, 1);
                    flipPane.getChildren().clear();
                    flipPane.getChildren().add(side2);
                } catch (FileSystemException e) {
                    side2.add(new Label("Could not read settings!"), 0, 0);
                }

            }
        });

        VBox options = new VBox();
        options.setMinWidth(80);
        options.setFillWidth(true);
        options.setSpacing(10);
        options.getChildren().addAll(install, uninstall, start, stop, edit);

        main.setHgap(10);
        main.setPadding(new Insets(15));
        main.add(flipPane, 0, 0);
        main.add(options, 1, 0);
        main.add(output, 2, 0);

        return main;
    }


}
