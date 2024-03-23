//import org.json.JSONArray;
import java.sql.*;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.*;
public class weatherData {
    int currentTemp;
    String[] threeHourlyTime = new String[8];
    int[] threeHourlyTemp = new int[8];
    String city = "";

    public weatherData(){
        this.city="Karachi";
    }
    public void setThreeHourly(String city, JSONArray jsonArray) {
        this.city = city;
    
        // Iterate through the JSONArray
        for (int i = 0; i < jsonArray.size(); i++) {
            // Get the current JSONObject
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
    
            // Extract the date_time and temperature values
            String dateTime = (String) jsonObject.get("date_time");
            // Retrieve temperature as Double and cast to int for storage
            double temperature = (Double) jsonObject.get("temperature");
    
            // Split the date_time string to extract the time part
            String time = dateTime.split(" ")[1];
            threeHourlyTime[i] = time;
    
            // Store the temperature as an integer
            threeHourlyTemp[i] = (int) temperature;
        }
    }

    public int[] getTemp(){
        return this.threeHourlyTemp;
    }
    public String[] getTimes(){
        return this.threeHourlyTime;
    }
    public String getCity(){
        return this.city;
    }
    // public static void main(String[] args) {
    //     weatherData data = new weatherData("Lahore");
    //   //  data.getThreeHourly(""); // Use a real city name or keep it as is for testing

    //     // Print the results
    //     System.out.println("Three Hourly Time:");
    //     for (String time : data.threeHourlyTime) {
    //         System.out.println(time);
    //     }
    //     System.out.println("\nThree Hourly Temperature:");
    //     for (int temp : data.threeHourlyTemp) {
    //         System.out.println(temp);
    //     }
    // }
}