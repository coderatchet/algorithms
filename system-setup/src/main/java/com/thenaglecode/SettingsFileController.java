package com.thenaglecode;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: Macindows
 * Date: 16/09/13
 * Time: 18:18
 */
public class SettingsFileController implements Initializable{

    @FXML public Button back;
    @FXML public TextField portField;
    private SettingsFile settingsFile;
    @FXML private TextField nameField;
    @FXML private ComboBox<File> basedirChoice;
    @FXML private TextField socketField;
    @FXML private Label error;


    public SettingsFileController(){}

    public Button getBack() {
        return back;
    }

    public TextField getPortField() {
        return portField;
    }

    public SettingsFile getSettingsFile() {
        return settingsFile;
    }

    public TextField getNameField() {
        return nameField;
    }

    public ComboBox<File> getBasedirComboBox() {
        return basedirChoice;
    }

    public TextField getSocketField() {
        return socketField;
    }

    public void setSettingsFile(SettingsFile settingsFile) {
        this.settingsFile = settingsFile;
        getNameField().setText(settingsFile.name);
        for(File file : getBasedirComboBox().getItems()){
            if(file.getAbsolutePath().equals(settingsFile.baseDir)){
                getBasedirComboBox().getSelectionModel().select(file);
                break;
            }
        }
        getPortField().setText(String.valueOf(settingsFile.port));
        getSocketField().setText(settingsFile.socket);
    }

    public void setBackAction(final Callable<Void> backAction){
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    backAction.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @FXML
    public void save() throws IOException {
        if(settingsFile == null) return;
        settingsFile.name = nameField.getText();
        settingsFile.port = Integer.valueOf(socketField.getText());
        settingsFile.socket = socketField.getText();
        if(!settingsFile.isValid()){
            return;
        }
        FileObject configFolder = VFS.getManager().resolveFile("file://" + SettingsFileUtil.CONFIG_FOLDER_ABSOLUTE_PATH);
        FileObject settings = configFolder.resolveFile("./"+ settingsFile.name + ".cnf");
        settings.delete();
        settings.createFile();
        IOUtils.copy(new StringReader(settingsFile.toString()), settings.getContent().getOutputStream());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getBasedirComboBox().setCellFactory(new Callback<ListView<File>, ListCell<File>>() {
                @Override
                public ListCell<File> call(ListView<File> fileListView) {
                    return new ListCell<File>(){
                        @Override
                        protected void updateItem(File file, boolean empty) {
                            super.updateItem(file, empty);
                            if(!empty){
                                setText(file == null ? "" : file.getAbsolutePath());
                            }
                        }
                    };
                }
            });
            SettingsFileUtil.getAllMySqlHomes();
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
        getBasedirComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<File>() {
            @Override
            public void changed(ObservableValue<? extends File> observableValue, File file, File file2) {
                settingsFile.baseDir = file2.getAbsolutePath();
            }
        });
        getNameField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                settingsFile.name = s2;
            }
        });
        List<File> list = new ArrayList<>();
    }

    @FXML
    public void reloadMySqlHomes(ActionEvent actionEvent) {
        try {
            Context.getInstance().reloadMySqlHomes();
        } catch (FileSystemException e) {
            getError().setText(e.getMessage());
            getError().setTextFill(Color.FIREBRICK);
        }
    }

    public Label getError() {
        return error;
    }
}
