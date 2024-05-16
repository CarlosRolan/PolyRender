import awt.scene.SceneWindow;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;

import javafx.stage.Stage;


public class Main2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent  root = FXMLLoader.load(getClass().getResource("fx/sample.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        // Set the stage title
        primaryStage.setTitle("JavaFX App");
        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
