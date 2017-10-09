import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherNow {
    private static final String openWeatherBase = "api.openweathermap.org/data/2.5/";
    private static final String openWeatherApiID = "&units=metric&appid=c5879c907ea67ceab79a594b2b3d9d19";
    private String cityName;
    private JSONObject jsonObject;

    public WeatherNow(String cityName) {
        this.cityName = cityName;

        String urlRequest = "weather?q=" + cityName;
        String openWeatherUrl = "http://" + openWeatherBase + urlRequest + openWeatherApiID;

        // Try to response url
        try {
            URL url = new URL(openWeatherUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();

            jsonObject = new JSONObject(sb.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    public Double getCurrentTemperature() {
        try {
            return jsonObject.getJSONObject("main").getDouble("temp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCurrentCity() {
        try {
            return jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

}
