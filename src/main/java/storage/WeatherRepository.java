package storage;

import data.CurrentWeatherReport;
import data.WeatherForecastReport;
import exceptions.APIDataNotFoundException;
import requests.WeatherAppRequest;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherrequest.WeatherRequest;

public interface WeatherRepository {
    WeatherForecastReport getWeatherForecastReport(WeatherAppRequest request) throws APIDataNotFoundException;

    CurrentWeatherReport getCurrentWeatherReport(WeatherAppRequest request) throws APIDataNotFoundException;
}
