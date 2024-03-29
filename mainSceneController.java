import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;

public class mainSceneController {

    JavaUI myJavaUI = new JavaUI();

    @FXML
    public WebView webMap;
    @FXML
    public WebEngine engine;

   

    public double Percentage_Of_Used_Screen;
    public double design_width;
    public double design_height;
    public double screenWidth;
    public double screenHeight;
    // Actual used screen widths and heights
    public double W_width;
    public double W_height;

    public ImageView[] f_disc;

    @FXML
    public Rectangle searchbuttonrect;
    @FXML
    public Rectangle humidityRect;
    @FXML
    public Rectangle forcastRect;
    @FXML
    public Rectangle searchRect;
    @FXML
    public Rectangle riseRect;
    @FXML
    public Rectangle setRect;
    @FXML
    public Region maxminRect;
    @FXML
    public Region weatherRect;
    @FXML
    public Region stampRect;
    @FXML
    public Rectangle dayRect;
    @FXML
    public Button gasesButton;
    @FXML
    public Group gasesTable;
    @FXML
    public AnchorPane container;
    @FXML
    public Line headerLine;
    @FXML
    public Button searchButton;

    @FXML
    public ImageView backGroundPic;
    @FXML
    public TextField textfieldLocation;

    @FXML
    public Label mintxt;
    @FXML
    public Label maxtxt;
    @FXML
    public Label searchtxt;

    @FXML
    public Label locationLabel;
    @FXML
    public Label temperatureLabel;
    @FXML
    public Label time;

    @FXML
    public Label sunRisetxt;
    @FXML
    public Label sunSettxt;
    @FXML
    public Label forecasttxt;
    @FXML
    public Label datetxt;
    @FXML
    public Label humiditytxt;

    @FXML
    public Label feelLabel;
    // @FXML
    // public Label minmaxtxt;

    @FXML
    public Label c_maxtemp;

    @FXML
    public Label c_mintemp;

    @FXML
    public Label slash;

    // 3 hourly stamp temprature
    @FXML
    public Label h3_temp1;
    @FXML
    public Label h3_temp2;
    @FXML
    public Label h3_temp3;
    @FXML
    public Label h3_temp4;
    @FXML
    public Label h3_temp5;
    @FXML
    public Label h3_temp6;
    @FXML
    public Label h3_temp7;
    @FXML
    public Label h3_temp8;

    @FXML
    public Label[] h3_tempLabels;

    // 3 hourly stamp time
    @FXML
    public Label h3_time1;
    @FXML
    public Label h3_time2;
    @FXML
    public Label h3_time3;
    @FXML
    public Label h3_time4;
    @FXML
    public Label h3_time5;
    @FXML
    public Label h3_time6;
    @FXML
    public Label h3_time7;
    @FXML
    public Label h3_time8;

    @FXML
    public Label[] h3_timeLabels;

    // time stamp circles
    @FXML
    public Circle c1;
    @FXML
    public Circle c2;
    @FXML
    public Circle c3;
    @FXML
    public Circle c4;
    @FXML
    public Circle c5;
    @FXML
    public Circle c6;
    @FXML
    public Circle c7;
    @FXML
    public Circle c8;

    public Circle[] circles;

    @FXML
    public Line stampline;
    @FXML
    public ImageView weatherIcon;
    @FXML
    public ImageView humidityIcon;
    @FXML
    public ImageView minmaxIcon;
    @FXML
    public ImageView sunriseIcon;
    @FXML
    public ImageView sunsetIcon;
    @FXML
    public ImageView locationIcon;
    @FXML
    public ImageView gasesIcon;
    @FXML
    public Label weatherDes;
    @FXML
    public Label fivedaytxt;

    @FXML
    public Label f_date1;
    @FXML
    public Label f_date2;
    @FXML
    public Label f_date3;
    @FXML
    public Label f_date4;
    @FXML
    public Label f_date5;
    @FXML
    public Label f_max1;
    @FXML
    public Label f_max2;
    @FXML
    public Label f_max3;
    @FXML
    public Label f_max4;
    @FXML
    public Label f_max5;
    @FXML
    public Label f_min1;
    @FXML
    public Label f_min2;
    @FXML
    public Label f_min3;
    @FXML
    public Label f_min4;
    @FXML
    public Label f_min5;

    Label[] f_dateLabels;
    Label[] f_maxLabels;
    Label[] f_minLabels;

    @FXML
    public Rectangle pollutingRect;
    @FXML
    public CubicCurve triRect;

