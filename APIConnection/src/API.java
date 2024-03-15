import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class API
{
  private String locationName;
  JSONObject weatherData=new JSONObject();
  JSONObject currWeatherData=new JSONObject();


 API(String locationName){
    this.locationName=locationName;
    }
    

public JSONObject getWeatherData(){

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
public JSONObject getCurrWeatherData(){

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
public JSONObject getFiveDayForecast() {
    if(weatherData.toString().equals("{}")){
        weatherData=getWeatherData();
    }

    JSONObject forecastData = new JSONObject();
    JSONArray forecastList = new JSONArray();

    JSONArray list = (JSONArray) weatherData.get("list");

    for (int i = 0; i < list.size(); i += 8) {
        JSONObject fiveDayData = (JSONObject) list.get(i);

        String date = (String) fiveDayData.get("dt_txt").toString().substring(0, 10);

        JSONObject main = (JSONObject) fiveDayData.get("main");
        double temperature = (double) main.get("temp");
        temperature=(temperature-273);

        long humidity = (long) main.get("humidity");

        double feelsLike = (double) main.get("feels_like");

        JSONObject dayForecast = new JSONObject();
        dayForecast.put("date", date);
        dayForecast.put("temperature", temperature);
        dayForecast.put("humidity", humidity);
        dayForecast.put("feels_like", feelsLike);

      forecastList.add(dayForecast);
    }

  forecastData.put("forecast", forecastList);

    return forecastData;
}
@SuppressWarnings("unchecked")
public JSONObject getThreeHourlyForecast(String locationName) {
    
    if(weatherData.toString().equals("{}")){
        weatherData=getWeatherData();
    }
    JSONObject forecastData = new JSONObject();
    JSONArray forecastList = new JSONArray();

    JSONArray list = (JSONArray) weatherData.get("list");
    

    for (int i = 0; i < 8; i++) {
        JSONObject threeHourData = (JSONObject) list.get(i);

        String dateTimeString = (String) threeHourData.get("dt_txt");

      
            JSONObject main = (JSONObject) threeHourData.get("main");
            double temperature = (double) main.get("temp");
            temperature=(int)(temperature-273.15);


            long humidity = (long) main.get("humidity");

 

            JSONObject hourlyForecast = new JSONObject();
            hourlyForecast.put("date_time", dateTimeString);
            hourlyForecast.put("temperature", temperature);
            hourlyForecast.put("humidity", humidity);

            forecastList.add(hourlyForecast);
        
    }

    forecastData.put("forecast", forecastList);

    return forecastData;
}
public long getCurrentHumidity() {
    if(currWeatherData.toString().equals("{}")){
        currWeatherData=getCurrWeatherData();
    }
        JSONObject mainObject =(JSONObject) currWeatherData.get("main");
        return  (long)mainObject.get("humidity");
    }
public double getCurrentTemperature() {
    
    if(currWeatherData.toString().equals("{}")){
        currWeatherData=getCurrWeatherData();
    }

    JSONObject mainObject =(JSONObject) currWeatherData.get("main");
    double temperature=(double) mainObject.get("temp");
    return (int)(temperature-273);
}
public double getMinimumTemperature() {

    if(currWeatherData.toString().equals("{}")){
        currWeatherData=getCurrWeatherData();
    }

    JSONObject mainObject =(JSONObject) currWeatherData.get("main");
    double temperature=(double) mainObject.get("temp_min");
    return (int)(temperature-273);
}
public double getMaximumTemperature() {
    
    if(currWeatherData.toString().equals("{}")){
        currWeatherData=getCurrWeatherData();
    }

    JSONObject mainObject =(JSONObject) currWeatherData.get("main");
    double temperature=(double) mainObject.get("temp_max");
    return (int)(temperature-273);
}
public LocalDateTime getSunriseTime() {
    
    
    if(currWeatherData.toString().equals("{}")){
        currWeatherData=getCurrWeatherData();
    }

           JSONObject sysObject = (JSONObject) currWeatherData.get("sys");
            long sunsetUnixTimestamp =(long) sysObject.get("sunrise");
            
            Instant instant = Instant.ofEpochSecond(sunsetUnixTimestamp);
            return LocalDateTime.ofInstant (instant, ZoneOffset.UTC);

}

public LocalDateTime getSunsetTime() {
    
    
    if(currWeatherData.toString().equals("{}")){
        currWeatherData=getCurrWeatherData();
    }

           JSONObject sysObject = (JSONObject) currWeatherData.get("sys");
            long sunsetUnixTimestamp =(long) sysObject.get("sunset");
            
            Instant instant = Instant.ofEpochSecond(sunsetUnixTimestamp);
            return LocalDateTime.ofInstant (instant, ZoneOffset.UTC);

}

public String getWeatherDescription() {

    JSONArray weatherArray = (JSONArray) currWeatherData.get("weather");
    JSONObject weatherObject = (JSONObject)weatherArray.get(0);
    return (String)weatherObject.get("description");
}

public double getFeelsLikeTemperature() {
    
    JSONObject mainObject = (JSONObject) currWeatherData.get("main");
    return (double)mainObject.get("feels_like");
}





public JSONObject getLocationData(String locationName){
    locationName=locationName.replaceAll(" ","+");
String urlString="https://geocoding-api.open-meteo.com/v1/search?name="+ locationName + "&count=10&language=en&format=json";
      try{
          HttpURLConnection conn=fetchAPIResponse(urlString);
          if(conn.getResponseCode() != 200){
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
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();
            return conn;
        }catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Enter Your City Name: " );
        String locationName = scanner.nextLine();
        API myApp=new API(locationName);
        System.out.println(myApp.getSunriseTime());

        scanner.close();
    }

}
