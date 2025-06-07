package com.akshay.automationexecrices.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    static Properties properties;

    public static Properties initProp(){
        properties =new Properties();

        try{
            FileInputStream fileInputStream = new FileInputStream("./config/config.properties");
            properties.load(fileInputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return properties;

    }
}
