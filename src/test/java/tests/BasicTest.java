package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import utils.ExtentManager;

public class BasicTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        ExtentManager.setDriver(driver); // Set driver for listener
        driver.manage().window().maximize();
    }

    // Add this getter method
    public WebDriver getDriver() {
        return driver;
    }

    @AfterSuite
    public void teardownSuite() {
        if(driver != null) {
            driver.quit(); // Quit once after all tests
        }
    }
}
