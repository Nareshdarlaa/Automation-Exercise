package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactUs;
import pages.HomePage;
import utils.TestListener;

@Listeners(TestListener.class)
public class ContactUsForm extends BasicTest {

    @Test(description = "Test Case 6: Contact Us Form Submission")
    public void contactUsFormTest() {

        // 1 Launch browser & 2 Navigate to URL
        getDriver().get("http://automationexercise.com");

        // 3 Verify home page is visible
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible.");

        // 4 Click 'Contact Us'
        ContactUs contactUs = homePage.clickContactUsButton();

        // 5 Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(contactUs.getGetInTouch().isDisplayed(), "'GET IN TOUCH' is not visible.");

        // 6 Fill contact form and upload file
        contactUs.fillForm();

        // 7 Click 'Submit'
        contactUs.submitButtonClick();

        // 8 Accept alert if it appears
        contactUs.okButtonClick();

        // 9 Verify success message
        String expectedMessage = "Success! Your details have been submitted successfully.";
        String actualMessage = contactUs.getAlertSuccess().getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Success message is incorrect.");

        // Click Home button and verify landing on home page
        HomePage newHomePage = contactUs.homePageButtonClick();
        Assert.assertTrue(newHomePage.isHomePageVisible(), "Home page is not visible after clicking Home button.");
    }
}
