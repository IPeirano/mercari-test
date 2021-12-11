import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.*;
import utils.*;

import java.util.List;

public class Tests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static TimelineScreen timelineScreen;
    static MyPage myPage;
    static PersonalInformation personalInformation;
    static ShippingAddress shippingAddress;
    static ItemDetails itemDetails;
    ExtentTest test;
    static ExtentReports report = new ExtentReports("report/Test Report " + Utils.generateTimestamp() + ".html");

    @BeforeSuite
    public void initializeObjects() {

        // Initialize all POM & selected driver
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        timelineScreen = new TimelineScreen();
        myPage = new MyPage();
        personalInformation = new PersonalInformation();
        shippingAddress = new ShippingAddress();
        itemDetails = new ItemDetails();

        // Reports
        TestCases[] tests = TestCases.values();
        test = report.startTest(tests[Utils.testCount].getTestName());
        Utils.testCount++;

        // Log
        Log.getLogData(Log.class.getName());
        Log.startTest(tests[Utils.testCount].getTestName());
    }

    @Test
    public void addNewAddress() {
        initializeObjects();
        driver.get(Constants.URL);
        test.log(LogStatus.INFO, "Navigating to " + Constants.URL);
        Log.info("Navigating to " + Constants.URL);

        try {
            timelineScreen.goToMyPage();
            myPage.goToPersonalInformation();
            personalInformation.goToShippingAddress();
            shippingAddress.addAddress();
            List<WebElement> newAddress = shippingAddress.addNewAddress();
            shippingAddress.checkAddress(newAddress);
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }

    @Test
    public void searchProduct() {
        try {
            timelineScreen.searchProduct(Constants.PRODUCT_NAME);
            itemDetails.goToDetailScreen();
            itemDetails.wait.until(ExpectedConditions.visibilityOf(itemDetails.productTitle));
            itemDetails.checkProduct(Constants.PRODUCT_NAME);
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }

    @AfterSuite
    public void closeObjects() {
        report.endTest(test);
        report.flush(); // save report file from all previously ended tests
        driver.close();
        DriverSingleton.closeObjectInstance();
    }

}