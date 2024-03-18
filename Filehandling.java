import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Filehandling {

    
    public JSONObject jsonFile() {
        // Provided forecast data
        String forecastDataString = "{\"forecast\":[{\"date_time\":\"2024-03-15 18:00:00\", \"temperature\": 18.0, \"humidity\":28},{\"date_time\":\"2024-03-15 21:00:00\", \"temperature\":15.0, \"humidity\": 33},{\"date_time\":\"2024-03-16 00:00:00\", \"temperature\": 12.0, \"humidity\":39},{\"date_time\":\"2024-03-16 03:00:00\",\"temperature\":18.0, \"humidity\":26},{\"date_time\":\"2024-03-16 06:00:00\", \"temperature\":28.0, \"humidity\":12},{\"date_time\":\"2024-03-16 09:00:00\", \"temperature\":31.0, \"humidity\":10},{\"date_time\":\"2024-03-16 12:00:00\", \"temperature\":29.0, \"humidity\":10},{\"date_time\":\"2024-03-16 15:00:00\", \"temperature\": 20.0, \"humidity\":19}]}";

        try {
            // Parse the forecast data string into a JSON object
            JSONParser parser = new JSONParser();
            JSONObject forecastData = (JSONObject) parser.parse(forecastDataString);

            // Return the JSON object containing the forecast data
            return forecastData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static class FileHandler {
    
        public void createFile(String fileName) {
            File file = new File(fileName);
            
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + fileName);
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
        
        public void writeFile(String fileName, JSONObject forecastData) {
            JSONArray forecastArray = (JSONArray) forecastData.get("forecast");
            StringBuilder data = new StringBuilder();
        
            for (Object obj : forecastArray) {
                JSONObject forecastEntry = (JSONObject) obj;
                String date_time = (String) forecastEntry.get("date_time");
                double temperature = (double) forecastEntry.get("temperature");
                data.append("Date/Time: ").append(date_time).append(", Temperature: ").append(temperature).append("\n");
            }
        
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(data.toString());
                System.out.println("Data has been written to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }
        
        public void readFile(String fileName) {
            int i = 0;
            
            try (FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    i++;
                    System.out.println(i + ": " + line); // Print each line to the console
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
    
        Filehandling f = new Filehandling();
        JSONObject fbj = f.jsonFile();
        String fileName = "weatherData.txt";
        FileHandler fileHandler = new FileHandler();
    
        fileHandler.createFile(fileName);
        fileHandler.writeFile(fileName, fbj);
        fileHandler.readFile(fileName);
        
    }
}
