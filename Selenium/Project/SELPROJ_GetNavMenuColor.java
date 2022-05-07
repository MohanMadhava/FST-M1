import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SELPROJ_GetNavMenuColor {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open browser
        driver.get("http://alchemy.hguy.co/crm");
    }

    @Test
    public void exampleTestCase() {
        // Check the title of the page
        WebElement un = driver.findElement(By.cssSelector("#user_name"));
        WebElement pw = driver.findElement(By.cssSelector("#username_password"));
        WebElement btn_login = driver.findElement(By.cssSelector("#bigbutton"));



        un.sendKeys("admin");
        pw.sendKeys("pa$$w0rd");
        btn_login.click();
        WebElement btn_dashboard= driver.findElement(By.cssSelector("#tab0"));
        Assert.assertEquals(true, btn_dashboard.isDisplayed());
        System.out.println("Home Page is Displayed");
        WebElement nav_bar = driver.findElement(By.cssSelector(".desktop-toolbar"));
        String color=nav_bar.getCssValue("color");
        System.out.println("The Color of the navigation bar is ="+ color);
    }

    @AfterMethod
    public void afterMethod() {
        //Close the browser
        driver.quit();
    }
}
