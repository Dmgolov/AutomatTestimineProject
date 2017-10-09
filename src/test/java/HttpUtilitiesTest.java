import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class HttpUtilitiesTest {

    WeatherNow weatherInTallinn;
    WeatherNow weatherInNarva;
    WeatherNow currentWeatherTest;

    WeatherForecast forecastForTallinn;
    WeatherForecast forecastForNarva;
    WeatherForecast forecastTest;

    @Before
    public void setup() {
        weatherInTallinn = new WeatherNow("Tallinn");
        weatherInNarva = new WeatherNow("Narva");
    }

    @Test
    public void testHttpConnection() throws IOException {
        String strUrl = "api.openweathermap.org/data/2.5/&units=metric&appid=c5879c907ea67ceab79a594b2b3d9d19";

        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            assertEquals(HttpURLConnection.HTTP_OK, 200);
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void testContentJSONisNoteEmpty() {

    }

    @Test
    public void testIsResponseJSON() {

    }

    @Test
    public void testApiKey() {
        fail();
    }
}
