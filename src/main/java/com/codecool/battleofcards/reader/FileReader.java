package com.codecool.battleofcards.reader;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
    public String readerFromFile(String filename) {
        StringBuilder fileContent = new StringBuilder();

        ClassLoader classLoader = getClass().getClassLoader();
        Path filePath = Paths.get(classLoader.getResource(filename).getFile());

        try (Scanner reader = new Scanner(filePath)) {
            while (reader.hasNextLine()) {
                fileContent.append(reader.nextLine());
                fileContent.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent.toString();
    }
}