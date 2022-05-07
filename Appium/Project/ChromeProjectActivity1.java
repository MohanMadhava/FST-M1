package liveprojects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ChromeProjectActivity1 {
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId","c3437723");
        caps.setCapability("platformName","android");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("appPackage","com.android.chrome");
        caps.setCapability("appActivity","com.google.android.apps.chrome.Main");
        caps.setCapability("noReset",true);

        // Server URL
        URL serverURl = new URL("http://localhost:4723/wd/hub");

        //Driver Initialization
        driver = new AndroidDriver<>(serverURl,caps);
        wait=new WebDriverWait(driver,20);
        driver.get("https://www.training-support.net/selenium");
    }

    @Test
    public  void addTodo() throws InterruptedException {
        //Scroll to find the To-Do List card and click it.
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
        // Wait for the page to load
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.view.View")));
        // Scroll element into view and click it
        driver.findElement(MobileBy.AndroidUIAutomator(UiScrollable + ".scrollForward(7).scrollIntoView(text(\"To-Do List\"))"))
                .click();
        //driver.findElementByAccessibilityId("To-Do List \uF0AE Elements get added at run time").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.className("android.widget.EditText")));
        driver.findElementByClassName("android.widget.EditText").sendKeys("Add tasks to list");
        driver.findElementByClassName("android.widget.Button").click();
        Thread.sleep(4000);
        driver.findElementByClassName("android.widget.EditText").sendKeys("Add tasks two");
        driver.findElementByClassName("android.widget.Button").click();
        List<MobileElement> allTask =driver.findElementsByClassName("android.widget.TextView");
        //Get number of tasks
        int numtask = allTask.size();
        System.out.println("Total number of tasks added are "+numtask);
        //Click on each of the tasks added to strike them out.
        driver.findElementByClassName("android.widget.TextView").click();
        driver.findElementByClassName("android.widget.TextView").click();
        //clear the List
        driver.findElementByClassName("android.view.View").click();
        //Add assertions to verify that the test has passed or failed.
        Assert.assertEquals(2,numtask);
    }
}
