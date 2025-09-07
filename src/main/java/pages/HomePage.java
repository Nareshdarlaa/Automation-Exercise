package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ----------------- Locators -----------------
    private By homePageHeading = By.xpath("//div[@id='slider']//h2");
    private By girlImgResponsive = By.cssSelector("div[class='item active'] img[alt='demo website for practice']");
    private By signupLoginButton = By.cssSelector("a[href='/login']");
    private By contactUsButton = By.linkText("Contact us");
    private By testCasesButton = By.cssSelector("a[href='/test_cases']");
    private By productsButton = By.cssSelector("a[href='/products']");
    private By cartButton = By.cssSelector("a[href='/view_cart']");
    private By viewProduct1Button = By.cssSelector("a[href='/product_details/1']");
    private By addToCartButtons = By.cssSelector("a.btn.btn-default.add-to-cart");

    // Footer
    private By subscription = By.cssSelector("div[class='single-widget'] h2");
    private By subscribeEmailInput = By.id("susbscribe_email");
    private By subscribeButton = By.id("subscribe");
    private By alertSuccessSubscribe = By.id("success-subscribe");

    // ----------------- Constructor -----------------
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ----------------- Actions -----------------

    /** Verify home page is visible */
    public boolean isHomePageVisible() {
        return driver.getTitle().contains("Automation Exercise");
    }


    /** Navigate to Login page */
    public LoginPage clickLoginLink() {
        driver.findElement(signupLoginButton).click();
        return new LoginPage(driver);
    }

    /** Navigate to Signup page */
    public SignupPage clickSignupLink() {
        driver.findElement(signupLoginButton).click();
        return new SignupPage(driver);
    }

    /** Navigate to Contact Us page */
    public ContactUs clickContactUsButton() {
        WebElement contactUs = wait.until(ExpectedConditions.elementToBeClickable(contactUsButton));
        contactUs.click();
        return new ContactUs(driver);
    }

    /** Navigate to Test Cases page */
    public void clickTestCases() {
        driver.findElement(testCasesButton).click();
    }

    /** Get Test Cases heading text */
    public String getTestCasesHeadingText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Test Cases']"))).getText();
    }

    /** Navigate to Products page */
    public ProductsPage clickProducts() {
        driver.findElement(productsButton).click();
        return new ProductsPage(driver);
    }

    /** Navigate to Cart page */
    public CartPage clickCart() {
        driver.findElement(cartButton).click();
        return new CartPage(driver);
    }

    /** Click View Product for Product 1 */
    public void clickViewProduct1() {
        driver.findElement(viewProduct1Button).click();
    }

    /** Add a product to cart (hover & click) */
    public void clickAddToCart(WebElement addButton) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addButton);
        new Actions(driver).moveToElement(addButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

        // Handle popup modal if it appears
        try {
            By continueBtn = By.cssSelector("button[data-dismiss='modal']");
            wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        } catch (TimeoutException ignored) {
        }
    }

    /** Get all "Add to Cart" buttons */
    public List<WebElement> getAllAddToCartButtons() {
        return driver.findElements(addToCartButtons);
    }

    // ----------------- Footer Section -----------------

    /** Get Home Page Heading element */
    public WebElement getHomePageHeading() {
        return driver.findElement(homePageHeading);
    }

    /** Subscription section element */
    public WebElement getSubscriptionElement() {
        return driver.findElement(subscription);
    }

    /** Subscribe with email */
    public void subscribe(String email) {
        driver.findElement(subscribeEmailInput).sendKeys(email);
        driver.findElement(subscribeButton).click();
    }

    /** Get subscription success message element */
    public WebElement getAlertSuccessSubscribe() {
        return driver.findElement(alertSuccessSubscribe);
    }
}
