package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;

/**
 * Beginner-friendly Contact Us page object using plain Selenium
 */
public class ContactUs {

    private WebDriver driver;

    // ----------------- Locators -----------------
    private By getInTouchText = By.cssSelector("h2.title:nth-child(2)");
    private By nameInput = By.name("name");
    private By emailInput = By.name("email");
    private By subjectInput = By.name("subject");
    private By messageInput = By.id("message");
    private By uploadFileInput = By.name("upload_file");
    private By submitButton = By.name("submit");
    private By alertSuccess = By.cssSelector(".status.alert.alert-success");
    private By homePageButton = By.cssSelector("a.btn.btn-success");

    // ----------------- Constructor -----------------
    public ContactUs(WebDriver driver) {
        this.driver = driver;
    }

    // ----------------- Methods -----------------

    /** Get the 'Get In Touch' heading element */
    public WebElement getGetInTouch() {
        return driver.findElement(getInTouchText);
    }

    /** Fill the contact form with sample data */
    public ContactUs fillForm() {
        driver.findElement(nameInput).sendKeys("Robert");
        driver.findElement(emailInput).sendKeys("robert@test.pl");
        driver.findElement(subjectInput).sendKeys("Test Subject");
        driver.findElement(messageInput).sendKeys("This is a test message for Contact Us form.");

        // Upload a file from project folder (make sure file exists)
        driver.findElement(uploadFileInput)
              .sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\sample.txt");
        return this; // for method chaining
    }
    
    public ContactUs uploadFile() {
        // Make sure this file exists on your machine
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\sample.txt";
        driver.findElement(uploadFileInput).sendKeys(filePath);
        return this;
    }

    /** Click Submit button */
    public ContactUs submitButtonClick() {
        driver.findElement(submitButton).click();
        return this;
    }

    /** Accept alert popup if it appears */
    public ContactUs okButtonClick() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            // No alert appeared; ignore
        }
        return this;
    }

    /** Get the success message element */
    public WebElement getAlertSuccess() {
        return driver.findElement(alertSuccess);
    }

    /** Click Home button and return HomePage object */
    public HomePage homePageButtonClick() {
        driver.findElement(homePageButton).click();
        return new HomePage(driver);
    }
}
