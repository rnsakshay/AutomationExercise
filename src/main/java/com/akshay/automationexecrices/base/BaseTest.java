package com.akshay.automationexecrices.base;

import com.akshay.automationexecrices.utils.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    protected static PropertyUtil util;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void reportSetup() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/AutomationExercise.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("QA", "Akshay");


    }

    @BeforeMethod
    public void setUp(Method method){
        util = PropertyUtil.getInstance("config/config.properties");
        driver = DriverManager.initDriver(util.getProperty("browser"));
        driver.get(util.getProperty("url"));
        driver.manage().window().maximize();

        // Create ExtentTest instance for current test
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
        // ✅ Log the browser mode from DriverManager
        test.get().info("Browser Mode: " + DriverManager.getBrowserMode());
       // test.get().info("✅ Headless mode (from config): " + util.getProperty("headless"));
    }

    @AfterMethod
    public void tearDown(ITestResult result){

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().pass("Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.get().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip("Test Skipped");
        }
        if(driver != null){
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() throws InterruptedException, IOException {
            // 1. Flush the Extent Report
            if (BaseTest.extent != null) {
                BaseTest.extent.flush();

            }

    }
}
