package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignupPage {
    WebDriver driver;

    private By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
    private By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
    private By signupBtn = By.cssSelector("button[data-qa='signup-button']");
    private By emailAlreadyExistMsg = By.xpath("//p[text()='Email Address already exist!']");
    private By signupFormTitle = By.xpath("//h2[text()='New User Signup!']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSignupFormVisible() {
        try {
            return driver.findElement(signupFormTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterName(String name) {
        driver.findElement(signupNameInput).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(signupEmailInput).sendKeys(email);
    }

    public void clickSignupButton() {
        driver.findElement(signupBtn).click();
    }

    public String getEmailAlreadyExistErrorText() {
        return driver.findElement(emailAlreadyExistMsg).getText();
    }
}
