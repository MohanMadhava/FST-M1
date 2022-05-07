package activities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Activity4 {
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "<device name>");
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.oneplus.dialer");
        caps.setCapability("appActivity", "com.android.dialer.oneplus.activity.OPDialtactsActivity");
        caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<>(appServer, caps);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void addContact() {
        // Wait for app to load
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Create new contact")));
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
        // Click on add new contact floating button
        driver.findElementByAccessibilityId("Create new contact").click();

        // Wait for fields to load
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.EditText[@content-desc=\"First name\"]")));

        // Find the first name, last name, and phone number fields
        MobileElement firstName = driver.findElementByAccessibilityId("First name");
        MobileElement lastName = driver.findElementByAccessibilityId("Last name");
        driver.findElement(MobileBy.AndroidUIAutomator(UiScrollable + ".scrollForward(7).scrollIntoView(text(\"Phone\"))"));
        MobileElement phoneNumber = driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Phone\"]");

        // Enter the text in the fields
        firstName.sendKeys("Mohan");
        lastName.sendKeys("Madhava");
        phoneNumber.sendKeys("9991284782");

        // Save the contact
        driver.findElementById("com.oneplus.contacts:id/menu_save").click();

        // Wait for contact card to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.oneplus.contacts:id/detailInfoContainer")));

        // Assertion
        MobileElement mobileCard = driver.findElementById("com.oneplus.contacts:id/detailInfoContainer");
        Assert.assertTrue(mobileCard.isDisplayed());

        String contactName = driver.findElementById("com.oneplus.contacts:id/displayNameTextView").getText();
        Assert.assertEquals(contactName, "Mohan Madhava");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
