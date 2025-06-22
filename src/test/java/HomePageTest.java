import com.akshay.automationexecrices.base.BaseTest;
import com.akshay.automationexecrices.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private static final String EXPECTED_HOMEPAGETITLE = "Automation Exercise";
    private HomePage homePage;


    @Test
    public void verifyHomePageTitle(){
        homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getTitle(), EXPECTED_HOMEPAGETITLE, "Page title does not match!");
    }
}
