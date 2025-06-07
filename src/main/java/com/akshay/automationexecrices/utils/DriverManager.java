package com.akshay.automationexecrices.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverManager {
    public static WebDriver webDriver;

    public static WebDriver initDriver(String browser){

       if(browser.equalsIgnoreCase("chrome")){
            webDriver = new ChromeDriver();
        }

        webDriver.manage().window().maximize();
       webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
       return webDriver;
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }
}
