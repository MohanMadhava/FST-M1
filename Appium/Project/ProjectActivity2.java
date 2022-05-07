package liveprojects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ProjectActivity2 {
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId","c3437723");
        caps.setCapability("platformName","android");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("appPackage","com.google.android.keep");
        caps.setCapability("appActivity",".activities.BrowseActivity");
        caps.setCapability("noReset",true);

        // Server URL
        URL serverURl = new URL("http://localhost:4723/wd/hub");

        //Driver Initialization
        driver = new AndroidDriver<>(serverURl,caps);
        wait=new WebDriverWait(driver,5);
    }

    @Test
    public  void addNotes()
    {
        driver.findElementById("new_note_button").click();
        //Add a title for the note and add a small description.
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("editable_title")));
        driver.findElementById("editable_title").click();
        driver.findElementById("editable_title").setValue("Task1");
        //Click the Create New Note button to add a new Note.
        driver.findElementById("edit_note_text").setValue("Notes Description");
        //Press the back button and make an assertion to ensure that the note was added.
        driver.findElementByAccessibilityId("Open navigation drawer").click();
        //Verify the Result
        String notes=driver.findElementById("index_note_title").getText();
        Assert.assertEquals(notes, "Task1");
        System.out.println("The Notes added is "+notes);
    }
}
