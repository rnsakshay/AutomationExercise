package com.akshay.automationexecrices.base;

import com.akshay.automationexecrices.utils.ConfigReader;
import com.akshay.automationexecrices.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties properties;

    @BeforeMethod
    public void setUp(){
        properties = ConfigReader.initProp();
        driver = DriverManager.initDriver(properties.getProperty("browser"));
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
