package dev.nhi.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteCsvFileExample {

    public static String convertToCSV(String[] data) {
        return Stream.of(data)
            .map(WriteCsvFileExample::escapeSpecialCharacters)
            .collect(Collectors.joining(","));
    }

    public static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(new String[]
                { "John", "Doe", "38", "Comment Data\nAnother line of comment data" });
        dataLines.add(new String[]
                { "Jane", "Doe, Jr.", "19", "She said \"I'm being quoted\"" });

        File csvOutputFile = new File("nhiph.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(WriteCsvFileExample::convertToCSV)
                    .forEach(pw::println);
        }

    }
}