import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.pages.HomePage;
import com.akshay.automationexecrices.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginFlowTest extends BaseTest {

    private static final String EXPECTED_LOGGED_USER_TITLE = "Logged in as akshay";
    private static final String EXPECTED_INVALID_LOGIN_ERROR_MESSAGE = "Your email or password is incorrect!";
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void pageSetup() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void verify_LoginSuccessfullyWithValidCredentials() throws Exception {
        test.get().info("✅ Scenario: User logs in with valid credentials and is successfully authenticated.");
        test.get().info("Test started: Verify login functionality with valid credentials");

        // Step 1: Open the application URL
        test.get().info("Step 1: Open the application URL: " + util.getProperty("url"));

        // Step 2: Click on the signup/login button
        test.get().info("Step 2: Click on the Sign Up / Login button");
        homePage.clickLoginSignupButton();

        // Step 3: Enter username
        test.get().info("Step 3: Enter valid username: " + util.getProperty("username"));
        loginPage.enterLoginEmail(util.getProperty("username"));

        // Step 4: Enter password (hidden in logs)
        test.get().info("Step 4: Enter valid password: ****** (hidden for security)");
        loginPage.enterLoginPassword(util.getProperty("password"));

        // Step 5: Click on the login button
        test.get().info("Step 5: Click on the Login button");
        loginPage.clickLoginButton();

        // Step 6: Validate logged-in user
        String actualLoggedInUserText = homePage.getLoggedInUsername();
        test.get().info("Step 6: Validated logged-in user");
        test.get().info("Actual logged-in user text: " + actualLoggedInUserText);

        Assert.assertEquals(actualLoggedInUserText, EXPECTED_LOGGED_USER_TITLE, "Expected user to be '"+EXPECTED_LOGGED_USER_TITLE+"' but found: " + actualLoggedInUserText);

        loginPage.clickLogout();
        test.get().info("Step 7: Clicked on the logout button");

        test.get().info("Test completed successfully: validLoginTest");
    }

    @Test
    public void verify_ShowErrorOnInvalidCredentials() throws Exception {
        test.get().info("❌ Scenario: User attempts to log in with invalid credentials and receives an error message.");
        // Step 1: Open the application URL
        test.get().info("Test started: Verify login functionality with invalid credentials");

        test.get().info("Step 1: Open the application URL: " + util.getProperty("url"));

        // Step 2: Click on the signup/login button
        test.get().info("Step 2: Click on the Sign Up / Login button");
        homePage.clickLoginSignupButton();

        // Step 3: Enter invalid username
        test.get().info("Step 3: Enter invalid username: " + "testuser");
        loginPage.enterLoginEmail("testuser@t.com");

        // Step 4: Enter invalid password (avoid logging sensitive info, but if necessary, log carefully)
        test.get().info("Step 4: Enter invalid password: ****** (hidden for security)");
        loginPage.enterLoginPassword("2122");

        // Step 5: Click on the login button
        test.get().info("Step 5: Click on the Login button");
        loginPage.clickLoginButton();

        // Step 6: Validate error message displayed for invalid login
        String actualErrorMessage = loginPage.getLoginErrorMsg();
        test.get().info("Step 6: Validate error message for invalid login");
        test.get().info("Error message displayed: " + actualErrorMessage);

        Assert.assertEquals(actualErrorMessage, EXPECTED_INVALID_LOGIN_ERROR_MESSAGE, "Error message mismatch!");

        test.get().info("Test completed successfully: inValidLoginTest");
    }
}