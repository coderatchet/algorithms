package com.thenaglecode;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.apache.commons.vfs2.FileSystemException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Macindows
 * Date: 18/09/13
 * Time: 17:20
 */
public class MainController implements Initializable{
    @FXML
    public TextArea output;
    @FXML
    private GridPane settingsFilePane;
    @FXML
    private SettingsFileController settingsFilePaneController;
    @FXML
    GridPane listAndOptionsPane;
    @FXML
    private ListAndOptionsController listAndOptionsPaneController;

    public MainController() {
        initialize();
    }

    public void initialize() {
        Context currentContext = Context.getInstance();
        try {
            SettingsFileUtil.getAllMySqlHomes();
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
        final Context context = Context.getInstance();
        listAndOptionsPaneController.getSettingsListView().setCellFactory(new Callback<ListView<SettingsFile>, ListCell<SettingsFile>>() {
            @Override
            public ListCell<SettingsFile> call(ListView<SettingsFile> settingsFileListView) {
                return new ListCell<SettingsFile>() {
                    @Override
                    protected void updateItem(SettingsFile settingsFile, boolean empty) {
                        super.updateItem(settingsFile, empty);
                        if(!empty) setText(settingsFile.name == null ? "" : settingsFile.name);
                    }
                };
            }
        });
        ObservableList<SettingsFile> list = FXCollections.observableList(context.getSettings());
        listAndOptionsPaneController.getSettingsListView().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SettingsFile>() {
            @Override
            public void changed(ObservableValue<? extends SettingsFile> observableValue, SettingsFile settingsFile, SettingsFile settingsFile2) {
                settingsFilePaneController.setSettingsFile(settingsFile2);
            }
        });
        listAndOptionsPaneController.getSettingsListView().setItems(list);
        listAndOptionsPaneController.getSettingsListView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listAndOptionsPaneController.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                context.getSettings().add(new SettingsFile());
                listAndOptionsPaneController.getSettingsListView().getSelectionModel().selectLast();
            }
        });
    }
}
