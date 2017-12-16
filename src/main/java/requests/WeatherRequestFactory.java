package requests;

import io.RequestFile;
import utilitiesTest.Constants;
import utilitiesTest.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherRequestFactory {
    public WeatherRequest makeWeatherRequest(String cityName) {
        WeatherRequest request = new WeatherRequest();
        request.cityName = cityName;

        return request;
    }

    public WeatherRequest makeWeatherRequest(String cityName, Constants.TemperatureUnits tempUnit) {
        WeatherRequest request = makeWeatherRequest(cityName);
        request.tempUnit = tempUnit;
        return request;
    }

    public WeatherRequest makeWeatherRequest(String cityName, String countryCode) {
        WeatherRequest request = makeWeatherRequest(cityName);

        if (!Utils.isCountryCodeCorrect(countryCode)) {
            throw new IllegalArgumentException("Incorrect country code!");
        }

        request.cityCode = countryCode;

        return request;
    }

    public WeatherRequest makeWeatherRequest(String cityName, String countryCode,
                                             Constants.TemperatureUnits tempUnit) {
        WeatherRequest request = makeWeatherRequest(cityName, countryCode);
        request.tempUnit = tempUnit;
        return request;
    }

    public List<WeatherRequest> makeWeatherRequests(RequestFile inputFile) {
        Constants.TemperatureUnits temperatureUnit = inputFile.getTemperatureUnit() == null
                ? Constants.TemperatureUnits.getUnitByDefault()
                : Constants.TemperatureUnits.of(inputFile.getTemperatureUnit());

        if (inputFile.getCitiesNames() == null) {
            throw new IllegalArgumentException("Cities missed!");
        }

        return Arrays.stream(inputFile.getCitiesNames())
                .map(n -> {
                    WeatherRequest request = makeWeatherRequest(n);
                    request.tempUnit = temperatureUnit;
                    return request;
                })
                .collect(Collectors.toList());
    }
}
