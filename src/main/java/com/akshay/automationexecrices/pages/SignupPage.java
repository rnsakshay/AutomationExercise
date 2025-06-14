package com.akshay.automationexecrices.pages;

import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.utils.CommonMethods;
import com.akshay.automationexecrices.utils.GoogleSheetManager;
import com.akshay.automationexecrices.utils.PropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SignupPage {
    WebDriver driver;
    WebDriverWait wait;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Step 1 Elements
    @FindBy(xpath = "//input[@name='name']")
    private WebElement inputName;

    @FindBy(xpath = "//form[@action='/signup']/input[@name='email']")
    private WebElement inputEmail;

    By signupBtn = By.xpath("//button[contains(text(),'Signup')]");

    // Step 2 Elements
    @FindBy(xpath = "//input[@name='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement inputFirstName;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement inputLastName;

    @FindBy(xpath = "//input[@id='address1']")
    private WebElement inputStreet;

    @FindBy(xpath = "//input[@name='state']")
    private WebElement inputState;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement inputCity;

    @FindBy(xpath = "//input[@name='zipcode']")
    private WebElement inputZipcode;

    @FindBy(xpath = "//input[@name='mobile_number']")
    private WebElement inputMobile;

    @FindBy(xpath = "//button[contains(text(),'Create Account')]")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//h2/b[contains(text(),'Account Created!')]")
    private WebElement accountConfirmationMessage;

    @FindBy(xpath = "//section[@id='form']/div/div/div/div/a")
    WebElement continueBtnOnRegistration;

    @FindBy(xpath = "//ul/li/a[contains(text(),' Delete Account')]")
    WebElement deleteAccountBtn;

    @FindBy(xpath = "//h2/b[contains(text(),'Account Deleted!')]")
    private WebElement deleteAccountConfirmationMessage;

    // === PUBLIC METHODS ===

    public void fillInitialSignup(String name, String email) {
        wait.until(ExpectedConditions.visibilityOf(inputName)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOf(inputEmail)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(signupBtn)).click();
        PageFactory.initElements(driver, this); // to avoid stale elements
    }

    public void fillDetailsFormAndLogToSheet() {
        Map<String, String> data = getRuntimeUserData();

        wait.until(ExpectedConditions.visibilityOf(inputPassword)).sendKeys(data.get("password"));
        wait.until(ExpectedConditions.visibilityOf(inputFirstName)).sendKeys(data.get("firstName"));
        wait.until(ExpectedConditions.visibilityOf(inputLastName)).sendKeys(data.get("lastName"));
        wait.until(ExpectedConditions.visibilityOf(inputStreet)).sendKeys(data.get("street"));
        wait.until(ExpectedConditions.visibilityOf(inputState)).sendKeys(data.get("state"));
        wait.until(ExpectedConditions.visibilityOf(inputCity)).sendKeys(data.get("city"));
        wait.until(ExpectedConditions.visibilityOf(inputZipcode)).sendKeys(data.get("zipcode"));
        wait.until(ExpectedConditions.visibilityOf(inputMobile)).sendKeys(data.get("mobile"));

        wait.until(ExpectedConditions.elementToBeClickable(createAccountBtn)).click();

        // Log to Google Sheet
        List<String> rowData = Arrays.asList(
                data.get("name"), data.get("email"), data.get("password"),
                data.get("firstName"), data.get("lastName"),
                data.get("street"), data.get("state"), data.get("city"),
                data.get("zipcode"), data.get("mobile")
        );

        try {
            GoogleSheetManager.appendRowData(rowData);
        } catch (Exception e) {
            System.out.println("Failed to write data to Google Sheet: " + e.getMessage());
        }


    }

    // === PRIVATE HELPERS ===

    private Map<String, String> getRuntimeUserData() {
        Map<String, String> data = new HashMap<>();
        data.put("name", PropertyUtil.get("runtimeName"));
        data.put("email", PropertyUtil.get("runtimeEmail"));
        data.put("password", PropertyUtil.get("runtimePassword"));
        data.put("firstName", PropertyUtil.get("runtimeFirstName"));
        data.put("lastName", PropertyUtil.get("runtimeLastName"));
        data.put("street", PropertyUtil.get("runtimeStreet"));
        data.put("state", PropertyUtil.get("runtimeState"));
        data.put("city", PropertyUtil.get("runtimeCity"));
        data.put("zipcode", PropertyUtil.get("runtimeZipcode"));
        data.put("mobile", PropertyUtil.get("runtimeMobileNumber"));
        return data;
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOf(accountConfirmationMessage)).getText();
    }

    public void continueToHome(){
        wait.until(ExpectedConditions.elementToBeClickable(continueBtnOnRegistration)).click();
    }

    public void deletionAccount(){
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountBtn)).click();
    }

    public String getConfirmationDeleteAccountMessage() {
        return wait.until(ExpectedConditions.visibilityOf(deleteAccountConfirmationMessage)).getText();
    }

}