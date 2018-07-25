package testcases.chapterNine;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import org.assertj.core.api.Assertions;
import pages.HomePage;
import testcases.TestShopScenario;

public class FillinContactFormTest extends TestShopScenario {

    @Test
    public void fillInForm(){
        // Open the contact page
        //driver.findElement(By.cssSelector("li#header_link_contact > a"))
        //        .click();

        HomePage homePage = new HomePage(driver);
        homePage.clickContactUS();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillInContactForm("Customer service", "test@tester.com" , "15", "Duurt lang!");

        Assertions.assertThat(contactUsPage.validationMessage()).as("Validate succesfull contactform submit")
                .isEqualToIgnoringCase("your message has been successfully sent to our team.");

    }
}
