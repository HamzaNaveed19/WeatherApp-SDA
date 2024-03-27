import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readLocationFromFile {
    public static String readFile() {
        String filePath = "C:/Users/tower/OneDrive/Desktop/Fast-Nu/4th Semester/4. SDA/Project/testjavafx/firstjavafx/src/value.txt"; // Path
        String cityName="";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                cityName=line;
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return cityName;
    }
}
