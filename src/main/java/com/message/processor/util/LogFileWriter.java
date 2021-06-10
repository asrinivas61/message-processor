package com.message.processor.util;

import org.apache.commons.lang3.math.IEEE754rUtils;

import java.io.FileWriter;
import java.io.IOException;

public class LogFileWriter {

    private LogFileWriter() {}

    private static FileWriter fileWriter = null;

    public static FileWriter getInstance() throws IOException {
        try {
            if (fileWriter == null) {
                fileWriter = new FileWriter("exceptions.log");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileWriter;
    }

    public void closeWriter() throws IOException {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}