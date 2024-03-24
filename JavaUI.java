import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaUI implements abstractUI {

    mainSceneController controller;
    @Override   
    public void start() throws Exception {
        Stage primaryStage = new Stage();
        double percentageOfUsedScreen = 0.90;
        Screen screen = Screen.getPrimary();

        // Get the bounds of the primary screen
        Rectangle2D bounds = screen.getVisualBounds();

        // Retrieve screen width and height
        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
        Parent root = loader.load();

        // Obtain the controller instance
        controller = loader.getController();

        // Create a scene with the loaded FXML file
        Scene scene = new Scene(root);

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Add custom CSS for styling the title bar
        scene.getStylesheets().add(getClass().getResource("animation.css").toExternalForm());

        // Display tray notification

        // notification notifier = new notification();
        // notifier.displayTrayNotification("Weather App", "Poor weather conditions detected!");



        // Settings
        primaryStage.setTitle("Weather App");
        primaryStage.setWidth(screenWidth * percentageOfUsedScreen);
        primaryStage.setHeight(screenHeight * percentageOfUsedScreen);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);


        // Show the stage
        primaryStage.show();
    }
    
    @Override
    public void displayAirQualityIndex(int temp) {
        
        controller.aqi_label.setText(temp+"");
    }

    @Override
    public void displayMinMaxTemp(int temp1, int temp2) {
        controller.minmaxtxt.setText(temp1+"°"+temp2+"°");
    }


    @Override
    public void displayHumidity(int temp) {
        controller.humiditytxt.setText(temp+"");
    }


    @Override
    public void displayFeelsLike(int temp) {
        controller.feelLabel.setText(temp+"°");
     }


    @Override
    public void displayCurrentDescription(String temp) {
        controller.weatherDes.setText(temp);
    }


    @Override
    public void displaySunRiseTime(String temp) {
        controller.sunRisetxt.setText(temp+"");
    }

    @Override
    public void displaySunSetTime(String temp) {
        controller.sunSettxt.setText(temp);
    }


    @Override
    public void displayCurrentLocationDate(String temp) {
        
    }


    @Override
    public void displayH3Temp1(int temp) {
        controller.h3_temp1.setText(temp+"°");
    }


    @Override
    public void displayH3Temp2(int temp) {
        controller.h3_temp2.setText(temp+"°");
    }


    @Override
    public void displayH3Temp3(int temp) {
        controller.h3_temp3.setText(temp+"°");
    }


    @Override
    public void displayH3Temp4(int temp) {
        controller.h3_temp4.setText(temp+"°");
    }


    @Override
    public void displayH3Temp5(int temp) {
        controller.h3_temp5.setText(temp+"°");
    }


    @Override
    public void displayH3Temp6(int temp) {
        controller.h3_temp6.setText(temp+"°");
    }


    @Override
    public void displayH3Temp7(int temp) {
        controller.h3_temp7.setText(temp+"°");
    }


    @Override
    public void displayH3Temp8(int temp) {
        controller.h3_temp8.setText(temp+"°");
    }


    @Override
    public void displayH3Time1(String temp) {
        controller.h3_time1.setText(temp);
    }


    @Override
    public void displayH3Time2(String temp) {
        controller.h3_time2.setText(temp);
    }


    @Override
    public void displayH3Time3(String temp) {
        controller.h3_time3.setText(temp);
    }


    @Override
    public void displayH3Time4(String temp) {
        controller.h3_time4.setText(temp);
    }


    @Override
    public void displayH3Time5(String temp) {
        controller.h3_time5.setText(temp);
    }


    @Override
    public void displayH3Time6(String temp) {
        controller.h3_time6.setText(temp);
    }


    @Override
    public void displayH3Time7(String temp) {
        controller.h3_time7.setText(temp);
    }


    @Override
    public void displayH3Time8(String temp) {
        controller.h3_time8.setText(temp);
    }

    // 5 day forcast
    @Override
    public void displayD1MaxTemp(int temp) {
        controller.f_max1.setText(temp+"°");
    }


    @Override
    public void displayD2MaxTemp(int temp) {
        controller.f_max2.setText(temp+"°");
    }


    @Override
    public void displayD3MaxTemp(int temp) {
        controller.f_max3.setText(temp+"°");
    }


    @Override
    public void displayD4MaxTemp(int temp) {
        controller.f_max4.setText(temp+"°");
    }


    @Override
    public void displayD5MaxTemp(int temp) {
       controller.f_max5.setText(temp+"°");
    }


    @Override
    public void displayD1MinTemp(int temp) {
        controller.f_min1.setText(temp+"°");
    }


    @Override
    public void displayD2MinTemp(int temp) {
        controller.f_max2.setText(temp+"°");
    }


    @Override
    public void displayD3MinTemp(int temp) {
       controller.f_max3.setText(temp+"°");
    }


    @Override
    public void displayD4MinTemp(int temp) {
        controller.f_max4.setText(temp+"°");
    }


    @Override
    public void displayD5MinTemp(int temp) {
        controller.f_max5.setText(temp+"°");
    }


    @Override
    public void displayD1Description(String temp) {
        //
    }


    @Override
    public void displayD2Description(String temp) {
        //controller.f_max1.setText(temp+"°");
    }


    @Override
    public void displayD3Description(String temp) {
       // controller.f_max1.setText(temp+"°");
    }


    @Override
    public void displayD4Description(String temp) {
        //controller.f_max1.setText(temp+"°");
    }


    @Override
    public void displayD5Description(String temp) {
        //controller.f_max1.setText(temp+"°");
    }


    @Override
    public void displayD1Date(String temp) {
        controller.f_date1.setText(temp+"°");
    }


    @Override
    public void displayD2Date(String temp) {
        controller.f_date2.setText(temp+"°");
    }


    @Override
    public void displayD3Date(String temp) {
        controller.f_date3.setText(temp+"°");
    }


    @Override
    public void displayD4Date(String temp) {
        controller.f_date4.setText(temp+"°");
    }


    @Override
    public void displayD5Date(String temp) {
        controller.f_date5.setText(temp+"°");
    }


    @Override
    public void displaySO2(Double temp) {
        controller.airLabels[0].setText(temp+"");
    }


    @Override
    public void displayNO2(Double temp) {
        controller.airLabels[1].setText(temp+"");
    }


    @Override
    public void displayO3(Double temp) {
        controller.airLabels[2].setText(temp+"");
    }


    @Override
    public void displayCO(Double temp) {
        controller.airLabels[3].setText(temp+"");    
    }


    @Override
    public void displayNO(Double temp) {
        controller.airLabels[4].setText(temp+"");  
    }


    @Override
    public void displayNH3(Double temp) {
        controller.airLabels[5].setText(temp+""); 
    }


    @Override
    public void displayPM10(Double temp) {
        controller.airLabels[6].setText(temp+"");  
    }


    @Override
    public void displayPM2_5(Double temp) {
        controller.airLabels[7].setText(temp+""); 
    }
}