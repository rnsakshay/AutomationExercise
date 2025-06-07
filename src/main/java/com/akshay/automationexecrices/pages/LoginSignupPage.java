package com.akshay.automationexecrices.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSignupPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//ul[@class='nav navbar-nav']/li/a[contains(text(),'Signup / Login')]")
    private WebElement loginSignupBtn;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement inputName;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//form[@action='/login']/input[@name='email']")
    private WebElement inputLoginEmail;

    @FindBy(xpath = "//form[@action='/login']/input[@name='password']")
    private WebElement inputLoginPassword;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginBtn;

    @FindBy(xpath = "//button[contains(text(),'Signup')]")
    private WebElement signupBtn;

    @FindBy(xpath = "//ul[@class='nav navbar-nav']/li[10]/a")
    private WebElement loggedInUsername;

    public LoginSignupPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void clickLoginSignupButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginSignupBtn)).click();
    }

    public void enterName(String name){
        wait.until(ExpectedConditions.visibilityOf(inputName));
        inputName.clear();
        inputName.sendKeys(name);
    }

    public void enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public void enterLoginEmail(String name){
        wait.until(ExpectedConditions.visibilityOf(inputName));
        inputLoginEmail.clear();
        inputLoginEmail.sendKeys(name);
    }

    public void enterLoginPassword(String email){
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputLoginPassword.clear();
        inputLoginPassword.sendKeys(email);
    }

    public void clickSignupButton(){
        wait.until(ExpectedConditions.elementToBeClickable(signupBtn)).click();
    }

    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public String getLoggedInUsername(){
        return loggedInUsername.getText();
    }
}
