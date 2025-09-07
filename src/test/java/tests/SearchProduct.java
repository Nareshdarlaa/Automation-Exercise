package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProductsPage;
import utils.TestListener;

import java.time.Duration;
import java.util.List;

@Listeners(TestListener.class) // attach your listener
public class SearchProduct extends BasicTest {

    private static final String searchKeyword = "Dress"; // hardcoded for now

    @Test(description = "Test Case 9: Search Product")
    public void searchProduct() {

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1️⃣ Navigate directly to Products page
        driver.get("https://automationexercise.com/products");

        ProductsPage productsPage = new ProductsPage(driver);

        // 2️⃣ Verify 'ALL PRODUCTS' heading is visible
        WebElement allProductsHeading = wait.until(
                ExpectedConditions.visibilityOf(productsPage.getTitleTextCenter())
        );
        Assert.assertEquals(allProductsHeading.getText(), "ALL PRODUCTS",
                "❌ User is not on ALL PRODUCTS page!");

        // 3️⃣ Enter product name in search input and click search button
        productsPage.fillSearchProductInput(searchKeyword);

        // 4️⃣ Verify 'SEARCHED PRODUCTS' heading
        WebElement searchedHeading = wait.until(
                ExpectedConditions.visibilityOf(productsPage.getTitleTextCenter())
        );
        Assert.assertEquals(searchedHeading.getText(), "SEARCHED PRODUCTS",
                "❌ 'SEARCHED PRODUCTS' is not visible!");

        // 5️⃣ Verify at least one product matches search
        List<String> productNames = productsPage.getProductsSearchNames();
        Assert.assertTrue(productNames.size() > 0, "❌ No products found in search!");

        boolean foundMatch = productNames.stream()
                .anyMatch(p -> p.toLowerCase().contains(searchKeyword.toLowerCase()));

        Assert.assertTrue(foundMatch, "❌ No product matched the search keyword: " + searchKeyword);

        // Log results
        for (String product : productNames) {
            System.out.println("🔎 Found product: " + product);
        }
    }
}
