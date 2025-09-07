package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By titleTextCenter = By.cssSelector("h2.title.text-center"); // ALL PRODUCTS / SEARCHED PRODUCTS
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By productNameElements = By.cssSelector(".productinfo p"); // product names after search

    private By signupLoginLink = By.cssSelector("a[href='/login']");
    private By productWrappers = By.cssSelector(".features_items .product-image-wrapper"); // all products
    private By addToCartBtnOverlay = By.cssSelector(".features_items .product-overlay .add-to-cart");
    private By continueShoppingButton = By.cssSelector(".btn-success.close-modal.btn-block");
    private By viewCartButton = By.cssSelector("a[href='/view_cart'] u");

    // Category locators (example)
    private By womenCategory = By.xpath("//a[contains(text(),'Women')]");
    private By dressSubCategory = By.xpath("//a[contains(text(),'Dress') and parent::li[parent::ul[@class='nav nav-pills nav-stacked']]]");
    private By menCategory = By.xpath("//a[contains(text(),'Men')]");
    private By tShirtsSubCategory = By.xpath("//a[contains(text(),'Tshirts') and parent::li[parent::ul[@class='nav nav-pills nav-stacked']]]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ✅ Navigate to login page
    public LoginPage clickSignupLogin() {
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink));
        loginLink.click();
        return new LoginPage(driver);
    }

    // ✅ Heading (ALL PRODUCTS / SEARCHED PRODUCTS)
    public WebElement getTitleTextCenter() {
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(titleTextCenter));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", heading);
        return heading;
    }

    // ✅ Search product
    public void fillSearchProductInput(String productName) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    // ✅ Get product names after search
    public List<String> getProductsSearchNames() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameElements));
        List<String> names = new ArrayList<>();
        for (WebElement e : elements) {
            names.add(e.getText());
        }
        return names;
    }

    // ✅ Click View Product of first product
    public ProductDetailPage viewProductOfFirstProductButtonClick() {
        List<WebElement> viewButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(".features_items .choose a[href*='/product_details/']")));
        WebElement firstViewProduct = viewButtons.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstViewProduct);
        firstViewProduct.click();
        return new ProductDetailPage(driver);
    }

    // ✅ Add first product to cart and stay on Products page
    public ProductsPage addFirstProductToCartAndContinue() {
        addProductByIndex(0, false);
        return this;
    }

    // ✅ Add first product to cart & go directly to CartPage
    public CartPage addFirstProductToCartAndGoToCart() {
        addProductByIndex(0, true);
        return new CartPage(driver);
    }

    // ✅ View Cart
    public CartPage viewCart() {
        WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        viewCartLink.click();
        return new CartPage(driver);
    }

    // 🔧 Core reusable method (handles hover + retry)
    private void addProductByIndex(int index, boolean goToCart) {
        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productWrappers));
        if (products.isEmpty()) throw new RuntimeException("❌ No products found!");

        WebElement product = products.get(index);

        // Hover to reveal overlay
        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();

        // Now wait until the overlay add-to-cart is visible
        WebElement addBtn = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".features_items .product-overlay .add-to-cart")));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
        } catch (Exception e) {
            // JS fallback if intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        }

        if (goToCart) {
            WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
            viewCartLink.click();
        } else {
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
            continueBtn.click();
        }
    }

}
