package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    // ------------------ Locators ------------------
    private By emailInput = By.cssSelector("input[data-qa='login-email']");
    private By passwordInput = By.cssSelector("input[data-qa='login-password']");
    private By loginBtn = By.cssSelector("button[data-qa='login-button']");
    private By errorMsg = By.xpath("//form/p");
    private By loginFormTitle = By.xpath("//h2[text()='Login to your account']");
    private By loggedInText = By.xpath("//a[contains(text(),'Logged in as')]");

    // ------------------ Constructor ------------------
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ------------------ Actions ------------------
    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public LoggedHomePage loginAndGoToHome(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        return new LoggedHomePage(driver);
    }

    // ------------------ Validations ------------------
    public boolean isLoginFormVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement form = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(loginFormTitle)
            );
            return form.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        try {
            return driver.findElement(errorMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorText() {
        try {
            return driver.findElement(errorMsg).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLoginSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInText));
            return text.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
