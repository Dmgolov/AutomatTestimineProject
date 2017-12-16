package network;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection implements Closeable {
    private HttpURLConnection connection;

    public String downloadFile() throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String nextLine;

        while ((nextLine = reader.readLine()) != null) {
            builder.append(nextLine).append("\n");
        }

        reader.close();
        return builder.toString();
    }

    public int getResponseCode() throws IOException {
        return connection.getResponseCode();
    }

    public void close() {
        connection.disconnect();
    }

    public static HTTPConnection createConnectionFromURL(String url) throws IOException {
        HTTPConnection newConnection = new HTTPConnection();
        newConnection.connection = (HttpURLConnection) new URL(url).openConnection();
        newConnection.connection.connect();
        return newConnection;
    }
}
