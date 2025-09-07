package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ProductDetailPage;

import java.time.Duration;

@Listeners(utils.TestListener.class)
public class ProductsPageTest extends BasicTest {

    @Test(description = "Test Case 8: Verify All Products and Product Detail Page")
    public void verifyAllProductsAndProductDetailPage() {

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1️⃣ Launch browser and navigate directly to Products page
        driver.get("https://automationexercise.com/products");

        // 2️⃣ Verify 'ALL PRODUCTS' page
        ProductsPage productsPage = new ProductsPage(driver);
        WebElement allProductsHeading = wait.until(ExpectedConditions.visibilityOf(productsPage.getTitleTextCenter()));
        Assert.assertEquals(allProductsHeading.getText().trim(), "ALL PRODUCTS", "User is not on ALL PRODUCTS page!");

        // 3️⃣ Click 'View Product' of first product
        ProductDetailPage productDetailPage = productsPage.viewProductOfFirstProductButtonClick();

        // 4️⃣ Verify product details are visible
        Assert.assertTrue(productDetailPage.getProductName().isDisplayed(), "Product name not visible!");
        Assert.assertTrue(productDetailPage.getProductCategory().isDisplayed(), "Product category not visible!");
        Assert.assertTrue(productDetailPage.getProductPrice().isDisplayed(), "Product price not visible!");
        Assert.assertTrue(productDetailPage.getProductAvailability().isDisplayed(), "Product availability not visible!");
        Assert.assertTrue(productDetailPage.getProductCondition().isDisplayed(), "Product condition not visible!");
        Assert.assertTrue(productDetailPage.getProductBrand().isDisplayed(), "Product brand not visible!");
    }
}
