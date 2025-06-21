package com.akshay.automationexecrices.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//ul[@class='nav navbar-nav']/li/a[contains(text(),'Signup / Login')]")
    private WebElement loginSignupBtn;

    @FindBy(xpath = "//ul[@class='nav navbar-nav']/li[10]/a")
    private WebElement loggedInUsername;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void clickLoginSignupButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginSignupBtn)).click();
    }

    public String getLoggedInUsername(){
        return loggedInUsername.getText();
    }

}
