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

public class TaskProjectActivity1 {
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId","c3437723");
        caps.setCapability("platformName","android");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("appPackage","com.google.android.apps.tasks");
        caps.setCapability("appActivity",".ui.TaskListsActivity");
        caps.setCapability("noReset",true);

        // Server URL
        URL serverURl = new URL("http://localhost:4723/wd/hub");

        //Driver Initialization
        driver = new AndroidDriver<>(serverURl,caps);
        wait=new WebDriverWait(driver,5);
    }

    @Test
    public  void GoogleTask()
    {
        //Click the button to add a new task.
        driver.findElementById("tasks_fab").click();
        //Add Task1:
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("add_task_title")));
        driver.findElementById("add_task_title").sendKeys("Complete Activity with Google Tasks");
        //Save It
        driver.findElementById("add_task_done").click();
        //Add Task2:
        driver.findElementById("tasks_fab").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("add_task_title")));
        driver.findElementById("add_task_title").setValue("Complete Activity with Google Keep");
        //Save It
        driver.findElementById("add_task_done").click();
        //Add Task3:
        driver.findElementById("tasks_fab").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("add_task_title")));
        driver.findElementById("add_task_title").setValue("Complete the second Activity Google Keep");
        //Save It
        driver.findElementById("add_task_done").click();
        //Wait till all the Tasks got added
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("(//android.widget.FrameLayout[@content-desc=\"Complete the second Activity Google Keep\"])[1]/android.view.ViewGroup/android.widget.TextView")));
        String first=driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Complete Activity with Google Tasks\"]/android.view.ViewGroup/android.widget.TextView").getText();
        String second=driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Complete Activity with Google Keep\"]/android.view.ViewGroup/android.widget.TextView").getText();
        String third=driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Complete the second Activity Google Keep\"]/android.view.ViewGroup/android.widget.TextView").getText();
        //Verify the Result
        Assert.assertEquals(first, "Complete Activity with Google Tasks");
        Assert.assertEquals(second, "Complete Activity with Google Keep");
        Assert.assertEquals(third, "Complete the second Activity Google Keep");

        //Mark Tasks as Complete One by One
        driver.findElementByXPath("(//android.view.View[@content-desc=\"Mark as complete\"])[3]").click();
        driver.findElementByXPath("(//android.view.View[@content-desc=\"Mark as complete\"])[2]").click();
        driver.findElementByXPath("(//android.view.View[@content-desc=\"Mark as complete\"])[1]").click();
        System.out.println("All the Tasks got  completed");
    }
}
