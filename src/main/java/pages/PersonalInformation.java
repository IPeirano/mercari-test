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

public class PersonalInformation {
    private WebDriver driver;
    private WebDriverWait wait;

    public PersonalInformation() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(id = "shippingAddressId")
    private WebElement shippingAddress;

    public void goToShippingAddress() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(shippingAddress));
            shippingAddress.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
            e.printStackTrace();
        }
    }
}