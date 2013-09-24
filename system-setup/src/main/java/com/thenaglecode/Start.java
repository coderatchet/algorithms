package com.thenaglecode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 9/09/13
 * Time: 4:27 PM
 * //todo implement
 */
public class Start extends Application {

    Scene root;

    public static void main(String[] args){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("Hello World!");
        URL url = this.getClass().getResource("fxml/mainPage.fxml");
        VBox root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
