package dataDriven;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import testcases.TestShopScenarioBrowFact;

public class DataDrivenTest extends TestShopScenarioBrowFact{

    @Parameters({"subject", "email", "orderID", "message"})
    @Test
    public void fillInForm(String subject, String email, String orderID, String message){
        HomePage homePage = new HomePage(driver);
        homePage.clickContactUS();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillInContactForm(subject, email , orderID, message);

        Assertions.assertThat(contactUsPage.validationMessage()).as("Validate succesfull contactform submit")
                .isEqualToIgnoringCase("your message has been successfully sent to our team.");
    }
}
