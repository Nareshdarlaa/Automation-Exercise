package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private WebDriver driver;

    private By productName = By.xpath("//td[contains(@class, 'cart_description')]//a");
    private By price = By.xpath("//td[contains(@class, 'cart_price')]/p");
    private By quantity = By.xpath("//td[contains(@class, 'cart_quantity')]/button");
    private By totalPrice = By.xpath("//p[contains(@class, 'cart_total_price')]");
    private By shoppingCart = By.cssSelector("li[class='active']");
    private By proceedToCheckoutButton = By.cssSelector("a.btn.btn-default.check_out");
    private By registerLoginButton = By.cssSelector("a[href='/login'] u");
    private By xButtons = By.cssSelector("a.cart_quantity_delete");
    private By emptyCartSpan = By.id("empty_cart");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getProductsNames() {
        List<WebElement> elements = driver.findElements(productName);
        List<String> names = new ArrayList<>();
        for (WebElement e : elements) {
            names.add(e.getText());
        }
        return names;
    }

    public List<String> getPrices() {
        List<WebElement> elements = driver.findElements(price);
        List<String> prices = new ArrayList<>();
        for (WebElement e : elements) {
            prices.add(e.getText());
        }
        return prices;
    }

    public List<String> getQuantities() {
        List<WebElement> elements = driver.findElements(quantity);
        List<String> qtys = new ArrayList<>();
        for (WebElement e : elements) {
            qtys.add(e.getText());
        }
        return qtys;
    }

    public List<String> getTotalPrices() {
        List<WebElement> elements = driver.findElements(totalPrice);
        List<String> totals = new ArrayList<>();
        for (WebElement e : elements) {
            totals.add(e.getText());
        }
        return totals;
    }

    public String getShoppingCartHeading() {
        return driver.findElement(shoppingCart).getText();
    }

    public void clickProceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }

    public void clickRegisterLogin() {
        driver.findElement(registerLoginButton).click();
    }

    public CheckoutPage proceedToCheckoutLoggedButtonClick() {
        driver.findElement(proceedToCheckoutButton).click();
        return new CheckoutPage(driver);
    }

    public void deleteAllProducts() throws InterruptedException {
        List<WebElement> buttons = driver.findElements(xButtons);
        while (!buttons.isEmpty()) {
            buttons.get(0).click();
            Thread.sleep(500);
            buttons = driver.findElements(xButtons);
        }
    }

    public String getEmptyCartMessage() {
        return driver.findElement(emptyCartSpan).getText();
    }

    // ------------------- NEW HELPER METHOD -------------------
    /**
     * Checks if the given product is present in the cart
     * @param name Product name to check
     * @return true if the product exists in the cart, false otherwise
     */
    public boolean isProductInCart(String name) {
        List<String> products = getProductsNames();
        for (String p : products) {
            if (p.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
