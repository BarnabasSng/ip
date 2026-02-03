package Barn.gui;

import java.io.IOException;

import Barn.Barn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Barn using FXML.
 */
public class Main extends Application {

    private Barn barn = new Barn("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBarn(barn); // inject the Barn instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
