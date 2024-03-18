import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        Screen screen = Screen.getPrimary();

        // Get the bounds of the primary screen
        Rectangle2D bounds = screen.getVisualBounds();
        // Retrieve screen width and height
        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();

        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("controller.fxml"));
        
        // Create a scene with the loaded FXML file
        Scene scene = new Scene(root);
        
        // Set the scene to the stage
        primaryStage.setScene(scene);
        
        // Settings
        primaryStage.setTitle("Weather App");
        primaryStage.setWidth(screenWidth*0.90);
        primaryStage.setHeight(screenHeight* 0.90);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        // Show the stage
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
