public class TerminalUI implements AbstractUI {

    // Dummy data
    private static final int TEMP = 25;
    private static final String TEMP1 = "DB WILL GIVE DATA";
    private static final Double TEMP2 = 0.0;

    // Main method
    public static void main(String[] args) {
        TerminalUI UI = new TerminalUI();
        UI.displayInUI("Max Temperature", String.valueOf(TEMP));
        UI.displayInUI("Min Temperature", String.valueOf(TEMP));
        UI.displayInUI("Humidity", String.valueOf(TEMP));
        UI.displayInUI("Feels Like", String.valueOf(TEMP));
        UI.displayInUI("Current Description", TEMP1);
        UI.displayInUI("Sunrise Time", TEMP1);
        UI.displayInUI("Sunset Time", TEMP1);
        UI.displayInUI("Current Location Date", TEMP1);
        UI.displayInUI("3 Hourly Temp 1", String.valueOf(TEMP));
        UI.displayInUI("3 Hourly Temp 2", String.valueOf(TEMP));
        UI.displayInUI("3 Hourly Temp 3", String.valueOf(TEMP));
        UI.displayInUI("3 Hourly Temp 4", String.valueOf(TEMP));
        UI.displayInUI("3 Hourly Temp 5", String.valueOf(TEMP));
        UI.displayInUI("3 Hourly Temp 6", String.valueOf(TEMP));
        UI.displayInUI("3 Hourly Temp 7", String.valueOf(TEMP));
        UI.displayInUI("3 Hourly Temp 8", String.valueOf(TEMP));
        UI.displayInUI("3 Hourly Time 1", TEMP1);
        UI.displayInUI("3 Hourly Time 2", TEMP1);
        UI.displayInUI("3 Hourly Time 3", TEMP1);
        UI.displayInUI("3 Hourly Time 4", TEMP1);
        UI.displayInUI("3 Hourly Time 5", TEMP1);
        UI.displayInUI("3 Hourly Time 6", TEMP1);
        UI.displayInUI("3 Hourly Time 7", TEMP1);
        UI.displayInUI("3 Hourly Time 8", TEMP1);
        UI.displayInUI("Day 1 Max Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 2 Max Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 3 Max Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 4 Max Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 5 Max Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 1 Min Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 2 Min Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 3 Min Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 4 Min Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 5 Min Temp", String.valueOf(TEMP));
        UI.displayInUI("Day 1 Description", TEMP1);
        UI.displayInUI("Day 2 Description", TEMP1);
        UI.displayInUI("Day 3 Description", TEMP1);
        UI.displayInUI("Day 4 Description", TEMP1);
        UI.displayInUI("Day 5 Description", TEMP1);
        UI.displayInUI("Day 1 Date", TEMP1);
        UI.displayInUI("Day 2 Date", TEMP1);
        UI.displayInUI("Day 3 Date", TEMP1);
        UI.displayInUI("Day 4 Date", TEMP1);
        UI.displayInUI("Day 5 Date", TEMP1);
        UI.displayInUI("SO2 Level", String.valueOf(TEMP2));
        UI.displayInUI("NO2 Level", String.valueOf(TEMP2));
        UI.displayInUI("O3 Level", String.valueOf(TEMP2));
        UI.displayInUI("CO Level", String.valueOf(TEMP2));
        UI.displayInUI("NO Level", String.valueOf(TEMP2));
        UI.displayInUI("NH3 Level", String.valueOf(TEMP2));
        UI.displayInUI("PM10 Level", String.valueOf(TEMP2));
        UI.displayInUI("PM2.5 Level", String.valueOf(TEMP2));
        UI.displayInUI("Air Quality Index", String.valueOf(TEMP));
    }

    // Method to display data within a UI
    public void displayInUI(String label, String data) {
        int length = label.length() + data.length() + 6;
        StringBuilder UI = new StringBuilder();
        UI.append("-").append("-".repeat(Math.max(0, length))).append("-\n");
        UI.append("| ").append(label).append(": ").append(data).append(" |\n");
        UI.append("-").append("-".repeat(Math.max(0, length))).append("-");
        System.out.println(UI.toString());
    }
 // Current weather temperatures
 @Override
 public void displayMaxTemp(int temp) {
     System.out.println("Max Temperature: " + temp);
 }

 @Override
 public void displayMinTemp(int temp) {
     System.out.println("Min Temperature: " + temp);
 }

 @Override
 public void displayHumidity(int temp) {
     System.out.println("Humidity: " + temp);
 }

 @Override
 public void displayFeelsLike(int temp) {
     System.out.println("Feels Like: " + temp);
 }

 @Override
 public void displayCurrentDescription(String temp) {
     // Display the current description
     System.out.println("Current Description: " + temp);
 }

 @Override
 public void displaySunRiseTime(String temp) {
     System.out.println("Sunrise Time: " + temp);
 }

 @Override
 public void displaySunSetTime(String temp) {
     System.out.println("Sunset Time: " + temp);
 }

 @Override
 public void displayCurrentLocationDate(String temp) {
     System.out.println("Current Location Date: " + temp);
 }

 // 3 hourly temp stamp 
 @Override
 public void displayH3Temp1(int temp) {
     System.out.println("3 Hourly Temp 1: " + temp);
 }

 @Override
 public void displayH3Temp2(int temp) {
     System.out.println("3 Hourly Temp 2: " + temp);
 }