    @FXML
    public Label air_d1;
    @FXML
    public Label air_d2;
    @FXML
    public Label air_d3;
    @FXML
    public Label air_d4;
    @FXML
    public Label air_d5;
    @FXML
    public Label air_d6;
    @FXML
    public Label air_d7;
    @FXML
    public Label air_d8;
    @FXML
    public Label air_p1;
    @FXML
    public Label air_p2;
    @FXML
    public Label air_p3;
    @FXML
    public Label air_p4;
    @FXML
    public Label air_p5;
    @FXML
    public Label air_p6;
    @FXML
    public Label air_p7;
    @FXML
    public Label air_p8;
    @FXML
    public Label aqi_data;
    @FXML
    public Label aqi_label;

    Label[] airLabels;
    Label[] airData;

    @FXML
    public Label pollutinggastxt;
    @FXML
    public Label ctxt;
    @FXML
    public Label ftxt;

    @FXML
    public ImageView coIcon;

    @FXML
    public Rectangle FCrect;
    @FXML
    public Rectangle FCrect1;
    @FXML
    public Rectangle unitrect;
    @FXML
    public Rectangle unitrect1;
    @FXML
    public Button unitbutton;

    @FXML
    private ImageView f_disc1;

    @FXML
    private ImageView f_disc2;

    @FXML
    private ImageView f_disc3;

    @FXML
    private ImageView f_disc4;

    @FXML
    private ImageView f_disc5;

    @FXML
    public Button okButton;
    

    unitConvert uConvert;

