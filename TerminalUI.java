import java.util.Scanner;

public class TerminalUI implements AbstractUI {
    private Scanner scanner;

    public TerminalUI() {
        this.scanner = new Scanner(System.in);
    }
    @Override
    public String getLocation() {
        System.out.println("Please enter your location:");
        return scanner.nextLine();
    }
    @Override
    public void start() throws Exception {
        displayWeatherAppHeader(); // Display the "Weather Application" block
        String location = getLocation();
        System.out.println("You entered location: " + location);
    }

    @Override
    public void displayAirQualityIndex(int temp) {
        System.out.println("Air Quality Index: " + temp);
    }

    @Override
    public void displayMinMaxTemp(int temp1, int temp2) {
        System.out.println("Min Temperature: " + temp1 + "°C, Max Temperature: " + temp2 + "°C");
    }

    @Override
    public void displayHumidity(int temp) {
        System.out.println("Humidity: " + temp + "%");
    }

    @Override
    public void displayFeelsLike(int temp) {
        System.out.println("Feels Like: " + temp + "°C");
    }

    @Override
    public void displayCurrentDescription(String temp) {
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

    @Override
    public void displayH3Temp(int[] temp) {
        System.out.println("H3 Temp 8: " + temp + "°C");
    }

    @Override
    public void displayH3Time(String[] temp) {
        System.out.println("H3 Time 1: " + temp);
    }

    @Override
    public void displayDMaxTemp(int[] temp) {
        System.out.println("Day 1 Max Temperature: " + temp + "°C");
    }

    @Override
    public void displayDMinTemp(int[] temp) {
        System.out.println("Day 1 Min Temperature: " + temp + "°C");
    }

    @Override
    public void displayDDescription(String[] temp) {
        System.out.println("Day 1 Description: " + temp);
    }

    @Override
    public void displayDDates(String[] dates) {
        System.out.println("Day 1 Date: " + dates);
    }

    @Override
    public void displaySO2(Double temp) {
        System.out.println("SO2: " + temp);
    }

    @Override
    public void displayNO2(Double temp) {
        System.out.println("NO2: " + temp);
    }

    @Override
    public void displayO3(Double temp) {
        System.out.println("O3: " + temp);
    }

    @Override
    public void displayCO(Double temp) {
        System.out.println("CO: " + temp);
    }

    @Override
    public void displayNO(Double temp) {
        System.out.println("NO: " + temp);
    }

    @Override
    public void displayNH3(Double temp) {
        System.out.println("NH3: " + temp);
    }

    @Override
    public void displayPM10(Double temp) {
        System.out.println("PM10: " + temp);
    }

    @Override
    public void displayPM2_5(Double temp) {
        System.out.println("PM2.5: " + temp);
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

    // Method to display the "Weather Application" in a block with dashed lines
    public void displayWeatherAppHeader() {
        int length = "Weather Application".length() + 6;
        StringBuilder header = new StringBuilder();
        header.append("-").append("-".repeat(Math.max(0, length))).append("-\n");
        header.append("| ").append("Weather Application").append(" |\n");
        header.append("-").append("-".repeat(Math.max(0, length))).append("-");
        System.out.println(header.toString());
    }

    // Main function to test the functionality
    public static void main(String[] args) {
        TerminalUI terminalUI = new TerminalUI();
        try {
            terminalUI.start();
    
            // Call other functions to demonstrate functionality
            terminalUI.displayAirQualityIndex(75);
            terminalUI.displayMinMaxTemp(10, 20);
            terminalUI.displayHumidity(60);
            terminalUI.displayFeelsLike(18);
            terminalUI.displayCurrentDescription("Sunny");
            terminalUI.displaySunRiseTime("06:30 AM");
            terminalUI.displaySunSetTime("07:00 PM");
            terminalUI.displayCurrentLocationDate("March 26, 2024");
            terminalUI.displayH3Temp(new int[]{15, 16, 14});
            terminalUI.displayH3Time(new String[]{"09:00 AM", "12:00 PM", "03:00 PM"});
            terminalUI.displayDMaxTemp(new int[]{20, 22, 23});
            terminalUI.displayDMinTemp(new int[]{10, 12, 13});
            terminalUI.displayDDescription(new String[]{"Sunny", "Partly Cloudy", "Cloudy"});
            terminalUI.displayDDates(new String[]{"March 27, 2024", "March 28, 2024", "March 29, 2024"});
            terminalUI.displaySO2(5.0);
            terminalUI.displayNO2(10.0);
            terminalUI.displayO3(3.0);
            terminalUI.displayCO(2.0);
            terminalUI.displayNO(1.0);
            terminalUI.displayNH3(0.5);
            terminalUI.displayPM10(25.0);
            terminalUI.displayPM2_5(15.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}    
