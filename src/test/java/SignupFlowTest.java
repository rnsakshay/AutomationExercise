import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.pages.LoginPage;
import com.akshay.automationexecrices.pages.SignupPage;
import com.akshay.automationexecrices.utils.PropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupFlowTest extends BaseTest {
    private static final String EXPECTED_LOGGED_USER_TITLE = "Logged in as ";
    private static final Logger logger = LogManager.getLogger(SignupFlowTest.class);
    private static final String EXPECTED_CONFIRMATION_MSG = "ACCOUNT CREATED!";
    private static final String EXPECTED_DELETION_CONFIRMATION_MSG = "ACCOUNT DELETED!";

    @Test
    public void signupProcess() throws Exception {
        logger.info("Test started: testUserRegistrationWithRandomData");
        // Step 1: Open the application URL
        logger.info("Step 1: Open the application URL: " + properties.getProperty("url"));
        SignupPage signupPage = new SignupPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Step 2: Click on the signup/login button
        logger.info("Step 2: Click on the Sign Up / Login button");
        loginPage.clickLoginSignupButton();

        logger.info("Step 3: Initial signup – Entered Name and Email, then clicked on the Signup button");
        // Step 1: Fill name and email
        logger.info("Name : " + PropertyUtil.get("runtimeName"));
        logger.info("Email : " + PropertyUtil.get("runtimeEmail"));
        signupPage.fillInitialSignup(
                PropertyUtil.get("runtimeName"),
                PropertyUtil.get("runtimeEmail")
        );

        logger.info("Step 4: Filled in the remaining registration details (Password, First Name, Last Name, Street, State, City, Zipcode, Mobile)");
        logger.info("===== Runtime Properties =====");
        logger.info("Name: " + PropertyUtil.get("runtimeName"));
        logger.info("Email: " + PropertyUtil.get("runtimeEmail"));
        logger.info("Password: ********");
        ;
        logger.info("First Name: " + PropertyUtil.get("runtimeFirstName"));
        logger.info("Last Name: " + PropertyUtil.get("runtimeLastName"));
        logger.info("Street: " + PropertyUtil.get("runtimeStreet"));
        logger.info("State: " + PropertyUtil.get("runtimeState"));
        logger.info("City: " + PropertyUtil.get("runtimeCity"));
        logger.info("Zipcode: " + PropertyUtil.get("runtimeZipcode"));
        logger.info("Mobile: " + PropertyUtil.get("runtimeMobileNumber"));
        logger.info("===== End Runtime Properties =====");
        signupPage.fillDetailsFormAndLogToSheet();

        logger.info("Step 5: Verified account creation by checking the confirmation message.");
        String confirmation = signupPage.getConfirmationMessage();
        Assert.assertEquals(confirmation, EXPECTED_CONFIRMATION_MSG);
        logger.info(EXPECTED_CONFIRMATION_MSG);

        signupPage.continueToHome();

        logger.info("Step 6: Redirected to the home screen and validated the logged-in username.");
        // Step 6: Validate logged-in user
        String actualLoggedInUserText = loginPage.getLoggedInUsername();
        logger.info("Step 7: Validated logged-in user"+ actualLoggedInUserText);

        Assert.assertEquals(actualLoggedInUserText, EXPECTED_LOGGED_USER_TITLE+PropertyUtil.get("runtimeName"), "Logged-in username mismatch!");

        signupPage.deletionAccount();
        logger.info("Step 8: Verified account deletion by checking the confirmation message.");
        String accountDeletionConfirmation = signupPage.getConfirmationDeleteAccountMessage();
        Assert.assertEquals(accountDeletionConfirmation, EXPECTED_DELETION_CONFIRMATION_MSG);
        logger.info(EXPECTED_DELETION_CONFIRMATION_MSG);

    }
}