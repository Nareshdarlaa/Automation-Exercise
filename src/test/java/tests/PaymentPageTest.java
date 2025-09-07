package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.PaymentPage;
import utils.TestListener;

import java.time.Duration;

@Listeners(TestListener.class)
public class PaymentPageTest extends BasicTest {

    @Test
    public void verifyPaymentAndConfirmOrder() {
        driver.get("https://automationexercise.com/payment");

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.enterPaymentDetails("Naresh", "4111111111111111", "313", "10", "2034"); //  use valid Visa test card
        paymentPage.confirmOrder();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //  Instead of waiting for .alert-success.alert, just grab body text
        WebElement body = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        String pageText = body.getText();
        System.out.println("Page text: " + pageText);

        Assert.assertTrue(pageText.contains("Congratulations! Your order has been confirmed!"),
                " Order confirmation message not found!");
        System.out.println(" verifyPaymentAndConfirmOrder passed successfully.");
    }
}