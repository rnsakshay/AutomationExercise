import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Properties;

public class LoginFlowTest extends BaseTest {

    private static final String EXPECTED_LOGGED_USER_TITLE = "Logged in as akshay";
    private static final Logger logger = LogManager.getLogger(LoginFlowTest.class);

    @Test
    public void validLoginTest() throws Exception {
        logger.info("Test started: validLoginTest");

        // Step 1: Open the application URL
        logger.info("Step 1: Open the application URL: " + util.getProperty("url"));
        LoginPage loginSignupPage = new LoginPage(driver);

        // Step 2: Click on the signup/login button
        logger.info("Step 2: Click on the Sign Up / Login button");
        loginSignupPage.clickLoginSignupButton();

        // Step 3: Enter username
        logger.info("Step 3: Enter valid username: " + util.getProperty("username"));
        loginSignupPage.enterLoginEmail(util.getProperty("username"));

        // Step 4: Enter password (hidden in logs)
        logger.info("Step 4: Enter valid password: ****** (hidden for security)");
        loginSignupPage.enterLoginPassword(util.getProperty("password"));

        // Step 5: Click on the login button
        logger.info("Step 5: Click on the Login button");
        loginSignupPage.clickLoginButton();

        // Step 6: Validate logged-in user
        String actualLoggedInUserText = loginSignupPage.getLoggedInUsername();
        logger.info("Step 6: Validated logged-in user");
        logger.info("Actual logged-in user text: " + actualLoggedInUserText);

        Assert.assertEquals(actualLoggedInUserText, EXPECTED_LOGGED_USER_TITLE, "Logged-in username mismatch!");

        logger.info("Test completed successfully: validLoginTest");
    }

    @Test
    public void inValidLoginTest() throws Exception {
        String expectedErrorMessage = "Your email or password is incorrect!";
        // Step 1: Open the application URL
        logger.info("Step 1: Open the application URL: " + util.getProperty("url"));
        LoginPage loginSignupPage = new LoginPage(driver);

        // Step 2: Click on the signup/login button
        logger.info("Step 2: Click on the Sign Up / Login button");
        loginSignupPage.clickLoginSignupButton();

        // Step 3: Enter invalid username
        logger.info("Step 3: Enter invalid username: " + "testuser");
        loginSignupPage.enterLoginEmail("testuser@t.com");

        // Step 4: Enter invalid password (avoid logging sensitive info, but if necessary, log carefully)
        logger.info("Step 4: Enter invalid password: ****** (hidden for security)");
        loginSignupPage.enterLoginPassword("2122");

        // Step 5: Click on the login button
        logger.info("Step 5: Click on the Login button");
        loginSignupPage.clickLoginButton();

        // Step 6: Validate error message displayed for invalid login
        String actualErrorMessage = loginSignupPage.getLoginErrorMsg();
        logger.info("Step 6: Validated error message for invalid login");
        logger.info("Error message displayed: " + actualErrorMessage);

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch!");

        logger.info("Test completed successfully: inValidLoginTest");
    }
}