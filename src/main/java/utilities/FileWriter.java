package utilities;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {
    public void writeToFile(String content, String pathToOutputFile) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new java.io.FileWriter(pathToOutputFile));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }
}
