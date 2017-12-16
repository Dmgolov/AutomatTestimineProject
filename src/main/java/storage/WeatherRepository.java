package storage;

import data.CurrentWeatherReport;
import data.WeatherForecastReport;
import exceptions.APIDataNotFoundException;
import requests.WeatherRequest;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherrequest.WeatherRequest;

public interface WeatherRepository {
    WeatherForecastReport getWeatherForecastReport(WeatherRequest request) throws APIDataNotFoundException;

    CurrentWeatherReport getCurrentWeatherReport(WeatherRequest request) throws APIDataNotFoundException;
}
