package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApplication extends Application {
    private final static int MIN_HEIGHT = 800;
    private final static int MIN_WIDTH = 1200;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../../resources/main-window.fxml"));
            primaryStage.setTitle("Hearthstone Battlegrounds Simulator");
            primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println("FXMLLoader failed");
            e.printStackTrace();
        }

        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.show();
    }
}