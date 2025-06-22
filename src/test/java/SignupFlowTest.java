import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.pages.HomePage;
import com.akshay.automationexecrices.pages.SignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupFlowTest extends BaseTest {
    private static final String EXPECTED_LOGGED_USER_TITLE = "Logged in as ";
    private static final String EXPECTED_CONFIRMATION_MSG = "ACCOUNT CREATED!";
    private static final String EXPECTED_DELETION_CONFIRMATION_MSG = "ACCOUNT DELETED!";
    private HomePage homePage;
    private SignupPage signupPage;

    @Test
    public void verify_RegisterAndDeleteAccountSuccessfully() throws Exception {
        test.get().info("✅ Scenario: User signs up with valid details, logs in automatically, and successfully deletes their account.");

        test.get().info("✅ Test started: Verifying the user registration functionality.");
        // Step 1: Open the application URL
        test.get().info("Step 1: Open the application URL: " + util.getProperty("url"));
        signupPage = new SignupPage(driver);
        homePage= new HomePage(driver);

        // Step 2: Click on the signup/login button
        test.get().info("Step 2: Click on the Sign Up / Login button");
        homePage.clickLoginSignupButton();

        test.get().info("Step 3: Initial signup – Entered Name and Email, then clicked on the Signup button");
        // Step 1: Fill name and email
        test.get().info("Name : " +util.getProperty("userFirstName") + " " +util.getProperty("userLastName"));
        test.get().info("Email : " + util.getProperty("userEmail"));
        signupPage.fillInitialSignup(
                util.getProperty("userFirstName") + " " +util.getProperty("userLastName"),
                util.getProperty("userEmail")
        );

        test.get().info("Step 4: Filled in the remaining registration details (Password, First Name, Last Name, Street, State, City, Zipcode, Mobile)");
        test.get().info("===== Runtime Properties =====");
        test.get().info("Full Name: " + util.getProperty("userFirstName") + " " + util.getProperty("userLastName"));
        test.get().info("Email: " + util.getProperty("userEmail"));
        test.get().info("Password: ********");
        test.get().info("First Name: " + util.getProperty("userFirstName"));
        test.get().info("Last Name: " + util.getProperty("userLastName"));
        test.get().info("Street: " + util.getProperty("userStreet"));
        test.get().info("State: " + util.getProperty("userState"));
        test.get().info("City: " + util.getProperty("userCity"));
        test.get().info("Zip Code: " + util.getProperty("userZipcode"));
        test.get().info("Mobile Number: " + util.getProperty("userMobileNumber"));
        test.get().info("===== End of Runtime Properties =====");

        signupPage.fillDetailsFormAndLog();

        test.get().info("Step 5: Verified account creation by checking the confirmation message.");
        String confirmation = signupPage.getConfirmationMessage();
        Assert.assertEquals(confirmation, EXPECTED_CONFIRMATION_MSG);
        test.get().info(EXPECTED_CONFIRMATION_MSG);

        signupPage.continueToHome();

        test.get().info("Step 6: Redirected to the home screen and validated the logged-in username.");
        // Step 6: Validate logged-in user
        String actualLoggedInUserText = homePage.getLoggedInUsername();
        test.get().info("Step 7: Validated logged-in user"+ actualLoggedInUserText);

        Assert.assertEquals(actualLoggedInUserText, EXPECTED_LOGGED_USER_TITLE+util.getProperty("userFirstName") +" "+ util.getProperty("userLastName"), "Logged-in username mismatch!");
        test.get().info("Step 8: Click on the 'Delete' button to initiate account deletion..");
        signupPage.deletionAccount();
        String accountDeletionConfirmation = signupPage.getConfirmationDeleteAccountMessage();
        Assert.assertEquals(accountDeletionConfirmation, EXPECTED_DELETION_CONFIRMATION_MSG);

        test.get().info("Step 10: Verified account deletion by validating the confirmation message is displayed.");
        test.get().info(EXPECTED_DELETION_CONFIRMATION_MSG);

    }
}