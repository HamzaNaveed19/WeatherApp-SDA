import org.json.simple.JSONArray;

import javafx.application.Platform;
public class WeatherApp {

   private AbstractUI ui;
    private weatherData data;
    private API api;
    private AbstractDB db;

    public WeatherApp(){
        ui=new javaUI();
        data = new weatherData();
    }

    public void callAPIFirst(String location){
        db.storeFiveDayData(location, api.getFiveDayForecast());
        db.storeThreeHourly(location, api.getThreeHourlyForecast(location));
        db.storeGasesComponent(location, api.getComponentGasses());
        db.storeAQIComponent(location, api.getAQI());
    
    }
    public void setAttributesthroughDataBase(String location){
        //data.setFiveDays(location, db.getFiveDaysData(location));
        data.setThreeHourly(location, db.getThreeHourlyData(location));
        data.setGasesConcentration(location, db.getGasesComponent(location));
        data.setAQI(location, (int)db.getAQIForCity(location));
       // data.setFiveDays(location, db.getThreeHourlyData(location));
    }
    public void setAPICallingAttributes(String location){
        data.setCurrentHumidity(location,api.getCurrentHumidity());
        data.setCurrentMaxTemp(location,(int)api.getMaximumTemperature());
        data.setCurrentMinTemp(location,(int)api.getMinimumTemperature());
        data.setCurrentTemp(location,(int)api.getCurrentTemperature());
        data.setSunSetTime(location,api.getSunsetTime());
        data.setSunriseTime(location,api.getSunriseTime());
        data.setFeelsLikeTemp(location,(int)api.getFeelsLikeTemperature());
    }
    public void retrieve(String location){
       JSONArray d=db.getFiveDaysData(location);
        this.setAPICallingAttributes(location);
        if(d.isEmpty()){
            this.callAPIFirst(location);
        }
        this.setAttributesthroughDataBase(location);

       
    }

    public void display(){

        System.out.println(data.getCity());
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {
                try {
                    ui.start();   
                    ui.displayH3Temp(data.threeHourlyTemp);   
                    ui.displayH3Time(data.threeHourlyTime);                
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            try {
                ui.start();
                ui.displaySunRiseTime("06:45 am");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        weatherApp a=new weatherApp();
        a.retrieve();
        a.display();
    }
}
//  import org.json.simple.JSONObject;
// import org.json.simple.JSONArray;

// public class WeatherApp {
//     public weatherData d=new weatherData();
//     String locationName = "Lahore";
//     Database d1=new Database();
//     JSONArray a= d1.getThreeHourlyData(locationName);
//    // API myApp=new API(locationName);
//    public  void nothing(){
//     d.setFiveDays(locationName,d1.getFiveDaysData(locationName) );
//     String[] times=d.getFiveDayDesc();
//     for (int i = 0; i < times.length; i++) {
//         System.out.println(times[i]);
//     }
 
//     // d.setFiveDays(locationName,Database.getFiveDaysData(locationName) );
//     // int [] maxt=d.getFiveDayMaxTemp();
//     // int[] mint=d.getFiveDayMinTemp();
//     // String[] desc=d.getFiveDayDesc();
//     // String[] date=d.getFiveDayDates();
//     // for (int i = 0; i < maxt.length; i++) {
//     //     System.out.println(maxt[i]+ (":") + ": " + desc[i]);
//     // }

//     // d.setGasesConcentration(locationName, Database.getGasesComponent(locationName));
//     // Double[] gases=d.getGasesData();
//     // for (int i = 0; i < gases.length; i++) {
//     //     System.out.println(gases[i]+ (":") + ": " );
//     // }

//     // d.setAQI(locationName,(int) Database.getAQIForCity(locationName));
//     // int a=d.getAQI();
//     // System.out.println(a);

//    }
//    WeatherApp(){
//     System.out.println("WeatherApp created");
//    }
//    public static void main(String[] args) {
//     WeatherApp w=new WeatherApp();
//     w.nothing();
   
// }
    
// }
