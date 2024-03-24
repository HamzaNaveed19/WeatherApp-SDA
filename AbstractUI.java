// AbstractUI.java
public interface AbstractUI {
    // Current weather temperatures
    void displayMaxTemp(int temp);
    void displayMinTemp(int temp);
    void displayHumidity(int temp);
    void displayFeelsLike(int temp);
    void displayCurrentDescription(String temp);
    void displaySunRiseTime(String temp);
    void displaySunSetTime(String temp);
    void displayCurrentLocationDate(String temp);

    // 3 hourly temp stamp 
    void displayH3Temp1(int temp);
    void displayH3Temp2(int temp);
    void displayH3Temp3(int temp);
    void displayH3Temp4(int temp);
    void displayH3Temp5(int temp);
    void displayH3Temp6(int temp);
    void displayH3Temp7(int temp);
    void displayH3Temp8(int temp);

    void displayH3Time1(String temp);
    void displayH3Time2(String temp);
    void displayH3Time3(String temp);
    void displayH3Time4(String temp);
    void displayH3Time5(String temp);
    void displayH3Time6(String temp);
    void displayH3Time7(String temp);
    void displayH3Time8(String temp);

    // 5 days forecast
    void displayD1MaxTemp(int temp);
    void displayD2MaxTemp(int temp);
    void displayD3MaxTemp(int temp);
    void displayD4MaxTemp(int temp);
    void displayD5MaxTemp(int temp);

    void displayD1MinTemp(int temp);
    void displayD2MinTemp(int temp);
    void displayD3MinTemp(int temp);
    void displayD4MinTemp(int temp);
    void displayD5MinTemp(int temp);

    void displayD1Description(String temp);
    void displayD2Description(String temp);
    void displayD3Description(String temp);
    void displayD4Description(String temp);
    void displayD5Description(String temp);

    void displayD1Date(String temp);
    void displayD2Date(String temp);
    void displayD3Date(String temp);
    void displayD4Date(String temp);
    void displayD5Date(String temp);

    // Polluting Gases
    void displaySO2(Double temp);
    void displayNO2(Double temp);
    void displayO3(Double temp);
    void displayCO(Double temp);
    void displayNO(Double temp);
    void displayNH3(Double temp);
    void displayPM10(Double temp);
    void displayPM2_5(Double temp);

    // Air Quality Index
    void displayAirQualityIndex(int temp);
}