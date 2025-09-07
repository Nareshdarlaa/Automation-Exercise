package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

    WebDriver driver;

    @Given("User is on the Automation Exercise home page")
    public void user_is_on_home_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @When("User clicks on Signup \\/ Login button")
    public void user_clicks_on_signup_login_button() {
        driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']")).click();
    }


    @When("User enters valid email {string} and password {string}")
    public void user_enters_valid_email_and_password(String email, String password) {
        driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys(password);
    }

    @When("User enters invalid email {string} and password {string}")
    public void user_enters_invalid_email_and_password(String email, String password) {
        driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys(password);
    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();
    }

    @Then("Verify Logged in as {string} is visible")
    public void verify_logged_in_as_is_visible(String username) {
        String loggedText = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]")).getText();
        Assert.assertTrue("Expected username not found! Actual: " + loggedText,
                loggedText.contains(username));
        driver.quit();
    }


    @Then("Verify error message {string} is visible")
    public void verify_error_message_is_visible(String expectedMessage) {
        String actualMsg = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]")).getText();
        Assert.assertEquals(expectedMessage, actualMsg);
        driver.quit();
    }
}
