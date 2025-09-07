package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterUser {
    WebDriver driver;

    // ------------ Locators ------------
    By signupLoginButton = By.cssSelector("a[href='/login']");
    By newUserSignupText = By.xpath("//h2[text()='New User Signup!']");
    By nameInput = By.name("name");
    By signupEmailInput = By.xpath("//input[@data-qa='signup-email']");
    By signupButton = By.xpath("//button[text()='Signup']");
    By enterAccountInfoText = By.xpath("//b[text()='Enter Account Information']");
    By genderMrs = By.id("id_gender2");
    By passwordInput = By.id("password");
    By dayDropdown = By.id("days");
    By monthDropdown = By.id("months");
    By yearDropdown = By.id("years");
    By newsletterCheckbox = By.id("newsletter");
    By offersCheckbox = By.id("optin");
    By firstNameInput = By.id("first_name");
    By lastNameInput = By.id("last_name");
    By companyInput = By.id("company");
    By address1Input = By.id("address1");
    By address2Input = By.id("address2");
    By countryDropdown = By.id("country");
    By stateInput = By.id("state");
    By cityInput = By.id("city");
    By zipcodeInput = By.id("zipcode");
    By mobileInput = By.id("mobile_number");
    By createAccountButton = By.xpath("//button[text()='Create Account']");
    By accountCreatedText = By.xpath("//b[text()='Account Created!']");
    By continueButton = By.xpath("//a[text()='Continue']");
    By deleteAccountButton = By.xpath("//a[text()=' Delete Account']");
    By accountDeletedText = By.xpath("//b[text()='Account Deleted!']");

    // ------------ Constructor ------------
    public RegisterUser(WebDriver driver) {
        this.driver = driver;
    }

    // ------------ Actions ------------
    public void clickSignupLogin() {
        driver.findElement(signupLoginButton).click();
    }

    public boolean isNewUserSignupVisible() {
        return driver.findElement(newUserSignupText).isDisplayed();
    }

    public void enterNameAndEmail(String name, String email) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(signupEmailInput).sendKeys(email);
    }

    public void clickSignupButton() {
        driver.findElement(signupButton).click();
    }

    public boolean isEnterAccountInfoVisible() {
        return driver.findElement(enterAccountInfoText).isDisplayed();
    }

    public void fillAccountDetails(String password) {
        driver.findElement(genderMrs).click();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void fillAddressDetails() {
        driver.findElement(firstNameInput).sendKeys("John");
        driver.findElement(lastNameInput).sendKeys("Cena");
        driver.findElement(companyInput).sendKeys("FleekSolutions");
        driver.findElement(address1Input).sendKeys("Madhapur");
        driver.findElement(address2Input).sendKeys("Hyderabad");
        driver.findElement(stateInput).sendKeys("Telangana");
        driver.findElement(cityInput).sendKeys("Hyderabad");
        driver.findElement(zipcodeInput).sendKeys("500081");
        driver.findElement(mobileInput).sendKeys("9876543210");
    }

    public void subscribeToNewsletter() {
        driver.findElement(newsletterCheckbox).click();
        driver.findElement(offersCheckbox).click();
    }

    public void clickCreateAccount() {
        driver.findElement(createAccountButton).click();
    }

    public boolean isAccountCreatedVisible() {
        return driver.findElement(accountCreatedText).isDisplayed();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void deleteAccount() {
        driver.findElement(deleteAccountButton).click();
    }

    public boolean isAccountDeletedVisible() {
        return driver.findElement(accountDeletedText).isDisplayed();
    }
 // --- Getters for dropdowns ---
    public WebElement getDayDropdown() {
        return driver.findElement(dayDropdown);
    }

    public WebElement getMonthDropdown() {
        return driver.findElement(monthDropdown);
    }

    public WebElement getYearDropdown() {
        return driver.findElement(yearDropdown);
    }

    public WebElement getCountryDropdown() {
        return driver.findElement(countryDropdown);
    }

}