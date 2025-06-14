import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.pages.LoginPage;
import com.akshay.automationexecrices.utils.GoogleSheetManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoginFlowTest extends BaseTest {

    private static final String EXPECTED_LOGGED_USER_TITLE = "Logged in as akshay";
    private static final Logger logger = LogManager.getLogger(LoginFlowTest.class);

    @Test
    public void validLoginTest() throws Exception {

        // Get login data from Google Sheet
        List<List<Object>> sheetData = GoogleSheetManager.readSheetData();
        String usernameFromSheet = sheetData.get(1).get(0).toString();
        String passwordFromSheet = sheetData.get(1).get(1).toString();


        logger.info("Test started: validLoginTest");

        // Step 1: Open the application URL
        logger.info("Step 1: Open the application URL: " + properties.getProperty("url"));
        LoginPage loginSignupPage = new LoginPage(driver);

        // Step 2: Click on the signup/login button
        logger.info("Step 2: Click on the Sign Up / Login button");
        loginSignupPage.clickLoginSignupButton();

        // Step 3: Enter username
        logger.info("Step 3: Enter valid username: " + usernameFromSheet);
        loginSignupPage.enterLoginEmail(usernameFromSheet);

        // Step 4: Enter password (hidden in logs)
        logger.info("Step 4: Enter valid password: ****** (hidden for security)");
        loginSignupPage.enterLoginPassword(passwordFromSheet);

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

        logger.info("Test started: inValidLoginTest");

        // Get login data from Google Sheet
        List<List<Object>> sheetData = GoogleSheetManager.readSheetData();
        String usernameFromSheet = sheetData.get(2).get(0).toString();
        String passwordFromSheet = sheetData.get(2).get(1).toString();

        // Step 1: Open the application URL
        logger.info("Step 1: Open the application URL: " + properties.getProperty("url"));
        LoginPage loginSignupPage = new LoginPage(driver);

        // Step 2: Click on the signup/login button
        logger.info("Step 2: Click on the Sign Up / Login button");
        loginSignupPage.clickLoginSignupButton();

        // Step 3: Enter invalid username
        logger.info("Step 3: Enter invalid username: " + usernameFromSheet);
        loginSignupPage.enterLoginEmail(usernameFromSheet);

        // Step 4: Enter invalid password (avoid logging sensitive info, but if necessary, log carefully)
        logger.info("Step 4: Enter invalid password: ****** (hidden for security)");
        loginSignupPage.enterLoginPassword(passwordFromSheet);

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