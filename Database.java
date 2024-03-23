import java.sql.*;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import org.json.simple.parser.JSONParser;

public class Database {
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/FiveDayForecast", "root", "hamza_7871");
    }

    private static boolean cityExists(String city, Connection con) throws SQLException {
        String checkCitySql = "SELECT COUNT(*) FROM City WHERE City = ?";
        try (PreparedStatement pstmt = con.prepareStatement(checkCitySql)) {
            pstmt.setString(1, city);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private static void insertCity(String city, Connection con) throws SQLException {
        String insertCitySql = "INSERT INTO City (City) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(insertCitySql)) {
            pstmt.setString(1, city);
            pstmt.executeUpdate();
        }
    }

    public static void storeFiveDayData(String city, JSONObject fiveDayForecast) {
        try (Connection con = getConnection()) { // Assumes getConnection() provides a valid database connection
            // Check if the city exists in the City table
            if (!cityExists(city, con)) {
                // If the city does not exist, handle this case
                System.out.println("City does not exist in the database.");
                return;
            }
    
            JSONArray forecastArray = (JSONArray) fiveDayForecast.get("forecast");
    
            // Prepare the SQL statement for inserting five-day forecast data
            String insertSql = "INSERT INTO weatherforecasts (City, ForecastDate, MAX_TEMP, MIN_TEMP, Weather_Description) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                for (int i = 0; i < forecastArray.size(); i++) {
                    JSONObject forecast = (JSONObject) forecastArray.get(i);
                    // Extracting forecast data for each day
                    String date = (String) forecast.get("date");
                    double tempMax = (Double) forecast.get("temp_max");
                    double tempMin = (Double) forecast.get("temp_min");
                    String weatherDescription = (String) forecast.get("weather_description");
    
                    // Set the parameters for the prepared statement
                    pstmt.setString(1, city);
                    pstmt.setString(2, date);
                    pstmt.setDouble(3, tempMax);
                    pstmt.setDouble(4, tempMin);
                    pstmt.setString(5, weatherDescription);
    
                    // Execute the insert statement
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static JSONArray getFiveDaysData(String city) {
        JSONArray forecastsArray = new JSONArray(); // Use org.json.simple.JSONArray
        try (Connection con = getConnection()) {
            String sql = "SELECT City, ForecastDate, MAX_TEMP, MIN_TEMP, Weather_Description FROM WeatherForecasts WHERE City = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, city); // Set the city parameter
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        JSONObject forecastObject = new JSONObject(); // Use org.json.simple.JSONObject
                        forecastObject.put("City", rs.getString("City"));
                        forecastObject.put("ForecastDate", rs.getString("ForecastDate"));
                        forecastObject.put("MaxTemp", rs.getDouble("MAX_TEMP"));
                        forecastObject.put("MinTemp", rs.getDouble("MIN_TEMP"));
                        forecastObject.put("Description", rs.getString("Weather_Description"));
                        forecastsArray.add(forecastObject);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally, handle errors by adding a JSONObject to the array or handle differently
            JSONObject errorObject = new JSONObject();
            errorObject.put("error", e.getMessage());
            forecastsArray.add(errorObject); // Add the error information to the array
        }
        return forecastsArray; // Return the JSONArray directly
    }
    public static void storeThreeHourly(String city, JSONObject threeHourly) {
      
        try (Connection con = getConnection()) {
            // Check if the city exists in the City table
            if (!cityExists(city, con)) {
                // If the city does not exist, insert it into the City table
                insertCity(city, con);
            }
            JSONArray forecastArray = (JSONArray) threeHourly.get("forecast");
            
            // Prepare the SQL statement for inserting forecast data
            String insertSql = "INSERT INTO HourlyForecasts (City, Date_Time, Temperature) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                for (int i = 0; i < forecastArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) forecastArray.get(i);
                    // Assuming the forecast object contains keys "date_time" and "temperature"
                    String dateTime = (String) jsonObject.get("date_time"); // Correctly casted to String
                    double temperature = (Double) jsonObject.get("temperature"); // Correctly casted to Double
                    
                    pstmt.setString(1, city);
                    pstmt.setString(2, dateTime);
                    pstmt.setDouble(3, temperature);
                    
                    // Execute the insert statement
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
     public static JSONArray getThreeHourlyData(String city) {
        JSONArray forecastArray = new JSONArray();
        
        // SQL query to select forecast data for a specific city
        String selectSql = "SELECT Date_Time, Temperature FROM HourlyForecasts WHERE City = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            
            // Set the city parameter in the SQL query
            pstmt.setString(1, city);
            
            // Execute the query
            try (ResultSet rs = pstmt.executeQuery()) {
                // Iterate over the result set and add each forecast to the forecastArray
                while (rs.next()) {
                    JSONObject forecast = new JSONObject();
                    forecast.put("date_time", rs.getString("Date_Time"));
                    forecast.put("temperature", rs.getDouble("Temperature"));
                    forecastArray.add(forecast);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return forecastArray;
    }
    public static void storeGasesComponent(String city, JSONObject gasesComp) {
        try (Connection con = getConnection()) { // Assumes getConnection() provides a valid database connection
            // Check if the city exists in the City table
            if (!cityExists(city, con)) {
                // If the city does not exist, handle appropriately
                System.out.println("City does not exist in the database.");
                return;
            }
    
            // Prepare the SQL statement for inserting air pollutants data
            String insertSql = "INSERT INTO AirPollutantsData (City, NO, NO2, O3, SO2, PM2_5, PM_10, NH3, CO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                pstmt.setString(1, city);
                pstmt.setDouble(2, toDouble(gasesComp.get("no")));
                pstmt.setDouble(3, toDouble(gasesComp.get("no2")));
                pstmt.setDouble(4, toDouble(gasesComp.get("o3")));
                pstmt.setDouble(5, toDouble(gasesComp.get("so2")));
                pstmt.setDouble(6, toDouble(gasesComp.get("pm2_5")));
                pstmt.setDouble(7, toDouble(gasesComp.get("pm10")));
                pstmt.setDouble(8, toDouble(gasesComp.get("nh3")));
                pstmt.setDouble(9, toDouble(gasesComp.get("co")));
                
                // Execute the insert statement
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method to safely convert to Double
    private static double toDouble(Object value) {
        if (value != null) {
            return Double.parseDouble(value.toString());
        }
        return 0.0;
    }
    public static JSONArray getGasesComponent(String city) {
        JSONArray gasesArray = new JSONArray();
        
        // SQL query to select air pollutants data for a specific city
        String selectSql = "SELECT NO, NO2, O3, SO2, PM2_5, PM_10, NH3, CO FROM AirPollutantsData WHERE City = ?";
        
        try (Connection con = getConnection(); // Assumes getConnection() provides a valid database connection
             PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            
            // Set the city parameter in the SQL query
            pstmt.setString(1, city);
            
            // Execute the query
            try (ResultSet rs = pstmt.executeQuery()) {
                // Iterate over the result set and add each set of gas components to the gasesArray
                while (rs.next()) {
                    JSONObject gasesComponent = new JSONObject();
                    gasesComponent.put("no", rs.getDouble("NO"));
                    gasesComponent.put("no2", rs.getDouble("NO2"));
                    gasesComponent.put("o3", rs.getDouble("O3"));
                    gasesComponent.put("so2", rs.getDouble("SO2"));
                    gasesComponent.put("pm2_5", rs.getDouble("PM2_5"));
                    gasesComponent.put("pm10", rs.getDouble("PM_10"));
                    gasesComponent.put("nh3", rs.getDouble("NH3"));
                    gasesComponent.put("co", rs.getDouble("CO"));
                    gasesArray.add(gasesComponent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return gasesArray;
    }
    public static void storeAQIComponent(String city, long AQI) {
        // SQL statement to insert the AQI data
        String insertSql = "INSERT INTO AirQualityIndex (City, AQI) VALUES (?, ?)";
        
        try (Connection con = getConnection(); // Assumes getConnection() provides a valid database connection
             PreparedStatement pstmt = con.prepareStatement(insertSql)) {
            
            // Set the parameters for the prepared statement
            pstmt.setString(1, city);
            pstmt.setDouble(2, AQI); // Even though AQI is a long, it's safe to use setDouble for compatibility
            
            // Execute the insert statement
            pstmt.executeUpdate();
            
            System.out.println("AQI data stored successfully for " + city);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to store AQI data for " + city);
        }
    }
    public static double getAQIForCity(String city) {
        // SQL query to select the AQI for a specific city
        String selectSql = "SELECT AQI FROM AirQualityIndex WHERE City = ?";
        
        try (Connection con = getConnection(); // Assumes getConnection() provides a valid database connection
             PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            
            // Set the city parameter in the SQL query
            pstmt.setString(1, city);
            
            // Execute the query
            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if the result set has at least one row
                if (rs.next()) {
                    return rs.getDouble("AQI"); // Return the AQI value for the city
                } else {
                    System.out.println("No AQI data found for " + city);
                    return -1; // Indicate that no data was found
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to retrieve AQI data for " + city);
            return -1; // Indicate an error
        }
    }
    
    public static void main(String[] args) {
        String city = "Lahore"; // Replace "SampleCity" with the name of the city you want to query
        
        // Assuming getGasesComponent is defined in the same class
        JSONArray gasesComponents =getFiveDaysData(city);
       // double AQi=getAQIForCity(city);
        // Print the resulting JSONArray to the console
        System.out.println(gasesComponents);
    }
}
    
//     public static void main(String[] args) {
//         // Example usage of storeFiveDayData
//     //    storeFiveDayData("KARACHI", new Date(System.currentTimeMillis()), 30.00, 20.00, 25.00, "Sunny");
//     //     String city = "Lahore";
//  Scanner scanner = new Scanner(System.in);

//         System.out.println("Enter the city name to get the three-hourly forecast data:");
//         String city = scanner.nextLine();

//         JSONArray threeHourlyForecast = getThreeHourlyData(city);

//         if (threeHourlyForecast.isEmpty()) {
//             System.out.println("No forecast data found for " + city + ".");
//         } else {
//             System.out.println("Three-hourly forecast data for " + city + ":");
//             System.out.println(threeHourlyForecast.toJSONString());
//         }

//         scanner.close();
        // // Call getFiveDaysData to fetch the weather forecast data for Springfield.
        // // String forecastJson = getFiveDaysData(city);
        // // double max_t=(double)forecastJson.get("MaxTemp");
        // JSONArray forecastData = getFiveDaysData("Springfield");
        // JSONObject first=(JSONObject)forecastData.get(0);
        // Double max1 = (Double) first.get("MaxTemp");

        // System.out.println(max1); // Print the JSON array as a string
        // // Print the returned JSON string to the console.
        // // System.out.println("Weather forecast data for " + city + ":");
        // // System.out.println(forecastJson);
    
    

