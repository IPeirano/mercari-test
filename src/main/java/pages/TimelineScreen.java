package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;


public class TimelineScreen {
    private WebDriver driver;
    private WebDriverWait wait;

    public TimelineScreen() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(id = "myPageLogo")
    private WebElement myPage;

    public void goToMyPage() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(myPage));
            myPage.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
            e.printStackTrace();
        }
    }

    @FindBy(id = "navSearchInput")
    private WebElement searchBar;

    public void searchProduct(String productName) throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchBar));
            searchBar.sendKeys(productName);
            searchBar.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            Log.error("Error at " + e);
            e.printStackTrace();
        }
    }


}
