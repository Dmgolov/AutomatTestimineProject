package storageTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OpenWeatherAppStorageTest {
    private final WeatherRequestFactory weatherRequestFactory = new WeatherRequestFactory();
    private WeatherRepository repository;

    @Before
    public void setUp() {
        repository = new OpenWeatherRepository();
    }

    @Test
    public void testRepositoryGivesCurrentWeatherReportForTheSameCityAsInRequest() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EE");

        CurrentWeatherReport currentWeatherReport = repository.getCurrentWeatherReport(request);

        assertEquals(request.getCityName(), currentWeatherReport.getCityName());
        assertEquals(request.getCountryCode().get(), currentWeatherReport.getCountryCode());
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetCurrentWeatherReportThrowsExceptionIfIncorrectCity() throws APIDataNotFoundException {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("kjjgyiv", "EE");

        repository.getCurrentWeatherReport(request);
    }

    @Test
    public void testTemperatureUnitInCurrentWeatherReportIsTheSameIfItIsNotSpecifiedInRequest() {
        try {
            WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US");

            CurrentWeatherReport report = repository.getCurrentWeatherReport(request);

            assertEquals("New York", report.getCityName());
            assertEquals("US", report.getCountryCode());
            assertEquals(Constants.TemperatureUnits.getUnitByDefault(), report.getTemperatureUnit());
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }

    @Test
    public void testTempUnitInTheCurrentWeatherReportIsTheSameAsInRequest() {
        try {
            WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US",
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
            WeatherRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EE");

            WeatherForecastReport weatherForecastReport = repository.getWeatherForecastReport(request);

            assertEquals(request.getCityName(), weatherForecastReport.getCityName());
            assertEquals(request.getCountryCode().get(), weatherForecastReport.getCountryCode());
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetForecastReportThrowsExceptionIfCityIsNotCorrect() throws APIDataNotFoundException {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("NoCity", "EE");

        repository.getWeatherForecastReport(request);
    }

    @Test
    public void testTemperatureUnitInForecastIsTheSameIfItIsNotSpecifiedInRequest() {
        try {
            WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US");

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
            WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US",
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
