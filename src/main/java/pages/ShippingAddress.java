package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Log;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ShippingAddress {
    private WebDriver driver;
    private WebDriverWait wait;

    public ShippingAddress() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(id = "add-btn")
    private WebElement addAddressButton;

    public void addAddress() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addAddressButton));
            addAddressButton.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
            e.printStackTrace();
        }
    }

    @FindBy(id = "ui-form__last-name")
    private WebElement lastNameInput;

    @FindBy(id = "ui-form__first-name")
    private WebElement firstNameInput;

    @FindBy(id = "ui-form__last-name-kana")
    private WebElement lastNameKanaInput;

    @FindBy(id = "ui-form__first-name-kana")
    private WebElement firstNameKanaInput;

    @FindBy(id = "ui-form__zip-code")
    private WebElement zipCodeInput;

    @FindBy(id = "ui-form__prefecture")
    private WebElement prefectureInput;

    @FindBy(id = "ui-form__ward")
    private WebElement wardInput;

    @FindBy(id = "ui-form__submit")
    private WebElement submitButton;

    // Add a new address and returns an array with all the elements

    public List<WebElement> addNewAddress() throws Exception {
        List<WebElement> newAddress = new ArrayList<>();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(lastNameInput));

            lastNameInput.sendKeys("Peirano");
            newAddress.add(lastNameInput);

            firstNameInput.sendKeys("Ignacio");
            newAddress.add(firstNameInput);

            lastNameKanaInput.sendKeys("ペイラノ");
            newAddress.add(lastNameKanaInput);

            firstNameKanaInput.sendKeys("イグナ-シ-オ-");
            newAddress.add(firstNameKanaInput);

            zipCodeInput.sendKeys("530-0000");
            newAddress.add(zipCodeInput);

            Select prefecture = new Select(prefectureInput);
            prefecture.selectByVisibleText("Osaka Fu");
            newAddress.add(prefectureInput);

            wardInput.sendKeys("Osaka Shi Kita Ku");
            newAddress.add(wardInput);

            submitButton.click();

        } catch (Exception e) {
            Log.error("Error at " + e);
            e.printStackTrace();
        }
        return newAddress;
    }

    // For each li tag (full address element) create an array with all nested elements (last name, first name, zip code...)
    // and compare it with all the fields in the previously created address
    // (assuming the new direction is on the first position using xpath for retrieving the first element in UL)

    public void checkAddress(List<WebElement> newAddress) throws Exception {
        try {
            List<WebElement> addressList = driver.findElements(By.xpath("/html/body/main/div/div[1]/ul/li[1][contains(tagName,'dd')]"));
            for (int i = 0; i < newAddress.size(); i++) {
                Assert.assertEquals(addressList.get(i).getText(), newAddress.get(i).getText());
            }
        } catch (Exception e) {
            Log.error("Error at " + e);
            e.printStackTrace();
        }
    }
}
