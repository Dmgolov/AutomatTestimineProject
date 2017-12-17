package requests;

import io.RequestFile;
import utilities.Constants;
import utilities.CountryCode;
import utilitiesTest.Constants;
import utilitiesTest.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherAppRequestFactory {
    public WeatherAppRequest makeWeatherRequest(String cityName) {
        WeatherAppRequest request = new WeatherAppRequest();
        request.cityName = cityName;

        return request;
    }

    public WeatherAppRequest makeWeatherRequest(String cityName, Constants.TemperatureUnits tempUnit) {
        WeatherAppRequest request = makeWeatherRequest(cityName);
        request.tempUnit = tempUnit;
        return request;
    }

    public WeatherAppRequest makeWeatherRequest(String cityName, String countryCode) {
        WeatherAppRequest request = makeWeatherRequest(cityName);

        if (!CountryCode.isCountryCodeCorrect(countryCode)) {
            throw new IllegalArgumentException("Incorrect country code!");
        }

        request.cityCode = countryCode;

        return request;
    }

    public WeatherAppRequest makeWeatherRequest(String cityName, String countryCode,
                                                Constants.TemperatureUnits tempUnit) {
        WeatherAppRequest request = makeWeatherRequest(cityName, countryCode);
        request.tempUnit = tempUnit;
        return request;
    }

    public List<WeatherAppRequest> makeWeatherRequests(RequestFile inputFile) {
        Constants.TemperatureUnits temperatureUnit = inputFile.getTemperatureUnit() == null
                ? Constants.TemperatureUnits.getUnitByDefault()
                : Constants.TemperatureUnits.of(inputFile.getTemperatureUnit());

        if (inputFile.getCitiesNames() == null) {
            throw new IllegalArgumentException("Cities missed!");
        }

        return Arrays.stream(inputFile.getCitiesNames())
                .map(n -> {
                    WeatherAppRequest request = makeWeatherRequest(n);
                    request.tempUnit = temperatureUnit;
                    return request;
                })
                .collect(Collectors.toList());
    }
}
