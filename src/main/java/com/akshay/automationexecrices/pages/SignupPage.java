package com.akshay.automationexecrices.pages;

import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.utils.PropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SignupPage extends BaseTest {
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
        wait.until(ExpectedConditions.visibilityOf(inputName)).sendKeys(util.getProperty("userFirstName") + " " + util.getProperty("userLastName"));
        wait.until(ExpectedConditions.visibilityOf(inputEmail)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(signupBtn)).click();
        PageFactory.initElements(driver, this); // to avoid stale elements
    }

    public void fillDetailsFormAndLog() {
        wait.until(ExpectedConditions.visibilityOf(inputPassword)).sendKeys(util.getProperty("userPassword"));
        wait.until(ExpectedConditions.visibilityOf(inputFirstName)).sendKeys(util.getProperty("userFirstName"));
        wait.until(ExpectedConditions.visibilityOf(inputLastName)).sendKeys(util.getProperty("userLastName"));
        wait.until(ExpectedConditions.visibilityOf(inputStreet)).sendKeys(util.getProperty("userStreet"));
        wait.until(ExpectedConditions.visibilityOf(inputState)).sendKeys(util.getProperty("userState"));
        wait.until(ExpectedConditions.visibilityOf(inputCity)).sendKeys(util.getProperty("userCity"));
        wait.until(ExpectedConditions.visibilityOf(inputZipcode)).sendKeys(util.getProperty("userZipcode"));
        wait.until(ExpectedConditions.visibilityOf(inputMobile)).sendKeys(util.getProperty("userMobileNumber"));

        wait.until(ExpectedConditions.elementToBeClickable(createAccountBtn)).click();
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