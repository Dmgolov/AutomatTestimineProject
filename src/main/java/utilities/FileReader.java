package utilities;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    public String readFile(String pathToInputFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(pathToInputFile));
        StringBuilder content = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
        }

        bufferedReader.close();
        return content.toString();
    }
}
