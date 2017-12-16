package requests;

import utilitiesTest.Constants;

import java.util.Optional;

public class WeatherRequest {
    String cityName;
    String cityCode;
    Constants.TemperatureUnits tempUnit = Constants.TemperatureUnits.getUnitByDefault();

    WeatherRequest() {

    }

    public String getCityName() {
        return cityName;
    }

    public Optional<String> getCountryCode() {
        return cityCode != null ? Optional.of(cityCode) : Optional.empty();
    }

    public Constants.TemperatureUnits getTemperatureUnit() {
        return tempUnit;
    }
}
