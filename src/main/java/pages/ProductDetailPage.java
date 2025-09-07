package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By productName = By.cssSelector("div.product-information h2");
    private By productCategory = By.xpath("//section/div/div/div[2]/div[2]/div[2]/div/p[1]");
    private By productPrice = By.cssSelector("div.product-information span span");
    private By productAvailability = By.xpath("//section/div/div/div[2]/div[2]/div[2]/div/p[2]");
    private By productCondition = By.xpath("//section/div/div/div[2]/div[2]/div[2]/div/p[3]");
    private By productBrand = By.xpath("//section/div/div/div[2]/div[2]/div[2]/div/p[4]");

    private By quantityInput = By.id("quantity");
    private By addToCartButton = By.cssSelector("button.btn.btn-default.cart");
    private By viewCartButton = By.cssSelector("a[href='/view_cart'] u");

    private By writeYourReview = By.cssSelector("a[href='#reviews']");
    private By yourNameInput = By.id("name");
    private By emailAddress = By.id("email");
    private By addReviewHere = By.id("review");
    private By submitButton = By.id("button-review");

    private By successMessage = By.cssSelector("div.alert-success.alert span");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Getters
    public WebElement getProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
    }

    public WebElement getProductCategory() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productCategory));
    }

    public WebElement getProductPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
    }

    public WebElement getProductAvailability() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productAvailability));
    }

    public WebElement getProductCondition() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productCondition));
    }

    public WebElement getProductBrand() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productBrand));
    }

    // Actions
    public ProductDetailPage setQuantity(String value) {
        WebElement qty = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        qty.clear();
        qty.sendKeys(value);
        return this;
    }
 // Checks if product name is displayed
    public boolean isProductNameDisplayed() {
        return getProductName().isDisplayed();
    }

    // Checks if product category is displayed
    public boolean isCategoryDisplayed() {
        return getProductCategory().isDisplayed();
    }

    // Checks if product price is displayed
    public boolean isPriceDisplayed() {
        return getProductPrice().isDisplayed();
    }

    // Checks if availability is displayed
    public boolean isAvailabilityDisplayed() {
        return getProductAvailability().isDisplayed();
    }

    // Checks if condition is displayed
    public boolean isConditionDisplayed() {
        return getProductCondition().isDisplayed();
    }

    // Checks if brand is displayed
    public boolean isBrandDisplayed() {
        return getProductBrand().isDisplayed();
    }


    public ProductDetailPage clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        return this;
    }

    public CartPage clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
        return new CartPage(driver);
    }

    // Review
    public WebElement getWriteYourReview() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(writeYourReview));
    }

    public ProductDetailPage writeReview(String name, String email, String reviewText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(yourNameInput)).sendKeys(name);
        driver.findElement(emailAddress).sendKeys(email);
        driver.findElement(addReviewHere).sendKeys(reviewText);
        driver.findElement(submitButton).click();
        return this;
    }

    public WebElement getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }
}
