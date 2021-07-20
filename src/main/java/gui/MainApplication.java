package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class MainApplication extends Application {
    private final static int MIN_HEIGHT = 800;
    private final static int MIN_WIDTH = 1200;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = getClass().getResource("/MainWindow.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setScene(new Scene(root));

        primaryStage.setTitle("Hearthstone Battlegrounds Simulator");
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.show();
    }
}