import java.sql.*;
import java.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Database implements AbstractDB{

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
        try (Connection con =SQLConnection.getConnection()) { 
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
 

    @SuppressWarnings("unchecked")
    @Override
    public JSONArray getFiveDaysData(String city) {
        JSONArray forecastsArray = new JSONArray(); 
        try (Connection con = SQLConnection.getConnection()) {
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
                        forecastObject.put("date", rs.getString("ForecastDate"));
                        forecastObject.put("temp_max", rs.getDouble("MAX_TEMP"));
                        forecastObject.put("temp_min", rs.getDouble("MIN_TEMP"));
                        forecastObject.put("weather_description", rs.getString("Weather_Description"));
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
      
        try (Connection con = SQLConnection.getConnection()) {
           
            if (!cityExists(city, con)) {
                insertCity(city, con);
            }
            JSONArray forecastArray = (JSONArray) threeHourly.get("forecast");
            
            String insertSql = "INSERT INTO HourlyForecasts (City, Date_Time, Temperature) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                for (int i = 0; i < forecastArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) forecastArray.get(i);
                    String dateTime = (String) jsonObject.get("date_time"); 
                    double temperature = (Double) jsonObject.get("temperature");
                    
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
     @SuppressWarnings("unchecked")
    @Override
     public  JSONArray getThreeHourlyData(String city) {
        JSONArray forecastArray = new JSONArray();
        
        String selectSql = "SELECT Date_Time, Temperature FROM HourlyForecasts WHERE City = ?";
        
        try (Connection con = SQLConnection.getConnection();
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
        try (Connection con = SQLConnection.getConnection()) { 
            if (!cityExists(city, con)) {
                insertCity(city, con);
            }
    
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
                
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static double toDouble(Object value) {
        if (value != null) {
            return Double.parseDouble(value.toString());
        }
        return 0.0;
    }
    @SuppressWarnings("unchecked")
    @Override
    public JSONArray getGasesComponent(String city) {
        JSONArray gasesArray = new JSONArray();
        
        String selectSql = "SELECT NO, NO2, O3, SO2, PM2_5, PM_10, NH3, CO FROM AirPollutantsData WHERE City = ?";
        
        try (Connection con = SQLConnection.getConnection(); 
         PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            
            pstmt.setString(1, city);
            if (!cityExists(city, con)) {
                System.out.println("City does not exist in the database.");
                return gasesArray;
            }
            try (ResultSet rs = pstmt.executeQuery()) {
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
        String insertSql = "INSERT INTO AirQualityIndex (City, AQI) VALUES (?, ?)";
        
        try (Connection con = SQLConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(insertSql)) {
            
            pstmt.setString(1, city);
            pstmt.setDouble(2, AQI); 
            // Execute the insert statement
            pstmt.executeUpdate();
            
          //  System.out.println("AQI data stored successfully for " + city);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to store AQI data for " + city);
        }
    }
    @Override
    public  double getAQIForCity(String city) {
        String selectSql = "SELECT AQI FROM AirQualityIndex WHERE City = ?";
        
        try (Connection con = SQLConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(selectSql)) {
                if (!cityExists(city, con)) {
                    System.out.println("City does not exist in the database.");
                    return 0;
                }
            pstmt.setString(1, city);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("AQI"); 
                } else {
                    System.out.println("No AQI data found for " + city);
                    return -1; // Indicate that no data was found
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to retrieve AQI data for " + city);
            return -1; 
        }
    }
    

    /////////////////////////
public static void checkAndCleanDatabase() throws SQLException, ClassNotFoundException {
    try (Connection conn = SQLConnection.getConnection();
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
            try (Connection conn = SQLConnection.getConnection()) {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
            throw e; 
        }
}

    // public static void main(String[] args) {
    //     String city = "Lahore"; // Replace "SampleCity" with the name of the city you want to query
    //     Database d1=new Database();
    //     // Assuming getGasesComponent is defined in the same class
    //     JSONArray gasesComponents =d1.getFiveDaysData(city);
    //     if(gasesComponents.isEmpty()){
    //         System.out.println("Hamza");    
    //     }
    //    // double AQi=getAQIForCity(city);
    //     // Print the resulting JSONArray to the console
    //     System.out.println(gasesComponents);
    // }
}