import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Button
        Button btn = new Button();
        btn.setText("Click me!");

        // Set action for the Button
        btn.setOnAction(event -> System.out.println("Hello, JavaFX!"));

        // Add the Button to a layout (StackPane)
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // Create a Scene
        Scene scene = new Scene(root, 300, 250);

        // Set the Scene on the Stage
        primaryStage.setScene(scene);

        // Set the title of the Stage
        primaryStage.setTitle("JavaFX Example");

        // Show the Stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