    public void initialize() {


        uConvert= new unitConvert();
        
        // Start an AnimationTimer to update the time label every second
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateTimeLabel();
            }
        };
        timer.start();

        engine = webMap.getEngine();
        engine.load(
                "file:///C:/Users/tower/OneDrive/Desktop/Fast-Nu/4th%20Semester/4.%20SDA/Project/testjavafx/firstjavafx/src/map.html");

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Retrieve screen width and height
        screenWidth = bounds.getWidth();
        screenHeight = bounds.getHeight();

        Percentage_Of_Used_Screen = 0.90;
        design_width = 1690;
        design_height = 950;

        // System.out.println("Screen Width: " + screenWidth);
        // System.out.println("Screen Height: " + screenHeight);

        // Actual used screen widths and heights
        W_width = screenWidth * Percentage_Of_Used_Screen;
        W_height = screenHeight * Percentage_Of_Used_Screen;

        // weather Icon
        dynamicImage(weatherIcon);

        // humidity Icon
        dynamicImage(humidityIcon);

        // min max Icon
        dynamicImage(minmaxIcon);

        // min max Icon
        dynamicImage(sunriseIcon);

        // sunset Icon
        dynamicImage(sunsetIcon);

        // location Icon
        dynamicImage(locationIcon);

        // gases Icon
        dynamicImage(gasesIcon);

        // co Icon
        dynamicImage(coIcon);

        // forcast rectangle
        dynamicRectangle(forcastRect);

        // unit rectangle
        dynamicRectangle(unitrect);
        dynamicRectangle(unitrect1);

        // Bind font size to screen width
        dynamicLabel(locationLabel, "work sans", FontWeight.BOLD);

        // Date
        dynamicLabel(time, "work sans", FontWeight.LIGHT);

        // five day txext
        dynamicLabel(fivedaytxt, "work sans", FontWeight.BOLD);

        dynamicLabel(ctxt, "work sans", FontWeight.BOLD);
        dynamicLabel(ftxt, "work sans", FontWeight.BOLD);

        // Search Rectangle
        dynamicRectangle(searchRect);
        dynamicRectangle(searchbuttonrect);
        dynamicLabel(searchtxt, "work sans", FontWeight.BOLD);

        // weather description
        dynamicLabel(weatherDes, "work sans", FontWeight.BOLD);

        // SearchBox
        textfieldLocation.setLayoutX(W_width * textfieldLocation.getLayoutX() / design_width);
        textfieldLocation.setLayoutY(W_height * textfieldLocation.getLayoutY() / design_height);

        // header line
        dynamicLine(headerLine, 0.30);

        // stamp line
        dynamicLine(stampline, 0.50);

        // map
        // dynamicRectangle(map);
        dynamicRectangle(webMap);

        // FC rectangle
        dynamicRectangle(FCrect);
        dynamicRectangle(FCrect1);

        // humidity rectangle
        dynamicRectangle(humidityRect);

        // Rise Rectangle
        dynamicRectangle(riseRect);

        // set Rectangle
        dynamicRectangle(setRect);

        // weather Rectangle
        dynamicRegion(weatherRect);

        // stamp Rectangle
        dynamicRegion(stampRect);

        // maxmin Rectangle
        dynamicRegion(maxminRect);

        // Temperature label
        dynamicLabel(temperatureLabel, "work sans", FontWeight.BOLD);

        // feels like label
        dynamicLabel(feelLabel, "work sans", FontWeight.EXTRA_LIGHT);

        // Time stamp temperature label
        h3_tempLabels = new Label[] { h3_temp1, h3_temp2, h3_temp3, h3_temp4, h3_temp5, h3_temp6, h3_temp7, h3_temp8 };

        for (Label label : h3_tempLabels) {
            dynamicLabel(label, "work sans", FontWeight.BOLD);
        }

        // Time stamp circle
        circles = new Circle[] { c1, c2, c3, c4, c5, c6, c7, c8 };
        for (Circle circle : circles) {
            dynamicCircle(circle);
        }

        // Time stamp time
        h3_timeLabels = new Label[] { h3_time1, h3_time2, h3_time3, h3_time4, h3_time5, h3_time6, h3_time7, h3_time8 };
        for (Label label : h3_timeLabels) {
            dynamicLabel(label, "work sans", FontWeight.MEDIUM);
        }

        // search button
        searchButton.setLayoutX(W_width * searchButton.getLayoutX() / design_width);
        searchButton.setLayoutY(W_height * searchButton.getLayoutY() / design_height);
        searchButton.setPrefWidth(W_width * searchButton.getPrefWidth() / design_width);
        searchButton.setPrefHeight(W_height * searchButton.getPrefHeight() / design_height);
        searchButton.setStyle("-fx-background-color: transparent;");

        // gases button
        gasesButton.setLayoutX(W_width * gasesButton.getLayoutX() / design_width);
        gasesButton.setLayoutY(W_height * gasesButton.getLayoutY() / design_height);
        gasesButton.setPrefWidth(W_width * gasesButton.getPrefWidth() / design_width);
        gasesButton.setPrefHeight(W_height * gasesButton.getPrefHeight() / design_height);
        gasesButton.setStyle("-fx-background-color: transparent;");

        // unit button
        unitbutton.setLayoutX(W_width * unitbutton.getLayoutX() / design_width);
        unitbutton.setLayoutY(W_height * unitbutton.getLayoutY() / design_height);
        unitbutton.setPrefWidth(W_width * unitbutton.getPrefWidth() / design_width);
        unitbutton.setPrefHeight(W_height * unitbutton.getPrefHeight() / design_height);
        unitbutton.setStyle("-fx-background-color: transparent;");

        // gases table
        gasesTable.setLayoutX(W_width * gasesTable.getLayoutX() / design_width);
        gasesTable.setLayoutY(W_height * gasesTable.getLayoutY() / design_height);

        // gases rectangle
        dynamicRectangle(pollutingRect);

        // gases triangle
        triRect.setLayoutX(W_width * triRect.getLayoutX() / design_width);
        triRect.setLayoutY(W_height * triRect.getLayoutY() / design_height);
        triRect.setStartX(W_width * triRect.getStartX() / design_width);
        triRect.setEndX(W_width * triRect.getEndX() / design_width);
        triRect.setStartY(W_width * triRect.getStartY() / design_width);
        triRect.setEndY(W_width * triRect.getEndY() / design_width);
        triRect.setControlX1(W_width * triRect.getControlX1() / design_width);
        triRect.setControlX2(W_width * triRect.getControlX2() / design_width);
        triRect.setControlY1(W_width * triRect.getControlY1() / design_width);
        triRect.setControlY2(W_width * triRect.getControlY2() / design_width);

        // day Rectangle
        dynamicRectangle(dayRect);
        dayRect.setArcHeight(7);
        dayRect.setArcWidth(7);

        // sun rise text
        dynamicLabel(sunRisetxt, "work sans", FontWeight.MEDIUM);

        // sun Set text
        dynamicLabel(sunSettxt, "work sans", FontWeight.MEDIUM);

        // forecast text
        dynamicLabel(forecasttxt, "work sans", FontWeight.BOLD);

        // Min Max label
        // dynamicLabel(minmaxtxt, "work sans", FontWeight.MEDIUM);
        dynamicLabel(c_mintemp, "work sans", FontWeight.MEDIUM);
        dynamicLabel(c_maxtemp, "work sans", FontWeight.MEDIUM);
        dynamicLabel(slash, "work sans", FontWeight.MEDIUM);

        // Humidity Text
        dynamicLabel(humiditytxt, "work sans", FontWeight.MEDIUM);

        // date,min,max labels
        dynamicLabel(datetxt, "work sans", FontWeight.LIGHT);
        dynamicLabel(mintxt, "work sans", FontWeight.LIGHT);
        dynamicLabel(maxtxt, "work sans", FontWeight.LIGHT);

        weatherDes.setPrefWidth(W_width * weatherDes.getPrefWidth() / design_width);
        weatherDes.setPrefHeight(W_height * weatherDes.getPrefHeight() / design_height);

        // forecast date,min,max
        // Create an array of Labels
        f_dateLabels = new Label[] { f_date1, f_date2, f_date3, f_date4, f_date5 };
        f_maxLabels = new Label[] { f_max1, f_max2, f_max3, f_max4, f_max5 };
        f_minLabels = new Label[] { f_min1, f_min2, f_min3, f_min4, f_min5 };
        f_disc = new ImageView[] { f_disc1, f_disc2, f_disc3, f_disc4, f_disc5 };

        for (ImageView img : f_disc) {
            dynamicImage(img);
        }

        // Apply formatting to each label in the arrays
        for (Label label : f_dateLabels) {
            dynamicLabel(label, "Voces", FontWeight.BOLD);
        }

        for (Label label : f_maxLabels) {
            dynamicLabel(label, "Voces", FontWeight.BOLD);
        }

        for (Label label : f_minLabels) {
            dynamicLabel(label, "Voces", FontWeight.BOLD);
        }

        airLabels = new Label[] { air_d1, air_d2, air_d3, air_d4, air_d5, air_d6, air_d7, air_d8, air_p1, air_p2,
                air_p3, air_p4, air_p5, air_p6, air_p7, air_p8 };
        for (Label label : airLabels) {
            dynamicLabel(label, "Voces", FontWeight.BOLD);
        }
        airData = new Label[] {
                air_p1, air_p2, air_p3, air_p4, air_p5, air_p6, air_p7, air_p8
        };

        // polluting gas text
        dynamicLabel(pollutinggastxt, "work sans", FontWeight.BOLD);

        NightModeDayMode();

        // Ensure backGroundPic is initialized properly
        if (backGroundPic != null) {
            // Set the size of the image view
            backGroundPic.setFitHeight(W_height);
            backGroundPic.setFitWidth(W_width);

        } else {
            System.out.println("backGroundPic is null!");
        }


        // Create a timeline to check the time every second
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Run the timeline indefinitely
        timeline.play();
    }

    // Method to update the time label with the current date and time
    public void updateTimeLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EE, MMMM dd, yyyy");
        Date now = new Date();
        time.setText(dateFormat.format(now));
    }

    @FXML
    void enterGases(ActionEvent event) {
        // If the table is currently visible, hide it. Otherwise, show it.
        gasesTable.setVisible(!gasesTable.isVisible());
    }

    public void dynamicRectangle(Rectangle rect) {
        rect.setWidth(W_width * rect.getWidth() / design_width);
        rect.setHeight(W_height * rect.getHeight() / design_height);
        rect.setLayoutX(W_width * rect.getLayoutX() / design_width);
        rect.setLayoutY(W_height * rect.getLayoutY() / design_height);
    }

    public void dynamicRectangle(WebView rect) {
        rect.prefWidth(W_width * rect.getWidth() / design_width);
        rect.prefHeight(W_height * rect.getHeight() / design_height);
        rect.setLayoutX(W_width * rect.getLayoutX() / design_width);
        rect.setLayoutY(W_height * rect.getLayoutY() / design_height);
    }

    public void dynamicLabel(Label lab, String fontFamily, FontWeight fw) {
        double tempFontSize = W_height * lab.getFont().getSize() / design_height;
        lab.setFont(Font.font(fontFamily, fw, tempFontSize));
        lab.setLayoutX(W_width * lab.getLayoutX() / design_width);
        lab.setLayoutY(W_height * lab.getLayoutY() / design_height);
        // lab.setAlignment(Pos.CENTER);
    }

    public void dynamicCircle(Circle c) {
        c.setRadius(c.getRadius() * W_width / design_width);
        c.setLayoutX(W_width * c.getLayoutX() / design_width);
        c.setLayoutY(W_height * c.getLayoutY() / design_height);
    }

    public void dynamicRegion(Region r) {
        r.setPrefWidth(W_width * r.getPrefWidth() / design_width);
        r.setPrefHeight(W_height * r.getPrefHeight() / design_height);
        r.setLayoutX(W_width * r.getLayoutX() / design_width);
        r.setLayoutY(W_height * r.getLayoutY() / design_height);
    }

    public void dynamicImage(ImageView i) {
        i.setFitWidth(W_width * i.getFitWidth() / design_width);
        i.setFitHeight(W_height * i.getFitHeight() / design_height);
        i.setLayoutX(W_width * i.getLayoutX() / design_width);
        i.setLayoutY(W_height * i.getLayoutY() / design_height);
    }

    public void dynamicLine(Line l, double stroke) {
        l.setLayoutX(W_width * l.getLayoutX() / design_width);
        l.setLayoutY(W_height * l.getLayoutY() / design_height);
        l.setStartX(W_width * l.getStartX() / design_width);
        l.setEndX(W_width * l.getEndX() / design_width);
        l.setStrokeWidth(stroke);
    }

    public void NightModeDayMode() {

        // Create a timeline to check the time every second
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.1), event -> {
            // Get current time
            LocalTime currentTime = LocalTime.now();
            int hour = currentTime.getHour();
            // int minute = currentTime.getMinute();

            // Check if it's 6 PM to 6AM
            if (hour >= 18 || hour <= 6) {
                backGroundPic.setImage(new Image("nightPic.jpg"));
                changeRectangleColorToWhite();
                changeFontColorToWhite();

            } else {
                backGroundPic.setImage(new Image("dayPic.jpg"));
                changeRectangleColorToBlack();
                changeFontColorToBlack();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Run the timeline indefinitely
        timeline.play();
    }

    public void changeRectangleColorToBlack() {
        forcastRect.setFill(Color.rgb(0, 0, 0, 0.3));
        riseRect.setFill(Color.rgb(0, 0, 0, 0.3));
        setRect.setFill(Color.rgb(0, 0, 0, 0.3));
        humidityRect.setFill(Color.rgb(0, 0, 0, 0.3));
        searchRect.setFill(Color.rgb(0, 0, 0, 0.4));
        FCrect.setFill(Color.rgb(0, 0, 0, 0.3));
        triRect.setFill(Color.rgb(0, 0, 0, 0.3));
        headerLine.setStroke(Color.BLACK);

        maxminRect.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);" + "-fx-background-radius: 10 0 0 0;");
        weatherRect.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);" + "-fx-background-radius: 0 10 10 0;");
        stampRect.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);" + "-fx-background-radius: 0 0 0 10;");
        pollutingRect.setFill(Color.rgb(0, 0, 0, 0.4));

    }

    public void changeFontColorToBlack() {
        locationLabel.setTextFill(Color.rgb(0, 0, 0)); // chnage to black
        time.setTextFill(Color.rgb(0, 0, 0));
    }

    public void changeRectangleColorToWhite() {
        forcastRect.setFill(Color.rgb(255, 255, 255, 0.14));
        riseRect.setFill(Color.rgb(255, 255, 255, 0.14));
        setRect.setFill(Color.rgb(255, 255, 255, 0.14));
        humidityRect.setFill(Color.rgb(255, 255, 255, 0.14));
        searchRect.setFill(Color.rgb(255, 255, 255, 0.14));
        FCrect.setFill(Color.rgb(255, 255, 255, 0.14));
        triRect.setFill(Color.rgb(255, 255, 255, 0.14));
        headerLine.setStroke(Color.WHITE);

        maxminRect.setStyle("-fx-background-color: rgba(255, 255, 255, 0.14);" + "-fx-background-radius: 10 0 0 0;");
        weatherRect.setStyle("-fx-background-color: rgba(255, 255, 255, 0.25);" + "-fx-background-radius: 0 10 10 0;");
        stampRect.setStyle("-fx-background-color: rgba(255, 255, 255,0.14);" + "-fx-background-radius: 0 0 0 10;");
        pollutingRect.setFill(Color.rgb(255, 255, 255, 0.14));

    }

    public void changeFontColorToWhite() {
        locationLabel.setTextFill(Color.rgb(255, 255, 255, 1)); // chnage to White
        time.setTextFill(Color.rgb(255, 255, 255, 1));
    }

    @FXML
    void changeUnit(ActionEvent event)
    {
        uConvert.changeUnit(ctxt,ftxt,unitrect,unitrect1,temperatureLabel, feelLabel, c_mintemp, c_maxtemp,f_maxLabels, f_minLabels, h3_tempLabels);
    }

}