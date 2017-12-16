package data;

import city.City;
import city.Coordinates;
import utilitiesTest.Constants;

import java.util.List;

public class WeatherForecastReport {
    static final int DAYS_IN_FORECAST = 3;

    private final City city;
    private final List<ForecastOneDayWeather> oneDayWeathers;
    private final Constants.TemperatureUnits temperatureUnit;

    WeatherForecastReport(City city, List<ForecastOneDayWeather> oneDayWeathers,
                          Constants.TemperatureUnits temperatureUnit) {
        this.city = city;
        this.oneDayWeathers = oneDayWeathers;
        this.temperatureUnit = temperatureUnit;
    }

    public String getCityName() {
        return city.getCityName();
    }

    public String getCountryCode() {
        return city.getCountryCode();
    }

    public Coordinates getCoordinates() {
        return city.getCoordinates();
    }

    public Constants.TemperatureUnits getTemperatureUnit() {
        return temperatureUnit;
    }

    public List<ForecastOneDayWeather> getOneDayWeathers() {
        return oneDayWeathers;
    }
}