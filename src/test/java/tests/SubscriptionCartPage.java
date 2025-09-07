package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

@Listeners(utils.TestListener.class)
public class SubscriptionCartPage extends BasicTest {

    @Test(description = "Test Case 11: Verify Subscription in Cart page")
    public void verifySubscriptionInCartPage() {
        WebDriver driver = getDriver();

        // 1️ Launch browser and directly open Cart page
        driver.get("https://automationexercise.com/view_cart");

        // 2️ Scroll to footer and verify 'SUBSCRIPTION' text
        HomePage homePage = new HomePage(driver);
        WebElement subscriptionElement = homePage.getSubscriptionElement();
        Assert.assertTrue(subscriptionElement.isDisplayed(), " 'SUBSCRIPTION' text is not visible!");

        // 3️ Enter email and click subscribe
        homePage.subscribe("testemail@example.com");

        // 4️ Verify success message
        WebElement successMessage = homePage.getAlertSuccessSubscribe();
        Assert.assertTrue(successMessage.isDisplayed(), " Success message not visible!");
        Assert.assertEquals(successMessage.getText().trim(),
                "You have been successfully subscribed!",
                " Success message text is incorrect!");
    }
}
