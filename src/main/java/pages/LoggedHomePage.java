package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedHomePage {
    private WebDriver driver;

    private By loggedInAsText = By.xpath("//a[contains(text(),'Logged in as')]");
    private By logoutButton = By.cssSelector("a[href='/logout']");
    private By cartButton = By.cssSelector("a[href='/view_cart']");

    public LoggedHomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public boolean isLoggedIn() {
        return driver.findElement(loggedInAsText).isDisplayed();
    }

    // Return the logged in username text
    public String getUsernameText() {
        return driver.findElement(loggedInAsText).getText().trim();
    }

    // Click Cart button
    public CartPage clickCart() {
        driver.findElement(cartButton).click();
        return new CartPage(driver);
    }

    // Logout
    public LoginPage logoutButtonClick() {
        driver.findElement(logoutButton).click();
        return new LoginPage(driver);
    }
}

