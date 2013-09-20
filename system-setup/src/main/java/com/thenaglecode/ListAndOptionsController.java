package com.thenaglecode;

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
public class ListAndOptionsController {
    @FXML public Button addButton;
    @FXML public Button removeButton;
    @FXML public Button installButton;
    @FXML public Button uninstallButton;
    @FXML public Button startButton;
    @FXML public Button stopButton;
    @FXML public Button refreshButton;
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
}
