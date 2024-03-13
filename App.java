import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {


 
    @Override
    public void start(Stage primaryStage) {
       
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().add("animation.css");

            primaryStage.setTitle("Hello World!");

            primaryStage.initStyle(StageStyle.UNDECORATED);

            Image appIcon = new Image("weather-app-icon.png");

            primaryStage.getIcons().add(appIcon);



            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}