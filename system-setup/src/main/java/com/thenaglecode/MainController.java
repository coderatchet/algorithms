package com.thenaglecode;

import javafx.application.Platform;
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

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
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
    private Console console;
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
        } catch (FileSystemException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
        final Context context = Context.getInstance();
        console = new Console(output);
        PrintStream ps = new PrintStream(console, true);
        listAndOptionsPaneController.getSettingsListView().setCellFactory(new Callback<ListView<SettingsFile>, ListCell<SettingsFile>>() {
            @Override
            public ListCell<SettingsFile> call(ListView<SettingsFile> settingsFileListView) {
                return new ListCell<SettingsFile>() {
                    @Override
                    protected void updateItem(SettingsFile settingsFile, boolean empty) {
                        super.updateItem(settingsFile, empty);
                        if(!empty) setText(settingsFile == null ? "" : settingsFile.getName());
                    }
                };
            }
        });
        ObservableList<SettingsFile> list = null;
        try {
            list = FXCollections.observableList(context.getSettings());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listAndOptionsPaneController.getSettingsListView().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SettingsFile>() {
            @Override
            public void changed(ObservableValue<? extends SettingsFile> observableValue, SettingsFile settingsFile, SettingsFile settingsFile2) {
                if(settingsFile2 != null)
                    settingsFilePaneController.setSettingsFile(settingsFile2);
            }
        });
        listAndOptionsPaneController.getSettingsListView().setItems(list);
        listAndOptionsPaneController.getSettingsListView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listAndOptionsPaneController.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SettingsFile settingsFile = new SettingsFile();
                settingsFile.nameProperty().addListener(listAndOptionsPaneController);
                try {
                    context.getSettings().add(settingsFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                listAndOptionsPaneController.getSettingsListView().getSelectionModel().selectLast();
            }
        });
        listAndOptionsPaneController.getEchoButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    output.clear();
                    CommandHelper.runCommandDisplayInOutput(console, "echo", new String[]{"test"}, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        listAndOptionsPaneController.getInstallButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                refreshData();
                SettingsFile item = listAndOptionsPaneController.getSettingsListView().getSelectionModel().getSelectedItem();
                if(ServiceMuncher.services.get(item.getName()) != null){
                    output.clear();
                    output.setText("The Database " + item.getName() + " is already installed!");
                } else {
                    try {
                        CommandHelper.runCommandDisplayInOutput(console, "mysqld",
                                new String[] {
                                        "--install",
                                        item.getName(),
                                        "--defaults-file=\"" + SettingsFileUtil.CONFIG_FOLDER_ABSOLUTE_PATH + "\\" + item.getName() + ".cnf\""
                                },
                                item.baseDir + "\\bin\\");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        listAndOptionsPaneController.getRefreshButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                refreshData();
            }
        });
    }

    public void refreshData() {
        try {
            CommandHelper.serviceMunchCommandOutput("sc", new String[]{"queryex"}, ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
