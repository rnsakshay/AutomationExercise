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
        logger.info("Step 1: Open the application URL: " + util.getProperty("url"));
        SignupPage signupPage = new SignupPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Step 2: Click on the signup/login button
        logger.info("Step 2: Click on the Sign Up / Login button");
        loginPage.clickLoginSignupButton();

        logger.info("Step 3: Initial signup – Entered Name and Email, then clicked on the Signup button");
        // Step 1: Fill name and email
        logger.info("Name : " +util.getProperty("userFirstName") + " " +util.getProperty("userLastName"));
        logger.info("Email : " + util.getProperty("userEmail"));
        signupPage.fillInitialSignup(
                util.getProperty("userFirstName") + " " +util.getProperty("userLastName"),
                util.getProperty("userEmail")
        );

        logger.info("Step 4: Filled in the remaining registration details (Password, First Name, Last Name, Street, State, City, Zipcode, Mobile)");
        logger.info("===== Runtime Properties =====");
        logger.info("Name: " +util.getProperty("userFirstName") + " " +util.getProperty("userLastName"));
        logger.info("Email: " + util.getProperty("userEmail"));
        logger.info("Password: ********");
        ;
        logger.info("First Name: " + util.getProperty("userFirstName"));
        logger.info("Last Name: " +util.getProperty("userLastName"));
        logger.info("Street: " + util.getProperty("userStreet"));
        logger.info("State: " + util.getProperty("userState"));
        logger.info("City: " + util.getProperty("userCity"));
        logger.info("Zipcode: " + util.getProperty("userZipcode"));
        logger.info("Mobile: " + util.getProperty("userMobileNumber"));
        logger.info("===== End Runtime Properties =====");
        signupPage.fillDetailsFormAndLog();

        logger.info("Step 5: Verified account creation by checking the confirmation message.");
        String confirmation = signupPage.getConfirmationMessage();
        Assert.assertEquals(confirmation, EXPECTED_CONFIRMATION_MSG);
        logger.info(EXPECTED_CONFIRMATION_MSG);

        signupPage.continueToHome();

        logger.info("Step 6: Redirected to the home screen and validated the logged-in username.");
        // Step 6: Validate logged-in user
        String actualLoggedInUserText = loginPage.getLoggedInUsername();
        logger.info("Step 7: Validated logged-in user"+ actualLoggedInUserText);

        Assert.assertEquals(actualLoggedInUserText, EXPECTED_LOGGED_USER_TITLE+util.getProperty("userFirstName") +" "+ util.getProperty("userLastName"), "Logged-in username mismatch!");

        signupPage.deletionAccount();
        logger.info("Step 8: Verified account deletion by checking the confirmation message.");
        String accountDeletionConfirmation = signupPage.getConfirmationDeleteAccountMessage();
        Assert.assertEquals(accountDeletionConfirmation, EXPECTED_DELETION_CONFIRMATION_MSG);
        logger.info(EXPECTED_DELETION_CONFIRMATION_MSG);

    }
}