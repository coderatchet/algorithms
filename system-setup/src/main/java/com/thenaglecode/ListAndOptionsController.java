package com.thenaglecode;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Macindows
 * Date: 19/09/13
 * Time: 08:33
 */
public class ListAndOptionsController implements ChangeListener<String> {
    @FXML private Button addButton;
    @FXML private Button removeButton;
    @FXML private Button installButton;
    @FXML private Button uninstallButton;
    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private Button refreshButton;
    @FXML private Button echoButton;
    @FXML ListView<SettingsFile> settingsListView;

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getInstallButton() {
        return installButton;
    }

    public Button getUninstallButton() {
        return uninstallButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public ListView<SettingsFile> getSettingsListView() {
        return settingsListView;
    }

    public Button getRefreshButton() {
        return refreshButton;
    }

    public Button getEchoButton() {
        return echoButton;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
        ObservableList<SettingsFile> items = getSettingsListView().<SettingsFile>getItems();
        getSettingsListView().setItems(null);
        getSettingsListView().setItems(items);
    }
}
