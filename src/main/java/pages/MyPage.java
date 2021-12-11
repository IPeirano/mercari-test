package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;

public class MyPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MyPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "personal-information-label")
    private WebElement personalInformation;

    public void goToPersonalInformation() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(personalInformation));
            personalInformation.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
            e.printStackTrace();
        }
    }
}
