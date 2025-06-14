package com.akshay.automationexecrices.utils;
import java.io.*;
import java.util.Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private static PropertyUtil instance;
    private Properties properties;

    // Private constructor to prevent external instantiation
    private PropertyUtil(String filePath) {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + filePath);
        }
    }

    // Get singleton instance (load only once)
    public static PropertyUtil getInstance(String filePath) {
        if (instance == null) {
            instance = new PropertyUtil(filePath);
        }
        return instance;
    }

    // Getter for property value
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}


