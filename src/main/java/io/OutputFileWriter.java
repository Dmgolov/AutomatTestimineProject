package io;

import com.google.gson.Gson;
import utilities.FileWriter;
import utility.FileWriter;

import java.io.IOException;

public class OutputFileWriter {
    private static final String OUTPUT_FILE_PATH = "weather_%s.json";

    private final FileWriter writer;

    public OutputFileWriter(FileWriter writer) {
        this.writer = writer;
    }

    private static String getOutputFilePath(String cityName) {
        return String.format(OUTPUT_FILE_PATH, cityName);
    }

    public void writeReportToFile(ReportFile file) throws IOException {
        Gson gson = new Gson();
        writer.writeToFile(gson.toJson(file), getOutputFilePath(file.getCityName()));
    }
}
