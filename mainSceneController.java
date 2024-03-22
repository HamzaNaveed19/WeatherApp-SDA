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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public class mainSceneController {

    private double Percentage_Of_Used_Screen;
    private double design_width ;
    private double design_height;
    private double screenWidth;
    private double screenHeight;
    //Actual used screen widths and heights
    private double W_width;
    private double W_height;

    @FXML private ImageView backGroundPic;  
    @FXML private TextField textfieldLocation;
    @FXML private Label locationLabel;
    @FXML private Label temperatureLabel;
    @FXML private Label time;
    @FXML private Button gasesButton;
    @FXML private Group gasesTable;  
    @FXML private AnchorPane container;
    @FXML private Rectangle forcastRect;
    @FXML private Rectangle searchRect;
    @FXML private Line headerLine;
    @FXML private Rectangle map;
    @FXML private Rectangle humidityRect;
    @FXML private Region maxminRect;
    @FXML private Rectangle riseRect;
    @FXML private Rectangle setRect;
    @FXML private Region weatherRect;
    @FXML private Region stampRect;
    @FXML private Label feelLabel;
    @FXML private Button searchButton;
    @FXML private Rectangle dayRect;
    @FXML private Label sunRisetxt;
    @FXML private Label sunSettxt;
    @FXML private Label forecasttxt; 
    @FXML private Label datetxt;
    @FXML private Label mintxt;  
    @FXML private Label maxtxt;
    @FXML private Label minmaxtxt;
    @FXML private Label humiditytxt;

    // 3 hourly stamp temprature
    @FXML private Label h3_temp1;
    @FXML private Label h3_temp2;
    @FXML private Label h3_temp3;
    @FXML private Label h3_temp4;
    @FXML private Label h3_temp5;
    @FXML private Label h3_temp6;
    @FXML private Label h3_temp7;
    @FXML private Label h3_temp8;

    @FXML private Label[] h3_tempLabels;

    // 3 hourly stamp time
    @FXML private Label h3_time1;
    @FXML private Label h3_time2;
    @FXML private Label h3_time3;
    @FXML private Label h3_time4;
    @FXML private Label h3_time5;
    @FXML private Label h3_time6;
    @FXML private Label h3_time7;
    @FXML private Label h3_time8;

    private Label[] h3_timeLabels;

    //time stamp circles
    @FXML private Circle c1;
    @FXML private Circle c2;
    @FXML private Circle c3;
    @FXML private Circle c4;
    @FXML private Circle c5;
    @FXML private Circle c6;
    @FXML private Circle c7;
    @FXML private Circle c8;

    private Circle[] circles;

    @FXML private Line stampline;
    @FXML private ImageView weatherIcon;
    @FXML private Label weatherDes;


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
        Rectangle2D bounds = screen.getVisualBounds();

        // Retrieve screen width and height
        screenWidth = bounds.getWidth();
        screenHeight = bounds.getHeight();

        Percentage_Of_Used_Screen=0.90;
        design_width = 1690;
        design_height = 950;

        System.out.println("Screen Width: " + screenWidth);
        System.out.println("Screen Height: " + screenHeight);

        //Actual used screen widths and heights
        W_width=screenWidth*Percentage_Of_Used_Screen;
        W_height=screenHeight*Percentage_Of_Used_Screen;

        //weather Icon
        weatherIcon.setFitWidth(W_width*weatherIcon.getFitWidth()/design_width);
        weatherIcon.setFitHeight(W_height*weatherIcon.getFitHeight()/design_height);
        weatherIcon.setLayoutX(W_width*weatherIcon.getLayoutX()/design_width);
        weatherIcon.setLayoutY(W_height*weatherIcon.getLayoutY()/design_height);

        //forcast rectangle
        dynamicRectangle(forcastRect);

        // Bind font size to screen width
        dynamicLabel(locationLabel,"work sans", FontWeight.BOLD);

        //Date
        dynamicLabel(time,"work sans", FontWeight.LIGHT);

        //Search Rectangle
        dynamicRectangle(searchRect);

        //weather description
        dynamicLabel(weatherDes, "work sans",FontWeight.BOLD);

        //SearchBox
        textfieldLocation.setLayoutX(W_width*textfieldLocation.getLayoutX()/design_width);
        textfieldLocation.setLayoutY(W_height*textfieldLocation.getLayoutY()/design_height);

        //header line
        headerLine.setLayoutX(W_width*0.10);
        headerLine.setLayoutY(W_height*0.1905);
        headerLine.setEndX(W_width*0.7958);
        headerLine.setStrokeWidth(0.30);

        //stamp line 
        stampline.setLayoutX(W_width*stampline.getLayoutX()/design_width);
        stampline.setLayoutY(W_height*stampline.getLayoutY()/design_height);
        stampline.setStartX(W_width*stampline.getStartX()/design_width);
        stampline.setEndX(W_width*stampline.getEndX()/design_width);
        stampline.setStrokeWidth(0.50);

        //map
        dynamicRectangle(map);

        //humidity rectangle
        dynamicRectangle(humidityRect);

        //Rise Rectangle
        dynamicRectangle(riseRect);

        //set Rectangle
        dynamicRectangle(setRect);

        //weather Rectangle
        dynamicRegion(weatherRect);

        //stamp Rectangle
        dynamicRegion(stampRect);

        //maxmin Rectangle
        dynamicRegion(maxminRect);
        
        //Temperature label
        dynamicLabel(temperatureLabel,"work sans",FontWeight.BOLD);
        
        //feels like label
        dynamicLabel(feelLabel,"work sans",FontWeight.EXTRA_LIGHT);

        //Time stamp temperature label
        h3_tempLabels = new Label[]{h3_temp1, h3_temp2, h3_temp3, h3_temp4, h3_temp5, h3_temp6, h3_temp7, h3_temp8};
    
        for (Label label : h3_tempLabels) {
            dynamicLabel(label, "work sans", FontWeight.BOLD);
        }

        //Time stamp circle
        circles = new Circle[]{c1,c2,c3,c4,c5,c6,c7,c8};
        for (Circle circle : circles) {
            dynamicCircle(circle);
        }

        //Time stamp time
        h3_timeLabels = new Label[]{h3_time1, h3_time2, h3_time3, h3_time4, h3_time5, h3_time6, h3_time7, h3_time8};
        for (Label label : h3_timeLabels) {
            dynamicLabel(label, "work sans", FontWeight.MEDIUM);
        }

        //search button
        searchButton.setLayoutX(W_width*searchButton.getLayoutX()/design_width);
        searchButton.setLayoutY(W_height*searchButton.getLayoutY()/design_height);
        searchButton.setPrefWidth(W_width*searchButton.getPrefWidth()/design_width);
        searchButton.setPrefHeight(W_height*searchButton.getPrefHeight()/design_height);
        searchButton.setStyle("-fx-background-color: transparent;");

        //day Rectangle
        dynamicRectangle(dayRect);
        dayRect.setArcHeight(7);
        dayRect.setArcWidth(7);

        //sun rise text 
        dynamicLabel(sunRisetxt,"work sans",FontWeight.MEDIUM);

        //sun Set text 
        dynamicLabel(sunSettxt,"work sans",FontWeight.MEDIUM);

        //forecast text 
        dynamicLabel(forecasttxt,"work sans",FontWeight.BOLD);
        
        //Min Max label
        dynamicLabel(minmaxtxt,"work sans",FontWeight.MEDIUM);

        //Humidity Text
        dynamicLabel(humiditytxt,"work sans",FontWeight.MEDIUM);

        //date,min,max labels
        dynamicLabel(datetxt,"work sans",FontWeight.LIGHT);
        dynamicLabel(mintxt,"work sans",FontWeight.LIGHT);
        dynamicLabel(maxtxt,"work sans",FontWeight.LIGHT);

        // Ensure backGroundPic is initialized properly
        if (backGroundPic != null) {
            // Set the size of the image view
            backGroundPic.setFitHeight(W_height);
            backGroundPic.setFitWidth(W_width);

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

    private void dynamicRectangle(Rectangle rect) {
        rect.setWidth(W_width*rect.getWidth()/design_width);
        rect.setHeight(W_height*rect.getHeight()/design_height);
        rect.setLayoutX(W_width*rect.getLayoutX()/design_width);
        rect.setLayoutY(W_height*rect.getLayoutY()/design_height);
    }

    private void dynamicLabel(Label lab,String fontFamily,FontWeight fw) {
        double tempFontSize = W_height  * lab.getFont().getSize() / design_height;
        lab.setFont(Font.font(fontFamily,fw, tempFontSize));    
        lab.setLayoutX(W_width* lab.getLayoutX() / design_width);
        lab.setLayoutY(W_height * lab.getLayoutY() / design_height);
    }

    private void dynamicCircle(Circle c){
        c.setRadius(c.getRadius()*W_width/design_width);
        c.setLayoutX(W_width*c.getLayoutX()/design_width);
        c.setLayoutY(W_height*c.getLayoutY()/design_height);
    }

    private void dynamicRegion(Region r){
        r.setPrefWidth(W_width*r.getPrefWidth()/design_width);
        r.setPrefHeight(W_height*r.getPrefHeight()/design_height);
        r.setLayoutX(W_width*r.getLayoutX()/design_width);
        r.setLayoutY(W_height*r.getLayoutY()/design_height);
    }
}
    
