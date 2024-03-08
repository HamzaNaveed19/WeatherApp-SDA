package saif;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 * Hello world!
 *
 */
public class App 
{

/**
 * @param locationName
 * @return
 */
public static JSONObject getWeatherData(String locationName){

    JSONObject locationData= getLocationData(locationName);
    double latitude=(double)locationData.get("latitude");
    double longitude=(double)locationData.get("longitude");
    String apiKey="7d888618e8c1a5f4afaead050a345b88";

    String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;

try{
    HttpURLConnection conn=fetchAPIResponse(apiUrl);
    
    if(conn.getResponseCode() != 200){
        System.out.println("Error: Could not connect to API");
        return null;
    }

    StringBuilder resultJson = new StringBuilder();
    Scanner scanner = new Scanner(conn.getInputStream());
            while(scanner.hasNext()){
                resultJson.append(scanner.nextLine());
            }

            scanner.close();

            conn.disconnect();
    JSONParser parser = new JSONParser();
    JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));


     return resultJsonObj;
}
catch(Exception e){
     e.printStackTrace();
}

return null;

}
public static JSONObject getCurrWeatherData(String locationName){

    JSONObject locationData= getLocationData(locationName);
    double latitude=(double)locationData.get("latitude");
    double longitude=(double)locationData.get("longitude");
    String apiKey="7d888618e8c1a5f4afaead050a345b88";

    String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;

try{
    HttpURLConnection conn=fetchAPIResponse(apiUrl);
    
    if(conn.getResponseCode() != 200){
        System.out.println("Error: Could not connect to API");
        return null;
    }

    StringBuilder resultJson = new StringBuilder();
    Scanner scanner = new Scanner(conn.getInputStream());
            while(scanner.hasNext()){
                resultJson.append(scanner.nextLine());
            }

            scanner.close();

            conn.disconnect();
    JSONParser parser = new JSONParser();
    JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));


     return resultJsonObj;
}
catch(Exception e){
     e.printStackTrace();
}

return null;

}
@SuppressWarnings("unchecked")
public static JSONObject getFiveDayForecast(String locationName) {
    JSONObject jsonObject=getWeatherData(locationName);
    JSONObject forecastData = new JSONObject();
    JSONArray forecastList = new JSONArray();

    JSONArray list = (JSONArray) jsonObject.get("list");

    for (int i = 0; i < list.size(); i += 8) {
        JSONObject weatherData = (JSONObject) list.get(i);

        // Extracting date
        String date = (String) weatherData.get("dt_txt").toString().substring(0, 10);

        // Extracting temperature
        JSONObject main = (JSONObject) weatherData.get("main");
        double temperature = (double) main.get("temp");
        temperature=(temperature-273);

        // Extracting humidity
        long humidity = (long) main.get("humidity");

        // Extracting feels like temperature
        double feelsLike = (double) main.get("feels_like");

        // Create JSON object for the day's forecast
        JSONObject dayForecast = new JSONObject();
        dayForecast.put("date", date);
        dayForecast.put("temperature", temperature);
        dayForecast.put("humidity", humidity);
        dayForecast.put("feels_like", feelsLike);

        // Add day's forecast to the list
        forecastList.add(dayForecast);
    }

    // Add forecast list to the main JSON object
    forecastData.put("forecast", forecastList);

    return forecastData;
}
public static long getCurrentHumidity(String locationName) {

        JSONObject jsonObject = getCurrWeatherData(locationName);
        JSONObject mainObject =(JSONObject) jsonObject.get("main");
        return  (long)mainObject.get("humidity");
    }
