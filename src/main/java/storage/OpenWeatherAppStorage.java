package storage;

import data.CurrentWeatherReport;
import data.WeatherForecastReport;
import exceptions.APIDataNotFoundException;
import exceptions.IncorrectAPIOutputException;
import network.HTTPConnection;
import requests.WeatherAppRequest;
import utilitiesTest.Constants;
import weatherdata.*;
import weatherrequest.WeatherRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Optional;

public class OpenWeatherAppStorage implements WeatherRepository {
    private static final String WEATHER_API_LINK = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String FORECAST_API_LINK = "https://api.openweathermap.org/data/2.5/forecast?";
    private static final String API_KEY = "846ff6c4ed10c7be2825f677097f8376";
    private final WeatherForecastReportFabric weatherForecastReportFabric;
    private final CurrentWeatherReportFabric currentWeatherReportFabric;

    public OpenWeatherAppStorage() {
        weatherForecastReportFabric = new WeatherForecastReportFabric();
        currentWeatherReportFabric = new CurrentWeatherReportFabric();
    }

    private static String getDataLineForString(WeatherAppRequest request) {
        String tempUnitString = request.getTemperatureUnit() == Constants.TemperatureUnits.getUnitByDefault() ?
                "" : String.format("&units=%s", request.getTemperatureUnit().toString().toLowerCase());

        Optional<String> countryCode = request.getCountryCode();
        String cityData = countryCode.map(s -> String.format("%s,%s", request.getCityName(), s))
                .orElseGet(request::getCityName);

        return String.format("q=%s%s&APPID=%s", cityData, tempUnitString, API_KEY);
    }

    private static String makeCurrentWeatherRequestLinkFromWeatherRequest(WeatherAppRequest request) {
        return WEATHER_API_LINK + getDataLineForString(request);
    }

    private static String makeWeatherForecastRequestLinkFromWeatherRequest(WeatherAppRequest request) {
        return FORECAST_API_LINK + getDataLineForString(request);
    }

    private static HTTPConnection getHTTPConnectionByLink(String connectionLink) throws APIDataNotFoundException {
        try {
            HTTPConnection connection = HTTPConnection.createConnectionFromURL(connectionLink);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                throw new APIDataNotFoundException("Report not found!");
            }
            return connection;
        } catch (IOException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }

    private static String getJSONFileFromConnection(HTTPConnection connection) throws APIDataNotFoundException {
        try {
            return connection.downloadFile();
        } catch (IOException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }

    private String getJSON(String connectionLink) {
        HTTPConnection connection = getHTTPConnectionByLink(connectionLink);
        String jsonFile = getJSONFileFromConnection(connection);
        connection.close();
        return jsonFile;
    }

    @Override
    public WeatherForecastReport getWeatherForecastReport(WeatherAppRequest request) throws APIDataNotFoundException {
        try {
            String connectionLink = makeWeatherForecastRequestLinkFromWeatherRequest(request);
            String jsonFile = getJSON(connectionLink);

            return weatherForecastReportFabric.createReportFromJSONAndRequest(jsonFile, request);
        } catch (IncorrectAPIOutputException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }

    @Override
    public CurrentWeatherReport getCurrentWeatherReport(WeatherAppRequest request) throws APIDataNotFoundException {
        try {
            String connectionLink = makeCurrentWeatherRequestLinkFromWeatherRequest(request);
            String jsonFile = getJSON(connectionLink);

            return currentWeatherReportFabric.createReportFromJSONAndRequest(jsonFile, request);
        } catch (IncorrectAPIOutputException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }
}
