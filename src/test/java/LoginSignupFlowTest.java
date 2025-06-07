import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.pages.LoginSignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.akshay.automationexecrices.utils.DriverManager.webDriver;

public class LoginSignupFlowTest extends BaseTest {

    public String expectedLoggedUserTitle;
    private static final Logger logger = LogManager.getLogger(LoginSignupFlowTest.class);

    @Test
    public void signUp() {
        LoginSignupPage loginSignup = new LoginSignupPage(webDriver);
        loginSignup.clickLoginSignupButton();
        loginSignup.enterName("Akshay");
        loginSignup.enterEmail("akshay@test.com");
        loginSignup.clickSignupButton();
    }

    @Test
    public void login() {
        expectedLoggedUserTitle = "Logged in as akshay";
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        loginSignupPage.clickLoginSignupButton();
        loginSignupPage.enterLoginEmail("akshay@test2.com");
        loginSignupPage.enterLoginPassword("akshay@2");
        loginSignupPage.clickLoginButton();

        String actualLoggedInUserText = loginSignupPage.getLoggedInUsername();
        logger.info("Actual logged-in user text: " + actualLoggedInUserText);

        Assert.assertEquals(actualLoggedInUserText, expectedLoggedUserTitle, "Logged-in username mismatch!");


        Assert.assertEquals(actualLoggedInUserText, expectedLoggedUserTitle,
                "Logged-in username mismatch!");
    }
}
