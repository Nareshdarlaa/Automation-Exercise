package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegisterUser;
import utils.TestListener;

import java.time.Duration;
import java.util.UUID;

@Listeners(TestListener.class)
public class RegisterUserTest extends BasicTest {

    private RegisterUser registerPage;

    @BeforeMethod
    public void setupTest() {
        driver.get("https://automationexercise.com/");
        registerPage = new RegisterUser(driver);
    }

    @Test
    public void testRegisterUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 1: Click Signup/Login
        registerPage.clickSignupLogin();

        // Step 2: Verify "New User Signup!" visible
        Assert.assertTrue(registerPage.isNewUserSignupVisible(),
                " New User Signup not visible");

        // Step 3: Enter name & unique email
        String uniqueEmail = "testuser_" + UUID.randomUUID() + "@gmail.com";
        registerPage.enterNameAndEmail("JohnCena", uniqueEmail);

        // Step 4: Click Signup
        registerPage.clickSignupButton();

        // Step 5: Verify "Enter Account Information" visible
        Assert.assertTrue(registerPage.isEnterAccountInfoVisible(),
                " Enter Account Information not visible");

        // Step 6: Fill account details
        registerPage.fillAccountDetails("Test@123");

        // Dropdown selections
        new Select(registerPage.getDayDropdown()).selectByValue("10");
        new Select(registerPage.getMonthDropdown()).selectByValue("12");
        new Select(registerPage.getYearDropdown()).selectByValue("2003");

        // Step 7: Subscribe to newsletter & offers
        registerPage.subscribeToNewsletter();

        // Step 8: Fill address details
        registerPage.fillAddressDetails();

        // Select country
        new Select(registerPage.getCountryDropdown()).selectByVisibleText("India");

        // Step 9: Click Create Account (with JS fallback for ads overlay)
        WebElement createBtn = driver.findElement(By.xpath("//button[text()='Create Account']"));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(createBtn)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBtn);
        }

        // Step 10: Verify "Account Created!" visible
        Assert.assertTrue(registerPage.isAccountCreatedVisible(),
                " Account not created!");

        // Step 11: Click Continue
        registerPage.clickContinue();

        // Step 12: Delete Account
        registerPage.deleteAccount();

        // Step 13: Verify "Account Deleted!" visible
        Assert.assertTrue(registerPage.isAccountDeletedVisible(),
                " Account not deleted!");
    }

    @AfterMethod
    public void tearDownTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
