import java.sql.*;
import java.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Database implements AbstractDB{
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/FiveDayForecast", "root", "ahad123");
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
    @Override
    public void storeFiveDayData(String city, JSONObject fiveDayForecast) {
        try (Connection con = getConnection()) { 
            checkAndCleanDatabase();
            if (!cityExists(city, con)) {
                insertCity(city, con);
            }
    
            JSONArray forecastArray = (JSONArray) fiveDayForecast.get("forecast");
    
            String insertSql = "INSERT INTO weatherforecasts (City, ForecastDate, MAX_TEMP, MIN_TEMP, Weather_Description) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                for (int i = 0; i < forecastArray.size(); i++) {
                    JSONObject forecast = (JSONObject) forecastArray.get(i);
                    String date = (String) forecast.get("date");
                    double tempMax = (Double) forecast.get("temp_max");
                    double tempMin = (Double) forecast.get("temp_min");
                    String weatherDescription = (String) forecast.get("weather_description");
    
                    pstmt.setString(1, city);
                    pstmt.setString(2, date);
                    pstmt.setDouble(3, tempMax);
                    pstmt.setDouble(4, tempMin);
                    pstmt.setString(5, weatherDescription);
    
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public JSONArray getFiveDaysData(String city) {
        JSONArray forecastsArray = new JSONArray(); 
        try (Connection con = getConnection()) {
            checkAndCleanDatabase();
            if (!cityExists(city, con)) {
                
                System.out.println("City does not exist in the database.");
                return forecastsArray;
            }
            String sql = "SELECT City, ForecastDate, MAX_TEMP, MIN_TEMP, Weather_Description FROM WeatherForecasts WHERE City = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, city); 
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        JSONObject forecastObject = new JSONObject(); 
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
            JSONObject errorObject = new JSONObject();
            errorObject.put("error", e.getMessage());
            forecastsArray.add(errorObject); 
        }
        return forecastsArray; 
    }
    @Override
    public  void storeThreeHourly(String city, JSONObject threeHourly) {
      
        try (Connection con = getConnection()) {
            if (!cityExists(city, con)) {
                insertCity(city, con);
            }
            JSONArray forecastArray = (JSONArray) threeHourly.get("forecast");
            
            String insertSql = "INSERT INTO HourlyForecasts (City, Date_Time, Temperature) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                for (int i = 0; i < forecastArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) forecastArray.get(i);
                    String dateTime = (String) jsonObject.get("date_time"); // Correctly casted to String
                    double temperature = (Double) jsonObject.get("temperature"); // Correctly casted to Double
                    
                    pstmt.setString(1, city);
                    pstmt.setString(2, dateTime);
                    pstmt.setDouble(3, temperature);
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
     @Override
     public  JSONArray getThreeHourlyData(String city) {
        JSONArray forecastArray = new JSONArray();
       
        String selectSql = "SELECT Date_Time, Temperature FROM HourlyForecasts WHERE City = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(selectSql)) {
                if (!cityExists(city, con)) {
                    System.out.println("City does not exist in the database.");
                    return forecastArray;
                }
            pstmt.setString(1, city);
            
            try (ResultSet rs = pstmt.executeQuery()) {
            
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
    @Override
    public void storeGasesComponent(String city, JSONObject gasesComp) {
        try (Connection con = getConnection()) { 
            if (!cityExists(city, con)) {
                insertCity(city, con);
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
    @Override
    public JSONArray getGasesComponent(String city) {
        JSONArray gasesArray = new JSONArray();
        
        // SQL query to select air pollutants data for a specific city
        String selectSql = "SELECT NO, NO2, O3, SO2, PM2_5, PM_10, NH3, CO FROM AirPollutantsData WHERE City = ?";
        
        try (Connection con = getConnection(); // Assumes getConnection() provides a valid database connection
             PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            
            // Set the city parameter in the SQL query
            pstmt.setString(1, city);
            if (!cityExists(city, con)) {
                // If the city does not exist, handle appropriately
                System.out.println("City does not exist in the database.");
                return gasesArray;
            }
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
    @Override
    public  void storeAQIComponent(String city, long AQI) {
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
    @Override
    public  double getAQIForCity(String city) {
        // SQL query to select the AQI for a specific city
        String selectSql = "SELECT AQI FROM AirQualityIndex WHERE City = ?";
        
        try (Connection con = getConnection(); // Assumes getConnection() provides a valid database connection
             PreparedStatement pstmt = con.prepareStatement(selectSql)) {
                if (!cityExists(city, con)) {
                    // If the city does not exist, handle appropriately
                    System.out.println("City does not exist in the database.");
                    return 0;
                }
            // Set the city parameter in the SQL query
            pstmt.setString(1, city);
            
            // Execute the query
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("AQI"); 
                    System.out.println("No AQI data found for " + city);
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to retrieve AQI data for " + city);
            return -1;
        }
    }

public static void checkAndCleanDatabase() throws SQLException, ClassNotFoundException {
    try (Connection conn = getConnection();
          Statement stmt = conn.createStatement()) {

          conn.setAutoCommit(false);
    
          ResultSet rs = stmt.executeQuery("SELECT savedDate FROM StoredDate LIMIT 1");
          LocalDate currentDate = LocalDate.now();
          if (rs.next()) {

          java.sql.Date storedDate = rs.getObject("savedDate", java.sql.Date.class);


                
        
                if (!storedDate.toLocalDate().equals(currentDate)) {
            
                    int deletedCityRows = stmt.executeUpdate("DELETE FROM City");
                    System.out.println("Deleted rows from City table: " + deletedCityRows);
                
                 
                    int deletedDateRows = stmt.executeUpdate("DELETE FROM StoredDate");
                    System.out.println("Deleted rows from StoredDate table: " + deletedDateRows);
                
                    PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO StoredDate (savedDate) VALUES (?)");

                    insertStmt.setDate(1, java.sql.Date.valueOf(currentDate));


                   
                    insertStmt.executeUpdate();
                    System.out.println("Inserted current date into StoredDate table.");
                } else {
                    System.out.println("The dates match. No action taken.");
                }
                
            } else {
                System.out.println("No stored date found.");
                
                PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO StoredDate (savedDate) VALUES (?)");

                insertStmt.setDate(1, java.sql.Date.valueOf(currentDate));

                insertStmt.executeUpdate();
                System.out.println("Inserted current date into StoredDate table.");
            }
            
            conn.commit();
            
            conn.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException e) {
           
            System.err.println("An error occurred, rolling back changes.");
            try (Connection conn = getConnection()) {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
            throw e; // Rethrow the exception after handling
        }
}

}