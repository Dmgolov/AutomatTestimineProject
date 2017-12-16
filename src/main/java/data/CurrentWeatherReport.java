package data;

import city.City;
import city.Coordinates;
import utilitiesTest.Constants;

public class CurrentWeatherReport {
    private final City city;
    private final double currentTemperature;
    private final Constants.TemperatureUnits temperatureUnit;

    CurrentWeatherReport(City city, double currentTemperature, Constants.TemperatureUnits tempUnit) {
        this.city = city;
        this.currentTemperature = currentTemperature;
        this.temperatureUnit = tempUnit;
    }

    public String getCityName() {
        return city.getCityName();
    }

    public String getCountryCode() {
        return city.getCountryCode();
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public Coordinates getCoordinates() {
        return city.getCoordinates();
    }

    public Constants.TemperatureUnits getTemperatureUnit() {
        return temperatureUnit;
    }

    @Override
    public String toString() {
        return String.format("City: %s [%s]\nCoords: %s\nTemp: %.2f", getCityName(), getCountryCode(),
                city.getCoordinates(), getCurrentTemperature());
    }
}
