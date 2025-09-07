package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;

public class ExistingEmail extends BasicTest {

    @Test(description = "Test Case 5: Register User with existing email")
    public void registerUserWithExistingEmail() {
        driver.get("https://automationexercise.com/");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page is NOT visible!");

        SignupPage signupPage = homePage.clickSignupLink();
        Assert.assertTrue(signupPage.isSignupFormVisible(), "❌ 'New User Signup!' is NOT visible!");

        signupPage.enterName("Tejashwini");
        signupPage.enterEmail("chettipellitejashwini1@gmail.com"); // existing email
        signupPage.clickSignupButton();

        String actualError = signupPage.getEmailAlreadyExistErrorText();
        Assert.assertEquals(actualError, "Email Address already exist!", "❌ Error message does not match");
    }
}
