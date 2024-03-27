import java.util.function.Consumer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaUI implements AbstractUI {

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

        // Settings
        primaryStage.setTitle("Weather App");
        primaryStage.setWidth(screenWidth * percentageOfUsedScreen);
        primaryStage.setHeight(screenHeight * percentageOfUsedScreen);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        // Show the stage
        primaryStage.show();
    }
    // Current Weather Data
    @Override
    public void displayCurrentTemp(int temp) {
        controller.temperatureLabel.setText(temp + "°");
    }

    @Override
    public void displayMinTemp(int temp) {
        controller.c_mintemp.setText(temp + "°");
    }

    @Override
    public void displayMaxTemp(int temp) {
        controller.c_maxtemp.setText(temp + "°");
    }

    @Override
    public void displayHumidity(int temp) {
        controller.humiditytxt.setText(temp + "%");
    }

    @Override
    public void displayFeelsLike(int temp) {
        controller.feelLabel.setText("Feels like " + temp + "°");
    }

    @Override
    public void displayCurrentDescription(String temp) {
        controller.weatherDes.setText(temp);

        Image image = getImageForDescription(temp);
        controller.weatherIcon.setImage(image);
    }

    @Override
    public void displaySunRiseTime(String temp) {
        controller.sunRisetxt.setText(temp + "");
    }

    @Override
    public void displaySunSetTime(String temp) {
        controller.sunSettxt.setText(temp);
    }

    // 3 hourly data

    @Override
    public void displayH3Temp(int[] temp) {
        for (int i = 0; i < temp.length && i < controller.h3_tempLabels.length; i++) {
            controller.h3_tempLabels[i].setText(temp[i] + "°");
        }
    }

    @Override
    public void displayH3Time(String[] times) {
        for (int i = 0; i < times.length && i < controller.h3_timeLabels.length; i++) {
            controller.h3_timeLabels[i].setText(times[i]);
        }
    }

    // 5 day forcast
    @Override
    public void displayDMaxTemp(int[] temps) {
        for (int i = 0; i < temps.length && i < controller.f_maxLabels.length; i++) {
            controller.f_maxLabels[i].setText(temps[i] + "°");
        }
    }

    @Override
    public void displayDMinTemp(int[] temps) {
        for (int i = 0; i < temps.length && i < controller.f_minLabels.length; i++) {
            controller.f_minLabels[i].setText(temps[i] + "°");
        }
    }

    @Override
    public void displayDDescription(String[] descriptions) {
        for (int i = 0; i < descriptions.length && i < controller.f_disc.length; i++) {

            Image image = getImageForDescription(descriptions[i]);
            controller.f_disc[i].setImage(image);

            // rain
            // cloud
            // scattered clouds
        }
    }

    public Image getImageForDescription(String description) {

        if (description.equals("shower rain")) {
            description = "shower";
        } else if (description.equals("broken clouds")) {
            description = "broken";
        }


        if (description.toLowerCase().contains("sky")) {
            return new Image(getClass().getResourceAsStream("clear sky.png"));
        } else if (description.toLowerCase().contains("clouds")) {
            return new Image(getClass().getResourceAsStream("scattered cloud.png"));
        } else if (description.toLowerCase().contains("broken")) {
            return new Image(getClass().getResourceAsStream("broken clouds.png"));
        } else if (description.toLowerCase().contains("shower")) {
            return new Image(getClass().getResourceAsStream("shower rain.png"));
        } else if (description.toLowerCase().contains("rain")) {
            return new Image(getClass().getResourceAsStream("rain.png"));
        } else if (description.toLowerCase().contains("thunderstorm")) {
            return new Image(getClass().getResourceAsStream("thunderstorm.png"));
        } else if (description.toLowerCase().contains("mist")) {
            return new Image(getClass().getResourceAsStream("mist.png"));
        } else if (description.toLowerCase().contains("snow")) {
            return new Image(getClass().getResourceAsStream("snow.png"));
        } else if (description.toLowerCase().contains("windy")) {
            return new Image(getClass().getResourceAsStream("windy.png"));
        } else {
            return new Image(getClass().getResourceAsStream("scattered cloud.png"));
        }
    }

    @Override
    public void displayDDates(String[] dates) {
        for (int i = 0; i < dates.length && i < controller.f_dateLabels.length; i++) {
            controller.f_dateLabels[i].setText(dates[i]);
        }
    }

    // Gases Data
    @Override
    public void displayGases(Double[] temp) {
        for (int i = 0; i < temp.length && i < controller.airData.length; i++) {
            controller.airData[i].setText(temp[i] + "");
        }
    }

    @Override
    public void displayAirQualityIndex(int temp) {

        controller.aqi_label.setText(temp + "");
    }

    @Override
    public void userInputFromTextBox(Consumer<String> userInputHandler) {

        controller.searchButton.setOnAction(event -> {
            String userInput = controller.textfieldLocation.getText();
            userInputHandler.accept(userInput);
            controller.locationLabel.setText(userInput);
            controller.textfieldLocation.setText("");
        });
    }

    @Override
    public void userInputFromMap(Consumer<String> userInputHandler) {

        controller.okButton.setOnAction(event -> {

            String line=readLocationFromFile.readFile();
            System.out.println(line);
            userInputHandler.accept(line);
            controller.locationLabel.setText(line);
        });
    }
}