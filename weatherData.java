//import org.json.JSONArray;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class weatherData {
    String[] threeHourlyTime = new String[8];
    int[] threeHourlyTemp = new int[8];
    // for FiveDay
    int[] FiveDayMaxTemp = new int[5];
    int[] FiveDayMinTemp = new int[5];
    String[] FiveDayDate = new String[5];
    String[] FiveDayDescription = new String[5];
    String Location = "";
    // for gases Components
    // No NEED ---- String[] gasesNames=new String[8];
    Double[] gasesConcentration = new Double[8];

    // for AQI
    int AirQualityIndex;

    // Now Variables for API call wali cheezein
    int currentTemperature, feelsLike;
    int currentMin, currentMax, humidity;
    String weatherDiscription;

    String sunSetTime = "6:17", sunRiseTime;

    public weatherData() {
        // this.city = "Karachi";
        // this.getThreeHourly();
    }

    public void setLocation(String loc) {

        this.Location = loc;
    }

    public void setCurrentTemp(String city, int temp) {
        this.currentTemperature = temp;
    }

    public int getCurrentTemp() {
        return this.currentTemperature;
    }

    public void setCurrentHumidity(String city, long temp) {
        this.humidity = (int) temp;
    }

    public int getCurrentHumidity() {
        return this.humidity;
    }

    public void setCurrentMaxTemp(String city, int temp) {
        this.currentMax = temp;
    }

    public int getCurrentMaxTemp() {
        return this.currentMax;
    }

    public void setCurrentMinTemp(String city, int temp) {
        this.currentMin = temp;
    }

    public int getCurrentMinTemp() {
        return this.currentMin;
    }

    public void setFeelsLikeTemp(String city, int temp) {
        this.feelsLike = temp;
    }

    public int getFeelsLikeTemp() {
        return this.feelsLike;
    }

    public void setWeatherDiscription(String city, String temp) {
        weatherDiscription = temp;
    }

    public String getWeatherDiscription() {
        return this.weatherDiscription;
    }

    public void setSunriseTime(String city, LocalDateTime srTime) {
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
       
        this.sunRiseTime = srTime.format(formatter);
    }

    public String getSunriseTime() {
        return this.sunRiseTime;
    }

    public void setSunSetTime(String city, LocalDateTime ssTime) {
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
    
        this.sunSetTime = ssTime.format(formatter);
    }

    public String getSunSetTime() {
        return this.sunSetTime;
    }

    public void setThreeHourly(String city, JSONArray jsonArray) {
        this.Location = city;

        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jsonObject = (JSONObject) jsonArray.get(i);


            String dateTime = (String) jsonObject.get("date_time");
            double temperature = (Double) jsonObject.get("temperature");

            String time = dateTime.split(" ")[1];

            int hour = Integer.parseInt(time.split(":")[0]);

     
            String amPm;
            if (hour == 0) {
                hour = 12; // Midnight case
                amPm = "AM";
            } else if (hour == 12) {
                amPm = "PM"; // Noon case
            } else if (hour > 12) {
                hour -= 12;
                amPm = "PM";
            } else {
                amPm = "AM";
            }

            String newTime = hour + " " + amPm;
            threeHourlyTime[i] = newTime;

            threeHourlyTemp[i] = (int) temperature;
        }
    }

    public int[] getThreeHourlyTemp() {
        return this.threeHourlyTemp;
    }

    public String[] getThreeHourlyTimes() {
        return this.threeHourlyTime;
    }

    public String getLocaion() {
        return this.Location;
    }

    public void setFiveDays(String city, JSONArray jsonArray) {
      
        for (int i = 0; i < jsonArray.size(); i++) {
            
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            double MaxTemp = (Double) jsonObject.get("MaxTemp");
            double MinTemp = (Double) jsonObject.get("MinTemp");
            String desc = (String) jsonObject.get("Description");

            String date = (String) jsonObject.get("ForecastDate");

            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE"); // Adjust pattern if date format
            //                                                                           // differs
            // LocalDateTime dateObj;
            // dateObj = LocalDateTime.parse(date, formatter);

            FiveDayMaxTemp[i] = (int) MaxTemp;
            FiveDayMinTemp[i] = (int) MinTemp;
            FiveDayDescription[i] = (String) desc;
            FiveDayDate[i] = (String) date;

            //FiveDayDate[i] = (dateObj != null) ? dateObj.format(formatter) : null; // Store formatted date or null on error
            // Store the temperature as an integer
            // threeHourlyTemp[i] = (int) temperature;
        }
    }

    public int[] getFiveDayMaxTemp() {
        return this.FiveDayMaxTemp;
    }

    public int[] getFiveDayMinTemp() {
        return this.FiveDayMinTemp;
    }

    public String[] getFiveDayDesc() {
        return this.FiveDayDescription;
    }

    public String[] getFiveDayDates() {
        return this.FiveDayDate;
    }

    public void setGasesConcentration(String city, JSONArray jsonArray) {

        // Iterate through the JSONArray
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        gasesConcentration[0] = (Double) jsonObject.get("no2");
        gasesConcentration[1] = (Double) jsonObject.get("no");
        gasesConcentration[2] = (Double) jsonObject.get("o3");
        gasesConcentration[3] = (Double) jsonObject.get("so2");
        gasesConcentration[4] = (Double) jsonObject.get("pm2_5");
        gasesConcentration[5] = (Double) jsonObject.get("pm10");
        gasesConcentration[6] = (Double) jsonObject.get("nh3");
        gasesConcentration[7] = (Double) jsonObject.get("co");

    }

    public Double[] getGasesData() {
        return this.gasesConcentration;
    }

    public void setAQI(String city, int AQI) {
        this.AirQualityIndex = AQI;
    }

    public int getAQI() {
        return this.AirQualityIndex;
    }

}