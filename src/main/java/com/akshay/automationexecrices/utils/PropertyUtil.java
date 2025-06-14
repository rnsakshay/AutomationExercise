package com.akshay.automationexecrices.utils;
import java.io.*;
import java.util.Properties;

public class PropertyUtil {
    private static final String FILE_PATH = "config/config.properties";
    private static final Properties props = new Properties();

    // Load once
    static {
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get property
    public static String get(String key) {
        return props.getProperty(key);
    }

    // Set property and persist to file
    public static void set(String key, String value) {
        props.setProperty(key, value);
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            props.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

