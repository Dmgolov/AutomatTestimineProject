package io;

import com.google.gson.annotations.SerializedName;
import data.CurrentWeatherReport;
import data.WeatherForecastReport;

public class ReportFile {
    private final transient String cityName;
    @SerializedName("current")
    private final CurrentWeatherReport currentWeatherReport;
    @SerializedName("forecast")
    private final WeatherForecastReport weatherForecastReport;

    public ReportFile(String cityName, CurrentWeatherReport currentWeatherReport,
                      WeatherForecastReport weatherForecastReport) {
        this.cityName = cityName;
        this.currentWeatherReport = currentWeatherReport;
        this.weatherForecastReport = weatherForecastReport;
    }

    public String getCityName() {
        return cityName;
    }
}