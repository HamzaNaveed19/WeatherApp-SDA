import java.sql.*;
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

    public static void storeFiveDayData(String city, Date forecastDate, 
                                        double maxTemp, double minTemp, 
                                        double feelsLike, String weatherDesc) {
  try (Connection con = getConnection()) {
            // Check if the city exists in the City table
            if (!cityExists(city, con)) {
                // If the city does not exist, insert it into the City table
                insertCity(city, con);
            }
            // Prepare the SQL statement for inserting weather forecast data
            String sql = "INSERT INTO WeatherForecasts (City, ForecastDate, MAX_TEMP, MIN_TEMP, FeelsLikeTemperature, Weather_Description) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                // Set the parameters
                pstmt.setString(1, city);
                pstmt.setDate(2, forecastDate);
                pstmt.setDouble(3, maxTemp);
                pstmt.setDouble(4, minTemp);
                pstmt.setDouble(5, feelsLike);
                pstmt.setString(6, weatherDesc);
                
                // Execute the statement
                pstmt.executeUpdate();
                System.out.println("Weather data inserted successfully for city: " + city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static JSONArray getFiveDaysData(String city) {
        JSONArray forecastsArray = new JSONArray(); // Use org.json.simple.JSONArray
        try (Connection con = getConnection()) {
            String sql = "SELECT City, ForecastDate, MAX_TEMP, MIN_TEMP, FeelsLikeTemperature, Weather_Description FROM WeatherForecasts WHERE City = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, city); // Set the city parameter
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        JSONObject forecastObject = new JSONObject(); // Use org.json.simple.JSONObject
                        forecastObject.put("City", rs.getString("City"));
                        forecastObject.put("ForecastDate", rs.getString("ForecastDate"));
                        forecastObject.put("MaxTemp", rs.getDouble("MAX_TEMP"));
                        forecastObject.put("MinTemp", rs.getDouble("MIN_TEMP"));
                        forecastObject.put("FeelsLike", rs.getDouble("FeelsLikeTemperature"));
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
    
    
    public static void main(String[] args) {
        // Example usage of storeFiveDayData
       storeFiveDayData("KARACHI", new Date(System.currentTimeMillis()), 30.00, 20.00, 25.00, "Sunny");
        String city = "Lahore";

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
    
    }
}
