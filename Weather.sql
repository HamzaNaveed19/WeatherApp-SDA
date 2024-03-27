create database FiveDayForecast;
use FiveDayForecast;
create table city(
City varchar(50) Primary key
);
create table StoredDate(
savedDate date
);
ALTER TABLE StoredDate
ADD PRIMARY KEY (savedDate);

CREATE TABLE WeatherForecasts (
    City varchar(50),
    ForecastDate DATE,
    MAX_TEMP DECIMAL(5, 2),
    MIN_TEMP DECIMAL(5, 2),
    Weather_Description varchar (50)
    
);
ALTER TABLE WeatherForecasts
ADD CONSTRAINT FK_City_Five FOREIGN KEY (City)
REFERENCES City(City)
ON DELETE CASCADE 
ON UPDATE CASCADE;

CREATE TABLE HourlyForecasts (
    City varchar(50),
    Date_Time DATETIME,
    Temperature DECIMAL(5, 2)
);
ALTER TABLE  HourlyForecasts
ADD CONSTRAINT FK_City_Three FOREIGN KEY (City)
REFERENCES City(City)
ON DELETE CASCADE 
ON UPDATE CASCADE;

create Table AirPollutantsData(
City Varchar(50),
NO double,
NO2 double,
O3 double,
SO2 double,
PM2_5 double,
PM_10 Double,
NH3 double,
CO double
);
ALTER TABLE  AirPollutantsData
ADD CONSTRAINT FK_City_Gases FOREIGN KEY (City)
REFERENCES City(City)
ON DELETE CASCADE 
ON UPDATE CASCADE;

create Table AirQualityIndex(
City Varchar(50),
AQI double
);
ALTER TABLE  AirQualityIndex
ADD CONSTRAINT FK_City_AQI FOREIGN KEY (City)
REFERENCES City(City)
ON DELETE CASCADE 
ON UPDATE CASCADE;




