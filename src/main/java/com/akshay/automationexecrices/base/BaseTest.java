package com.akshay.automationexecrices.base;

import com.akshay.automationexecrices.utils.CommonMethods;
import com.akshay.automationexecrices.utils.ConfigReader;
import com.akshay.automationexecrices.utils.DriverManager;
import com.akshay.automationexecrices.utils.PropertyUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected static PropertyUtil util;

    @BeforeMethod
    public void setUp(){
        util = PropertyUtil.getInstance("config/config.properties");
        driver = DriverManager.initDriver(util.getProperty("browser"));
        driver.get(util.getProperty("url"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
           // driver.quit();
        }
    }
}
