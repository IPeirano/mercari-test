package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Log;

import java.time.Duration;

public class ItemDetails {
    private WebDriver driver;
    public WebDriverWait wait;

    public ItemDetails() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // select the third element from all the products in search result
    @FindBy(xpath = "/html/body/main/div/div[1]/section/ol/li[3]/div/div")
    private WebElement productView;

    public void goToDetailScreen() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(productView));
            productView.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
            e.printStackTrace();
        }
    }

    @FindBy(className = "ui-pdp-title")
    public WebElement productTitle;

    public void checkProduct(String productName) throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(productTitle));
            if (driver.getPageSource().contains(productName)) {
                Assert.assertTrue(true, productName + " is in element: " + productTitle.getTagName() + " with text " + productTitle.getText());
                System.out.println(productName + " is in element: <" + productTitle.getTagName() + " " + productTitle.getAttribute("class") + "> with text " + productTitle.getText());
            } else {
                Assert.assertFalse(false, "No element found");
            }
        } catch (Exception e) {
            Log.error("Error at " + e);
            e.printStackTrace();
        }
    }
}

