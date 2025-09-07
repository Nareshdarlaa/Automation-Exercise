package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.LoginPage;
import pages.LoggedHomePage;
import utils.ExtentManager;
import utils.TestListener;

@Listeners(TestListener.class) // attach listener here
public class Logout {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");

        // Set the driver in ExtentManager so listener can use it
        ExtentManager.setDriver(driver);
    }
    @Test(description = "Test Case 4: Logout User")
    public void logoutUser() {
        // Step 1-3: Verify Home Page
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4-5: Navigate to Login Page
        LoginPage loginPage = homePage.clickLoginLink();
        Assert.assertTrue(loginPage.isLoginFormVisible(), "Login form is not visible!");


        // Step 6-7: Enter valid credentials & login
        LoggedHomePage loggedHomePage = loginPage.loginAndGoToHome(
            "chettipellitejashwini1@gmail.com",
            "Tejashwini@123"
        );

        // Step 8: Verify logged in
        Assert.assertTrue(loggedHomePage.isLoggedIn(), "Login failed! 'Logged in as' text not found.");

        // Step 9: Logout
        LoginPage afterLogout = loggedHomePage.logoutButtonClick();

        // Step 10: Verify redirected to login page
        Assert.assertTrue(afterLogout.isLoginFormVisible(), "User not redirected to login page after logout!");
    }

    @AfterMethod
    public void tearDown() {
    	
    }
    @AfterSuite
    public void closeBrowser() {
        ExtentManager.quitDriver();  // Quit only once after all tests
    }
}
