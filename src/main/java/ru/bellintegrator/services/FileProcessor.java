package ru.bellintegrator.services;

import java.io.*;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class FileProcessor {
    /**
     * Write text to File
     *
     * @param textToWrite text to write in the file
     * @param filePath    file path to write text
     */
    public void writeTextToFile(String textToWrite, String filePath, boolean addToExistingFileText) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, addToExistingFileText)) {
            writer.append(textToWrite);
        }
    }

    /**
     * Gets file input stream
     *
     * @param filePath path to file
     * @return input stream of selected file
     */
    public InputStream getFileInputStream(String filePath) throws FileNotFoundException {
        return new FileInputStream(filePath);
    }
}
