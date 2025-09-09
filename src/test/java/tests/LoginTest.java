package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.TestListener;

@Listeners(TestListener.class)
public class LoginTest extends BasicTest {     

    LoginPage loginPage;

    @BeforeMethod
    public void setupLogin() {
        driver.get("https://automationexercise.com/login");
        loginPage = new LoginPage(driver);
    }
//checking
    @Test(priority = 1)
    public void testValidLogin() {
        loginPage.login("darlanaresh25@gmail.com", "Naresh@123");
        Assert.assertTrue(loginPage.isLoginSuccessful(), " Login failed!");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        loginPage.login("wrong_email@example.com", "wrong_password");
        Assert.assertTrue(loginPage.isErrorDisplayed(), " Error message not displayed!");
    }
}
