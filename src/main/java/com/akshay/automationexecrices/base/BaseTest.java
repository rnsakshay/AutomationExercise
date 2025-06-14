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
    protected Properties properties;

    @BeforeMethod
    public void setUp(){
        properties = ConfigReader.initProp();
        generateAndSetRuntimeData();

        driver = DriverManager.initDriver(properties.getProperty("browser"));
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
           // driver.quit();
        }
    }

    private void generateAndSetRuntimeData() {
        String name = CommonMethods.getName();
        PropertyUtil.set("runtimeName", name);

        String[] nameParts = name.split(" ");
        PropertyUtil.set("runtimeFirstName", nameParts[0]);
        PropertyUtil.set("runtimeLastName", nameParts.length > 1 ? nameParts[1] : "");

        PropertyUtil.set("runtimeEmail", CommonMethods.getEmail());
        PropertyUtil.set("runtimePassword", CommonMethods.getPassword());
        PropertyUtil.set("runtimeState", CommonMethods.getState());
        PropertyUtil.set("runtimeCity", CommonMethods.getCity());
        PropertyUtil.set("runtimeStreet", CommonMethods.getStreet());
        PropertyUtil.set("runtimeMobileNumber", CommonMethods.getPhone());
    }

}
