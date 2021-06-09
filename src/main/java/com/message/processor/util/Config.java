package com.message.processor.util;

import com.message.processor.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    static InputStream inputStream;

    public static String getConfig(String key) {
        String value = "";
        try {
            inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();

            if (inputStream != null) {
                prop.load(inputStream);
                value = prop.getProperty(key);
            } else {
                throw new FileNotFoundException("property file config.properties not found in the classpath");
            }
        } catch(IOException ioe) {
            System.out.println("Exception while processing config file");
        }
        return value;
    }
}