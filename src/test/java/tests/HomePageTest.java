package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.LoginPage;
import utils.ExtentManager;
import utils.TestListener;

@Listeners(TestListener.class)
public class HomePageTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();      // create new driver for each test
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");

        ExtentManager.setDriver(driver);  // pass driver to listener
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void verifyHomePageIsVisible() {
        Assert.assertTrue(homePage.isHomePageVisible(), " Home page is NOT visible!");
    }

    @Test(priority = 2)
    public void verifyNavigationToLoginPage() {
        LoginPage loginPage = homePage.clickLoginLink(); // returns LoginPage
        Assert.assertTrue(loginPage.isLoginFormVisible(), " Navigation to login page failed!");
    }

    @Test(priority = 3)
    public void verifyNavigationToProductsPage() {
        homePage.clickProducts();
        Assert.assertTrue(driver.getCurrentUrl().contains("products"), " Navigation to Products page failed!");
    }

 


    @AfterMethod
    public void tearDown() {
        ExtentManager.quitDriver(); // quit driver after each test
    }
}
