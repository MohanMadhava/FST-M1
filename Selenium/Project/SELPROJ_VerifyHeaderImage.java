import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SELPROJ_VerifyHeaderImage {
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
        WebElement header = driver.findElement(By.xpath("//img[@src='themes/default/images/company_logo.png?v=cK7kLsY0ftg72ZVHTYUT_g']"));
        String url = header.getAttribute("src");
        //Print the title of the page
        System.out.println("Page header is: " + url);
    }

    @AfterMethod
    public void afterMethod() {
        //Close the browser
        driver.quit();
    }
}
