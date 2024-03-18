import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

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
    
    @FXML
    private AnchorPane container;

    @FXML
    private Rectangle forcastRect;

    @FXML
    private Rectangle searchRect;

    @FXML
    private Line headerLine;

    @FXML
    private Rectangle map;

    @FXML
    private Rectangle humidityRect;

    @FXML
    private Rectangle maxminRect;

    @FXML
    private Rectangle riseRect;

    @FXML
    private Rectangle setRect;

    @FXML
    private Region weatherRect;

    @FXML
    private Rectangle stampRect;

    @FXML
    private Label feelLabel;

    @FXML
    private Label feelTemp;

    @FXML
    private Button searchButton;

    @FXML
    private Rectangle dayRect;


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

        Screen screen = Screen.getPrimary();

        // Get the bounds of the primary screen
        Rectangle2D bounds = screen.getVisualBounds();

        // Retrieve screen width and height
        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();

        System.out.println("Screen Width: " + screenWidth);
        System.out.println("Screen Height: " + screenHeight);

        //forcast rectangle
        forcastRect.setWidth(screenWidth*0.90*forcastRect.getWidth()/1690);
        forcastRect.setHeight(screenHeight*0.90*forcastRect.getHeight()/950);
        forcastRect.setLayoutX(screenWidth*0.90*forcastRect.getLayoutX()/1690);
        forcastRect.setLayoutY(screenHeight*0.90*forcastRect.getLayoutY()/950);

        // Bind font size to screen width
        double locationfontSize = screenWidth *0.90* 0.030;
        locationLabel.setStyle("-fx-font-family: work sans; -fx-font-weight: bold; -fx-font-size: " + locationfontSize + "px");
        locationLabel.setLayoutX(screenWidth*0.90*0.1076);
        locationLabel.setLayoutY(screenHeight*0.90*0.0457);

        //Date
        double datefontSize = screenWidth *0.90* 0.015;
        time.setStyle("-fx-font-family: work sans; -fx-font-weight: semibold; -fx-font-size: " + datefontSize + "px");
        time.setLayoutX(screenWidth*0.90*0.1076);
        time.setLayoutY(screenHeight*0.90*0.120);

        //Search Rectangle
        searchRect.setWidth(screenWidth*0.90*0.3273);
        searchRect.setHeight(screenHeight*0.90*0.06206);
        searchRect.setLayoutX(screenWidth*0.90*0.5639);
        searchRect.setLayoutY(screenHeight*0.90*0.0921);

        //SearchBox
        textfieldLocation.setLayoutX(screenWidth*0.90*textfieldLocation.getLayoutX()/1690);
        textfieldLocation.setLayoutY(screenHeight*0.90*textfieldLocation.getLayoutY()/950);

        //header line
        headerLine.setLayoutX(screenWidth*0.90*0.10);
        headerLine.setLayoutY(screenHeight*0.90*0.1905);
        headerLine.setEndX(screenWidth*0.90*0.7958);
        headerLine.setStrokeWidth(0.50);

        //map
        map.setWidth(screenWidth*0.90*map.getWidth()/1690);
        map.setHeight(screenHeight*0.90*map.getHeight()/950);
        map.setLayoutX(screenWidth*0.90*map.getLayoutX()/1690);
        map.setLayoutY(screenHeight*0.90*map.getLayoutY()/950);

        //maxmin Rectangle
        maxminRect.setWidth(screenWidth*0.90*maxminRect.getWidth()/1690);
        maxminRect.setHeight(screenHeight*0.90*maxminRect.getHeight()/950);
        maxminRect.setLayoutX(screenWidth*0.90*maxminRect.getLayoutX()/1690);
        maxminRect.setLayoutY(screenHeight*0.90*maxminRect.getLayoutY()/950);

        //humidity rectangle
        humidityRect.setWidth(screenWidth*0.90*humidityRect.getWidth()/1690);
        humidityRect.setHeight(screenHeight*0.90*humidityRect.getHeight()/950);
        humidityRect.setLayoutX(screenWidth*0.90*humidityRect.getLayoutX()/1690);
        humidityRect.setLayoutY(screenHeight*0.90*humidityRect.getLayoutY()/950);

        //Rise Rectangle
        riseRect.setWidth(screenWidth*0.90*riseRect.getWidth()/1690);
        riseRect.setHeight(screenHeight*0.90*riseRect.getHeight()/950);
        riseRect.setLayoutX(screenWidth*0.90*riseRect.getLayoutX()/1690);
        riseRect.setLayoutY(screenHeight*0.90*riseRect.getLayoutY()/950);

        //set Rectangle
        setRect.setWidth(screenWidth*0.90*setRect.getWidth()/1690);
        setRect.setHeight(screenHeight*0.90*setRect.getHeight()/950);
        setRect.setLayoutX(screenWidth*0.90*setRect.getLayoutX()/1690);
        setRect.setLayoutY(screenHeight*0.90*setRect.getLayoutY()/950);

        //weather Rectangle
        weatherRect.setPrefWidth(screenWidth*0.90*weatherRect.getPrefWidth()/1690);
        weatherRect.setPrefHeight(screenHeight*0.90*weatherRect.getPrefHeight()/950);
        weatherRect.setLayoutX(screenWidth*0.90*weatherRect.getLayoutX()/1690);
        weatherRect.setLayoutY(screenHeight*0.90*weatherRect.getLayoutY()/950);

        //stamp Rectangle
        stampRect.setWidth(screenWidth*0.90*stampRect.getWidth()/1690);
        stampRect.setHeight(screenHeight*0.90*stampRect.getHeight()/950);
        stampRect.setLayoutX(screenWidth*0.90*stampRect.getLayoutX()/1690);
        stampRect.setLayoutY(screenHeight*0.90*stampRect.getLayoutY()/950);

        //Temperature label
        double tempfontSize = screenHeight *0.90* 0.18;
        temperatureLabel.setStyle("-fx-font-family: work sans; -fx-font-weight: semibold; -fx-font-size: " + tempfontSize + "px");
        temperatureLabel.setLayoutX(screenWidth*0.90*temperatureLabel.getLayoutX()/1690);
        temperatureLabel.setLayoutY(screenHeight*0.90*0.43);

        //feels like label
        double feelfontSize = screenHeight *0.90* 0.06;
        feelLabel.setStyle("-fx-font-family: work sans; -fx-font-weight: semibold; -fx-font-size: " + feelfontSize + "px");
        feelLabel.setLayoutX(screenWidth*0.90*0.23);
        feelLabel.setLayoutY(screenHeight*0.90*feelLabel.getLayoutY()/950);

        //feel Temperature
        double feeltempfontSize = screenHeight *0.90* 0.06;
        feelTemp.setStyle("-fx-font-family: work sans; -fx-font-weight: semibold; -fx-font-size: " + feeltempfontSize + "px");
        feelTemp.setLayoutX(screenWidth*0.90*0.365);
        feelTemp.setLayoutY(screenHeight*0.90*feelTemp.getLayoutY()/950);

        //search button
        searchButton.setLayoutX(screenWidth*0.90*0.82);
        searchButton.setLayoutY(screenHeight*0.90*0.1);
        searchButton.setPrefWidth(screenWidth*0.90*0.060);
        searchButton.setPrefHeight(screenHeight*0.90*0.0085);
        searchButton.setStyle("-fx-font-size:8px");

        //day Rectangle
        dayRect.setWidth(screenWidth*0.90*dayRect.getWidth()/1690);
        dayRect.setHeight(screenHeight*0.90*dayRect.getHeight()/950);
        dayRect.setLayoutX(screenWidth*0.90*dayRect.getLayoutX()/1690);
        dayRect.setLayoutY(screenHeight*0.90*dayRect.getLayoutY()/950);
        dayRect.setArcHeight(7);
        dayRect.setArcWidth(7);

        // Ensure backGroundPic is initialized properly
        if (backGroundPic != null) {
            // Set the size of the image view
            backGroundPic.setFitHeight(screenHeight * 0.90);
            backGroundPic.setFitWidth(screenWidth * 0.90);

        } else {
            System.out.println("backGroundPic is null!");
        }

        // Create a timeline to check the time every second
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {}));
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
    }
}
