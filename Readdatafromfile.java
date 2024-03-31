import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Readdatafromfile {
    public static void main(String[] args) {
        String filePath = "C:/Users/tower/OneDrive/Desktop/Fast-Nu/4th Semester/4. SDA/Project/testjavafx/firstjavafx/src/value.txt"; // Path to the value.txt file

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Contents of value.txt:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
