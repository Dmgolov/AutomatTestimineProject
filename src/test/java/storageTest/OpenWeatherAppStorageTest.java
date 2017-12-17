package storageTest;

import org.junit.Before;
import org.junit.Test;
import requests.WeatherAppRequest;
import requests.WeatherAppRequestFactory;
import storage.OpenWeatherAppStorage;
import storage.WeatherRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OpenWeatherAppStorageTest {
    private final WeatherAppRequestFactory weatherRequestFactory = new WeatherAppRequestFactory();
    private WeatherRepository repository;

    @Before
    public void setUp() {
        repository = new OpenWeatherAppStorage();
    }

    @Test
    public void testRepositoryGivesCurrentWeatherReportForTheSameCityAsInRequest() {
        WeatherAppRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EE");

        CurrentWeatherReport currentWeatherReport = repository.getCurrentWeatherReport(request);

        assertEquals(request.getCityName(), currentWeatherReport.getCityName());
        assertEquals(request.getCountryCode().get(), currentWeatherReport.getCountryCode());
    }

    @Test
    public void testTemperatureUnitInCurrentWeatherReportIsTheSameIfItIsNotSpecifiedInRequest() {
        try {
            WeatherAppRequest request = weatherRequestFactory.makeWeatherRequest("Texas", "US");

            CurrentWeatherReport report = repository.getCurrentWeatherReport(request);

            assertEquals("Texas", report.getCityName());
            assertEquals("US", report.getCountryCode());
            assertEquals(Constants.TemperatureUnits.getUnitByDefault(), report.getTemperatureUnit());
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }

    @Test
    public void testTempUnitInTheCurrentWeatherReportIsTheSameAsInRequest() {
        try {
            WeatherAppRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US",
                    Constants.TemperatureUnits.METRIC);

            CurrentWeatherReport report = repository.getCurrentWeatherReport(request);

            assertEquals("New York", report.getCityName());
            assertEquals("US", report.getCountryCode());
            assertEquals(Constants.TemperatureUnits.METRIC, report.getTemperatureUnit());
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }

    @Test
    public void testRepositoryGivesForecastForTheSameCityAsInRequest() {
        try {
            WeatherAppRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EE");

            WeatherForecastReport weatherForecastReport = repository.getWeatherForecastReport(request);

            assertEquals(request.getCityName(), weatherForecastReport.getCityName());
            assertEquals(request.getCountryCode().get(), weatherForecastReport.getCountryCode());
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetForecastReportThrowsExceptionIfCityIsNotCorrect() throws APIDataNotFoundException {
        WeatherAppRequest request = weatherRequestFactory.makeWeatherRequest("NoCity", "EE");

        repository.getWeatherForecastReport(request);
    }

    @Test
    public void testTemperatureUnitInForecastIsTheSameIfItIsNotSpecifiedInRequest() {
        try {
            WeatherAppRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US");

            WeatherForecastReport report = repository.getWeatherForecastReport(request);

            assertEquals("New York", report.getCityName());
            assertEquals("US", report.getCountryCode());
            assertEquals(Constants.TemperatureUnits.getUnitByDefault(), report.getTemperatureUnit());
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }

    @Test
    public void testTempUnitInForecastReportIsTheSameAsInRequest() {
        try {
            WeatherAppRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US",
                    Constants.TemperatureUnits.IMPERIAL);

            WeatherForecastReport report = repository.getWeatherForecastReport(request);

            assertEquals("New York", report.getCityName());
            assertEquals("US", report.getCountryCode());
            assertEquals(Constants.TemperatureUnits.IMPERIAL, report.getTemperatureUnit());
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }
}
