package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailPage;
import pages.ProductsPage;
import utils.TestListener;

import java.util.List;

// Attach the listener
@Listeners({TestListener.class})
public class AddProductCart extends BasicTest {


    @Test(priority = 1)
    public void verifyViewProduct() {
        driver.get("https://automationexercise.com/products");

        ProductsPage productPage = new ProductsPage(driver);

        // Search for a product
        productPage.fillSearchProductInput("GRAPHIC DESIGN MEN T SHIRT - BLUE");

        // View first product in search results
        ProductDetailPage detailPage = productPage.viewProductOfFirstProductButtonClick();

        // Verify product detail page is displayed
        Assert.assertTrue(detailPage.isProductNameDisplayed(),
                "Product detail page did not open!");
    }
}
