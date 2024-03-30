package modules;
import javafx.scene.image.Image;

public class DescriptionImage {

    public static Image getImageForDescription(String description) {

        if (description.equals("shower rain")) {
            description = "shower";
        } else if (description.equals("broken clouds")) {
            description = "broken";
        }

        if (description.toLowerCase().contains("sky")) {
            return new Image(DescriptionImage.class.getResourceAsStream("img/clear sky.png"));
        } else if (description.toLowerCase().contains("clouds")) {
            return new Image(DescriptionImage.class.getResourceAsStream("img/scattered cloud.png"));
        } else if (description.toLowerCase().contains("broken")) {
            return new Image(DescriptionImage.class.getResourceAsStream("img/broken clouds.png"));
        } else if (description.toLowerCase().contains("shower")) {
            return new Image(DescriptionImage.class.getResourceAsStream("img/shower rain.png"));
        } else if (description.toLowerCase().contains("rain")) {
            return new Image(DescriptionImage.class.getResourceAsStream("img/rain.png"));
        } else if (description.toLowerCase().contains("thunderstorm")) {
            return new Image(DescriptionImage.class.getResourceAsStream("img/thunderstorm.png"));
        } else if (description.toLowerCase().contains("mist")) {
            return new Image(DescriptionImage.class.getResourceAsStream("img/mist.png"));
        } else if (description.toLowerCase().contains("snow")) {
            return new Image(DescriptionImage.class.getResourceAsStream("img/snow.png"));
        } else if (description.toLowerCase().contains("windy")) {
            return new Image(DescriptionImage.class.getResourceAsStream("img/windy.png"));
        } else {
            return new Image(DescriptionImage.class.getResourceAsStream("img/scattered cloud.png"));
        }
    }
}
