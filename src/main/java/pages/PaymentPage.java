package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
    WebDriver driver;

    // Locators
    By nameOnCard = By.name("name_on_card");
    By cardNumber = By.name("card_number");
    By cvc = By.name("cvc");
    By expiryMonth = By.name("expiry_month");
    By expiryYear = By.name("expiry_year");
    By payAndConfirmOrderButton = By.id("submit");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    // Normal way to enter details
    public void enterPaymentDetails(String name, String number, String cvcCode, String month, String year) {
        driver.findElement(nameOnCard).sendKeys(name);
        driver.findElement(cardNumber).sendKeys(number);
        driver.findElement(cvc).sendKeys(cvcCode);
        driver.findElement(expiryMonth).sendKeys(month);
        driver.findElement(expiryYear).sendKeys(year);
    }

    // Combined method (enter details + click button)
    public PaymentPage fillPaymentDetailsDirect(String name, String number, String cvcCode, String month, String year) {
        enterPaymentDetails(name, number, cvcCode, month, year);
        confirmOrder();
        return this;
    }

    // Remove Google Ad iframes blocking the button
    private void removeAdsIfPresent() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelectorAll('iframe[id^=\"aswift_\"]').forEach(e => e.remove());");
            System.out.println(" Ads removed successfully.");
        } catch (Exception e) {
            System.out.println(" No ads found, continuing...");
        }
    }

    // Confirm Order with JS click (avoids iframe issue)
    public void confirmOrder() {
        removeAdsIfPresent();
        WebElement button = driver.findElement(payAndConfirmOrderButton);

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        // Click with JS (avoids overlay/iframe issue)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }
}
