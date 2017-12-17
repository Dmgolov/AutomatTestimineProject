package io;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import exceptions.FailedToReadInputFileException;
import utilities.FileReader;
import utility.FileReader;

import java.io.IOException;

public class InputFileReader {
    private final FileReader reader;

    public InputFileReader(FileReader reader) {
        this.reader = reader;
    }

    public RequestFile readFromFile(String pathToInputFile) throws FailedToReadInputFileException {
        Gson gson = new Gson();
        try {
            return gson.fromJson(reader.readFile(pathToInputFile), RequestFile.class);
        } catch (IOException | JsonSyntaxException ex) {
            throw new FailedToReadInputFileException();
        }
    }
}
