package com.example.SDETExercise.config;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private Properties properties;

    private Configuration(String confFileName) {
        try (InputStream input = getClass().getResourceAsStream("/" + confFileName + ".properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static synchronized Configuration getInstance(String confFileName) {
        return new Configuration(confFileName);
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
