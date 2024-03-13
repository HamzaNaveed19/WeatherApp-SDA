import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class mainSceneController {

     @FXML
    private ImageView backGroundPic;
    
    @FXML
    private TextField textfieldLocation;

    @FXML
    private Label locationLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label time;

    @FXML
    private Button gasesButton;

    @FXML
    private Group gasesTable;
    

    public void initialize(URL location, ResourceBundle resources) {
        
    }

    // Method to initialize the controller
    public void initialize() {
        // Start an AnimationTimer to update the time label every second
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateTimeLabel();
            }
        };
        timer.start();

        backGroundPic.setImage(new Image("dayPic.jpg"));
         backGroundPic.setFitWidth(1423); // Set the width
        backGroundPic.setFitHeight(800); // Set the height


            // Create a timeline to check the time every second
            Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            // Get current time
            LocalTime currentTime = LocalTime.now();
            int hour = currentTime.getHour();
            //int minute = currentTime.getMinute();
    
            // Check if it's 12 PM to 6
            if (hour >= 18 || hour <=6) {
                  // Change the image to the new image
                 backGroundPic.setImage(new Image("nightPic.jpg"));
             }
            else{
                backGroundPic.setImage(new Image("dayPic.jpg"));
             }
        }));
            timeline.setCycleCount(Animation.INDEFINITE); // Run the timeline indefinitely
            timeline.play();
    }

    // Method to handle the enter button click event
    @FXML
    void enterButtonClicked(ActionEvent event) {
        String location = textfieldLocation.getText();
        locationLabel.setText(location);

        // Testing temperature (replace with actual weather API integration)
        int temperature = generateTestingTemperature();
        temperatureLabel.setText(temperature + "°");
    }

    // Method to generate a testing temperature
    private int generateTestingTemperature() {
        // Generate a random temperature between 10 and 40 degrees Celsius
        return (int) (Math.random() * 31) + 10;
    }

    // Method to update the time label with the current date and time
    private void updateTimeLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EE, MMMM dd, yyyy");
        Date now = new Date();
        time.setText(dateFormat.format(now));
    }



    @FXML
void enterGases(ActionEvent event) {
    // If the table is currently visible, hide it. Otherwise, show it.
    gasesTable.setVisible(!gasesTable.isVisible());

    // if (gasesTable.isDisabled()) {
    //     // If it's disabled, enable it
    //     gasesTable.setDisable(false);
    // } else {
    //     // If it's enabled, disable it
    //     gasesTable.setDisable(true);
    // }
}

        

}
