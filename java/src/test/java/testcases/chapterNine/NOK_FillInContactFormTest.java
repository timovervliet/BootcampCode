package testcases.chapterNine;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import testcases.TestShopScenarioBrowFact;

public class NOK_FillInContactFormTest extends TestShopScenarioBrowFact {

    @Test
    public void nokFillInContactForm(){
        HomePage homePage = new HomePage(driver);
        homePage.clickContactUS();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillInContactForm("Customer Service", "nope", "1234321", "Help!");

        Assertions.assertThat(contactUsPage.getInvalidEmailMessage())
                .as("Check invalid email message")
                .isEqualTo("Invalid email address.");
    }
}
