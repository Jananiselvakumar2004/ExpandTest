package com.expandtest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException(
                        "[ERROR] config.properties not found in classpath!");
            }
            properties.load(input);
            System.out.println("[INFO] config.properties loaded successfully");
        } catch (IOException e) {
            throw new RuntimeException(
                    "[ERROR] Failed to load config.properties: "
                            + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(
                    "[ERROR] Key not found in config.properties: " + key);
        }
        return value.trim();
    }

    public static int getTimeout() {
        return Integer.parseInt(get("timeout"));
    }
}