 @Override
 public void displayH3Temp3(int temp) {
     System.out.println("3 Hourly Temp 3: " + temp);
 }

 @Override
 public void displayH3Temp4(int temp) {
     System.out.println("3 Hourly Temp 4: " + temp);
 }

 @Override
 public void displayH3Temp5(int temp) {
     System.out.println("3 Hourly Temp 5: " + temp);
 }

 @Override
 public void displayH3Temp6(int temp) {
     System.out.println("3 Hourly Temp 6: " + temp);
 }

 @Override
 public void displayH3Temp7(int temp) {
     System.out.println("3 Hourly Temp 7: " + temp);
 }

 @Override
 public void displayH3Temp8(int temp) {
     System.out.println("3 Hourly Temp 8: " + temp);
 }

 @Override
 public void displayH3Time1(String temp) {
     System.out.println("3 Hourly Time 1: " + temp);
 }

 @Override
 public void displayH3Time2(String temp) {
     System.out.println("3 Hourly Time 2: " + temp);
 }

 @Override
 public void displayH3Time3(String temp) {
     System.out.println("3 Hourly Time 3: " + temp);
 }

 @Override
 public void displayH3Time4(String temp) {
     System.out.println("3 Hourly Time 4: " + temp);
 }

 @Override
 public void displayH3Time5(String temp) {
     System.out.println("3 Hourly Time 5: " + temp);
 }

 @Override
 public void displayH3Time6(String temp) {
     System.out.println("3 Hourly Time 6: " + temp);
 }

 @Override
 public void displayH3Time7(String temp) {
     System.out.println("3 Hourly Time 7: " + temp);
 }

 @Override
 public void displayH3Time8(String temp) {
     System.out.println("3 Hourly Time 8: " + temp);
 }

 // 5 days forecast
 @Override
 public void displayD1MaxTemp(int temp) {
     System.out.println("Day 1 Max Temp: " + temp);
 }

 @Override
 public void displayD2MaxTemp(int temp) {
     System.out.println("Day 2 Max Temp: " + temp);
 }

     // 5 days forecast
     @Override
     public void displayD3MaxTemp(int temp) {
         System.out.println("Day 3 Max Temp: " + temp);
     }
 
     @Override
     public void displayD4MaxTemp(int temp) {
         System.out.println("Day 4 Max Temp: " + temp);
     }
 
     @Override
     public void displayD5MaxTemp(int temp) {
         System.out.println("Day 5 Max Temp: " + temp);
     }
 
     @Override
     public void displayD1MinTemp(int temp) {
         System.out.println("Day 1 Min Temp: " + temp);
     }
 
     @Override
     public void displayD2MinTemp(int temp) {
         System.out.println("Day 2 Min Temp: " + temp);
     }
 
     @Override
     public void displayD3MinTemp(int temp) {
         System.out.println("Day 3 Min Temp: " + temp);
     }
 
     @Override
     public void displayD4MinTemp(int temp) {
         System.out.println("Day 4 Min Temp: " + temp);
     }
 
     @Override
     public void displayD5MinTemp(int temp) {
         System.out.println("Day 5 Min Temp: " + temp);
     }
 
     @Override
     public void displayD1Description(String temp) {
         System.out.println("Day 1 Description: " + temp);
     }
 
     @Override
     public void displayD2Description(String temp) {
         System.out.println("Day 2 Description: " + temp);
     }
 
     @Override
     public void displayD3Description(String temp) {
         System.out.println("Day 3 Description: " + temp);
     }
 
     @Override
     public void displayD4Description(String temp) {
         System.out.println("Day 4 Description: " + temp);
     }
 
     @Override
     public void displayD5Description(String temp) {
         System.out.println("Day 5 Description: " + temp);
     }
 
     @Override
     public void displayD1Date(String temp) {
         System.out.println("Day 1 Date: " + temp);
     }
 
     @Override
     public void displayD2Date(String temp) {
         System.out.println("Day 2 Date: " + temp);
     }
 
     @Override
     public void displayD3Date(String temp) {
         System.out.println("Day 3 Date: " + temp);
     }
 
     @Override
     public void displayD4Date(String temp) {
         System.out.println("Day 4 Date: " + temp);
     }
 
     @Override
     public void displayD5Date(String temp) {
         System.out.println("Day 5 Date: " + temp);
     }
 
     // Polluting Gases
     @Override
     public void displaySO2(Double temp) {
         System.out.println("SO2 Level: " + temp);
     }
 
     @Override
     public void displayNO2(Double temp) {
         System.out.println("NO2 Level: " + temp);
     }
 
     @Override
     public void displayO3(Double temp) {
         System.out.println("O3 Level: " + temp);
     }
 
     @Override
     public void displayCO(Double temp) {
         System.out.println("CO Level: " + temp);
     }
 
     @Override
     public void displayNO(Double temp) {
         System.out.println("NO Level: " + temp);
     }
 
     @Override
     public void displayNH3(Double temp) {
         System.out.println("NH3 Level: " + temp);
     }
 
     @Override
     public void displayPM10(Double temp) {
         System.out.println("PM10 Level: " + temp);
     }
 
     @Override
     public void displayPM2_5(Double temp) {
         System.out.println("PM2.5 Level: " + temp);
     }
 
     @Override
     public void displayAirQualityIndex(int temp) {
         System.out.println("Air Quality Index: " + temp);
     }
}