public static double getCurrentTemperature(String locationName) {
    JSONObject jsonObject = getCurrWeatherData(locationName);
    JSONObject mainObject =(JSONObject) jsonObject.get("main");
    double temperature=(double) mainObject.get("temp");
    return (int)(temperature-273);
}
public static double getMinimumTemperature(String locationName) {
    JSONObject jsonObject = getCurrWeatherData(locationName);
    JSONObject mainObject =(JSONObject) jsonObject.get("main");
    double temperature=(double) mainObject.get("temp_min");
    return (int)(temperature-273);
}
public static double getMaximumTemperature(String locationName) {
    JSONObject jsonObject = getCurrWeatherData(locationName);
    JSONObject mainObject =(JSONObject) jsonObject.get("main");
    double temperature=(double) mainObject.get("temp_max");
    return (int)(temperature-273);
}
public static long getSunriseTime(String locationName) {
    JSONObject jsonObject = getCurrWeatherData(locationName);
    JSONObject sysObject = (JSONObject) jsonObject.get("sys");
    return (long)sysObject.get("sunrise");
}

public static long getSunsetTime(String locationName) {
    JSONObject jsonObject = getCurrWeatherData(locationName);
    JSONObject sysObject =(JSONObject) jsonObject.get("sys");
    return (long)sysObject.get("sunset");
}

public static String getWeatherDescription(String locationName) {
    JSONObject jsonObject = getCurrWeatherData(locationName);
    JSONArray weatherArray = (JSONArray)jsonObject.get("weather");
    JSONObject weatherObject = (JSONObject)weatherArray.get(0);
    return (String)weatherObject.get("description");
}

public static double getFeelsLikeTemperature(String locationName) {
    JSONObject jsonObject = getCurrWeatherData(locationName);
    JSONObject mainObject = (JSONObject)jsonObject.get("main");
    return (double)mainObject.get("feels_like");
}

@SuppressWarnings("unchecked")
public static JSONObject getThreeHourlyForecast(String locationName) {
    JSONObject jsonObject =getWeatherData(locationName);

    JSONObject forecastData = new JSONObject();
    JSONArray forecastList = new JSONArray();

    JSONArray list = (JSONArray) jsonObject.get("list");
    

    for (int i = 0; i < 8; i++) {
        JSONObject weatherData = (JSONObject) list.get(i);

        // Extracting date and time
        String dateTimeString = (String) weatherData.get("dt_txt");

      
            // Extracting temperature
            JSONObject main = (JSONObject) weatherData.get("main");
            double temperature = (double) main.get("temp");
            temperature=(int)(temperature-273.15);


            // Extracting humidity
            long humidity = (long) main.get("humidity");

 

            // Create JSON object for the forecast
            JSONObject hourlyForecast = new JSONObject();
            hourlyForecast.put("date_time", dateTimeString);
            hourlyForecast.put("temperature", temperature);
            hourlyForecast.put("humidity", humidity);

            // Add forecast to the list
            forecastList.add(hourlyForecast);
        
    }

    // Add forecast list to the main JSON object
    forecastData.put("forecast", forecastList);

    return forecastData;
}



public static JSONObject getLocationData(String locationName){
    locationName=locationName.replaceAll(" ","+");
String urlString="https://geocoding-api.open-meteo.com/v1/search?name="+ locationName + "&count=10&language=en&format=json";
      try{
          HttpURLConnection conn=fetchAPIResponse(urlString);
          if(conn.getResponseCode()!=200){
            System.err.println("Error : Could Not Connect To API ");
            return null;
          }
          else{
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());

            while(scanner.hasNext()){
                resultJson.append(scanner.nextLine());
            }

            scanner.close();

            conn.disconnect();

             JSONParser parser = new JSONParser();
             JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

             JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
             return (JSONObject)locationData.get(0);


          }
      }catch(Exception e){
    e.printStackTrace();
}
return null;

}
private static HttpURLConnection fetchAPIResponse(String urlString){
        try{
            // attempt to create connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // set request method to get
            conn.setRequestMethod("GET");

            // connect to our API
            conn.connect();
            return conn;
        }catch(IOException e){
            e.printStackTrace();
        }

        // could not make connection
        return null;
    }
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Enter Your City Name: " );
        String locationName = scanner.nextLine();
       JSONObject weather= App.getWeatherData(locationName);

        scanner.close();

        System.out.println(App.getMaximumTemperature(locationName));
        System.out.println(App.getMinimumTemperature(locationName));


    }

}
