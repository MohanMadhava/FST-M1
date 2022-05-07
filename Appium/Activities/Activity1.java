package activities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.net.MalformedURLException;
import java.net.URL;

public class Activity1 {
    AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId","c3437723");
        caps.setCapability("platformName","android");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("appPackage","com.oneplus.calculator");
        caps.setCapability("appActivity",".Calculator");
        caps.setCapability("noReset",true);

        // Server URL
        URL serverURl = new URL("http://localhost:4723/wd/hub");

        //Driver Initialization
        driver = new AndroidDriver<>(serverURl,caps);

    }
    @Test
    public  void additionTest()
    {
    //Find 2 Digit and CLick it
        //find first number and Click
        driver.findElementById("digit_6").click();
        //CLick on Addition Button
        driver.findElementByAccessibilityId("plus").click();
        //Find second number and Click
        driver.findElementById("digit_2").click();
        driver.findElementByAccessibilityId("equals").click();
        //Find the Result and Get Text
        String result=driver.findElementById("result").getText();
        System.out.println(result);
        //Verify the Result
        Assert.assertEquals(result, "8");
    }

}
