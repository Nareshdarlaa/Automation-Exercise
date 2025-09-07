package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckoutPage {

    private WebDriver driver;

    // Locators
    private By addressDelivery = By.cssSelector("#address_delivery li");
    private By addressInvoice = By.cssSelector("#address_invoice li");
    private By commentBox = By.name("message");
    private By placeOrderButton = By.cssSelector("a.check_out");
    private By nameOnCard = By.name("name_on_card");
    private By cardNumber = By.name("card_number");
    private By cvc = By.name("cvc");
    private By expiryMonth = By.name("expiry_month");
    private By expiryYear = By.name("expiry_year");
    private By payAndConfirmButton = By.id("submit"); // The Pay button

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    //  Delivery address
    public List<WebElement> getAddressDelivery() {
        return driver.findElements(addressDelivery);
    }

    //  Invoice address
    public List<WebElement> getAddressInvoice() {
        return driver.findElements(addressInvoice);
    }

    //  Add comment
    public void enterComment(String comment) {
        WebElement commentElement = driver.findElement(commentBox);
        commentElement.clear();
        commentElement.sendKeys(comment);
    }

    //  Proceed → Fill payment details -> Go to PaymentPage
    public PaymentPage fillPaymentDetailsDirect(String cardName, String cardNum, String cvcCode,
                                                String expMonth, String expYear) {
        // Click Place Order
        driver.findElement(placeOrderButton).click();

        // Fill payment form
        driver.findElement(nameOnCard).sendKeys(cardName);
        driver.findElement(cardNumber).sendKeys(cardNum);
        driver.findElement(cvc).sendKeys(cvcCode);
        driver.findElement(expiryMonth).sendKeys(expMonth);
        driver.findElement(expiryYear).sendKeys(expYear);

        // Click Pay button
        driver.findElement(payAndConfirmButton).click();

        return new PaymentPage(driver);
    }

    //  Separate checkout button for logged in user
    public CheckoutPage proceedToCheckoutLoggedButtonClick() {
        driver.findElement(By.cssSelector("a.check_out")).click();
        return this;
    }
}
