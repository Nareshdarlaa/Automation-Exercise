package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.SignupPage;

public class RegisterUserStep {

    private static WebDriver driver;
    private SignupPage signupPage;
    private HomePage homePage;

    @Given("Launch browser")
    public void launch_browser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser launched successfully");
    }

    @When("Navigate to url {string}")
    public void navigate_to_url(String url) {
        driver.get(url);
        homePage = new HomePage(driver);
        signupPage = new SignupPage(driver);
        System.out.println("Navigated to URL: " + url);
    }

    @Then("Verify that home page is visible successfully")
    public void verify_home_page_visible() {
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");
    }

    @When("Click on {string} button")
    public void click_on_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Signup / Login")) {
            homePage.clickSignupLink();
        } else if (buttonName.equalsIgnoreCase("Signup")) {
            signupPage.clickSignupButton();
        }
    }
    
 // For steps like Click "Signup" button
    @When("Click {string} button")
    public void click_button(String buttonName) {
        clickButtonHelper(buttonName);
    }

    private void clickButtonHelper(String buttonName) {
        if (buttonName.equalsIgnoreCase("Signup / Login")) {
            homePage.clickSignupLink();
        } else if (buttonName.equalsIgnoreCase("Signup")) {
            signupPage.clickSignupButton();
        }
    }

    @Then("Verify {string} is visible")
    public void verify_text_visible(String expectedText) {
        Assert.assertTrue(signupPage.isSignupFormVisible(), expectedText + " is not visible!");
    }

    @When("Enter name {string} and email address {string}")
    public void enter_name_and_email(String name, String email) {
        signupPage.enterName(name);
        signupPage.enterEmail(email);
    }

    @Then("Verify error message {string} is displayed")
    public void verify_error_message(String expectedError) {
        String actualError = signupPage.getEmailAlreadyExistErrorText();
        Assert.assertEquals(actualError, expectedError, "Error message does not match!");
    }

    @And("Close the browser")
    public void close_the_browser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully");
        }
    }

    // Provide access to WebDriver for Page Objects
    public static WebDriver getDriver() {
        return driver;
    }
}
