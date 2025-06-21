package com.akshay.automationexecrices.utils;

import com.akshay.automationexecrices.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager extends BaseTest {
    public static WebDriver webDriver;
    private static String browserMode;

    public static WebDriver initDriver(String browser){
        ChromeOptions options = new ChromeOptions();


        if ("true".equalsIgnoreCase(util.getProperty("headless"))) {
            options.addArguments("--headless=new"); // or "--headless" for older Chrome
            // Use new headless mode
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            System.out.println("✅ Headless mode enabled via config.properties");
            browserMode = "Headless";
        } else {
            System.out.println("🖥️ Headless mode disabled. Running with UI.");
            browserMode = "UI";
        }

       if(browser.equalsIgnoreCase("chrome")){
            webDriver = new ChromeDriver(options);
        }

        webDriver.manage().window().maximize();
       webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
       return webDriver;
    }
    public static String getBrowserMode() {
        return browserMode;
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }
}
