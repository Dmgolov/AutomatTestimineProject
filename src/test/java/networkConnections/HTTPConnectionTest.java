package networkConnections;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class HTTPConnectionTest {
    @Test
    public void testConnectionToAPISample() {
        try {
            HTTPConnection connectionFromURL = HTTPConnection.createConnectionFromURL(
                    "http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a15");
            int responseCode = connectionFromURL.getResponseCode();
            assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        } catch (IOException e) {
            fail("Exception raised: " + e.getMessage());
        }
    }

    @Test(expected = IOException.class)
    public void testConnectionToNotExistingLink() throws IOException {
        HTTPConnection con = HTTPConnection.createConnectionFromURL("http://nolink.es");
        con.getResponseCode();
    }
}