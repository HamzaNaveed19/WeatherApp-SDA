interface abstractUI {

    // current weather tempratures
    public void displayMaxTemp(int temp);
    public void displayMinTemp(int temp);
    public void displayHumidity(int temp);
    public void displayFeelsLike(int temp);
    public void displayCurrentDisciption(String temp);

    public void displaySunRiseTime(String temp);
    public void displaySunSetTime(String temp);
    public void displayCurrentLocationDate(String temp);
    
    // 3 hourly temp stamp 
    public void displayh3temp1(int temp);
    public void displayh3temp2(int temp);
    public void displayh3temp3(int temp);
    public void hdisplay3temp4(int temp);
    public void displayh3temp5(int temp);
    public void displayh3temp6(int temp);
    public void displayh3temp7(int temp);
    public void displayh3temp8(int temp);

    public void displayh3time1(String temp);
    public void displayh3time2(String temp);
    public void displayh3time3(String temp);
    public void displayh3time4(String temp);
    public void displayh3time5(String temp);
    public void displayh3time6(String temp);
    public void displayh3time7(String temp);
    public void displayh3time8(String temp);

    // 5 days forcast
    public void displayd1MaxTemp(int temp);
    public void displayd2MaxTemp(int temp);
    public void displayd3MaxTemp(int temp);
    public void displayd4MaxTemp(int temp);
    public void displayd5MaxTemp(int temp);

    public void displayd1MinTemp(int temp);
    public void displayd2MinTemp(int temp);
    public void displayd3MinTemp(int temp);
    public void displayd4MinTemp(int temp);
    public void displayd5MinTemp(int temp);

    public void displayd1Discription(String temp);
    public void displayd2Discription(String temp);
    public void displayd3Discription(String temp);
    public void displayd4Discription(String temp);
    public void displayd5Discription(String temp);

    public void displayd1Date(String temp);
    public void displayd2Date(String temp);
    public void displayd3Date(String temp);
    public void displayd4Date(String temp);
    public void displayd5Date(String temp);
    
    // Polluting Gases
    public void displaySO2(Double temp);
    public void displayNO2(Double temp);
    public void displayO3(Double temp);
    public void displayCO(Double temp);
    public void displayNO(Double temp);
    public void displayNH3(Double temp);
    public void displayPM10(Double temp);
    public void displayPM2_5(Double temp);


    // Air Quality Index
    public void displayAirQualityIndex(int temp);


} 