package requests;

import utilities.Constants;

import java.util.Optional;

public class WeatherAppRequest {
    String cityName;
    String cityCode;
    Constants.TemperatureUnits tempUnit = Constants.TemperatureUnits.getUnitByDefault();

    WeatherAppRequest() {

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
