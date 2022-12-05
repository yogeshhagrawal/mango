package amazon;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AmazonHome;
import util.Browser;

import static util.EnvironmentReader.getBaseUri;
import static util.EnvironmentReader.getBrowser;

public class SearchTest extends BaseTest {

    private AmazonHome amazonHome;

    @BeforeTest
    public void launchWebApp() {
        driver = launchBrowser(Browser.valueOf(getBrowser()));
        driver.get(getBaseUri());
        amazonHome = new AmazonHome(driver);
    }

    @Test
    public void shouldSearchWithDefinedFilterCriteria() {

        //WHEN
        amazonHome.searchByText("Wrist Watches");
        amazonHome.filterSearch("Brand", "Titan");
        amazonHome.filterSearch("Display", "Analogue");
        amazonHome.filterSearch("Discount", "25% Off or more");
        amazonHome.filterSearch("Band Material", "Leather");

        //THEN
        amazonHome.verifyIfUserIsNavigatedToCorrectPageOnClickingItem(5);

    }

    @AfterTest
    public void shouldCloseBrowser() {
        driver.quit();
    }
}
