package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

public class AmazonHome {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private By searchInput = By.xpath("//input[@type='text']");
    private By searchButton = By.xpath("//input[@id='nav-search-submit-button' and @type='submit']");
//  private By elementRefinements = By.xpath("//div[@id='s-refinements']//div[contains(@id,'Refinements')]");
//  private By elementFilter = By.xpath("//div[@id='filters']");

    public AmazonHome(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        actions = new Actions(this.driver);
    }

    public void searchByText(String searchText) {
        driver.findElement(searchInput).sendKeys(searchText);
        driver.findElement(searchButton).click();
    }

    public void filterSearch(String filter, String value) {
        By filterXpath = By.xpath("//span[contains(text(),'" + filter + "')]/../following-sibling::ul[1]//span[text()='" + value + "']");
        WebElement filterElement = driver.findElement(filterXpath);
        if (!filterElement.isDisplayed()) {
            try {
                WebElement seeMoreLink = driver.findElement(By.xpath("//span[contains(text(),'" + filter + "')]/../following-sibling::ul[1]//a[@aria-label='See more, " + filter + "']"));
                if (seeMoreLink.isDisplayed()) {
                    seeMoreLink.click();
                }
            } catch (NoSuchElementException e) {
                System.out.println("See More link is not available for filter = " + filter);
            }
        }

        actions.moveToElement(filterElement);
        filterElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterXpath));
        //Check if element is selected after clicking
        WebElement filterTextAfter = driver.findElement(filterXpath);
        checkIfElementTextIsSelected(filterTextAfter);
    }

    public void verifyIfUserIsNavigatedToCorrectPageOnClickingItem(int n) {
        String parentWindow = driver.getWindowHandle();
        WebElement itemLink = driver.findElement(By.xpath("//div[@data-component-type='s-search-result'][" + n + "]//div[contains(@class,'a-spacing-top-small')][1]//h2"));
        String itemDescription = itemLink.getText();
        actions.moveToElement(itemLink);
        itemLink.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String childWindow : windowHandles) {
            if (!parentWindow.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                String childWindowTitle = driver.getTitle();
                Assert.assertTrue(childWindowTitle.contains(itemDescription), "Title -- '" + childWindowTitle + "' Does Not Contain  -- '" + itemDescription + "'");
            }
        }
    }

    public void checkIfElementTextIsSelected(WebElement element) {
        String fontWeight = element.getCssValue("font-weight");
        boolean isBold = Integer.parseInt(fontWeight) >= 700 || "bold".equals(fontWeight) || "bolder".equals(fontWeight);
        Assert.assertTrue(isBold, "Expected font weight is greater than or equal to 700 but Actual is " + fontWeight);
    }

}
