import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private static final String EXPECTED_HOMEPAGETITLE = "Automation Exercise";
    private static final Logger logger = LogManager.getLogger(LoginFlowTest.class);
    private HomePage homePage;


    @Test
    public void verifyHomePageTitle(){
        homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getTitle(), EXPECTED_HOMEPAGETITLE, "Page title does not match!");
    }
}
