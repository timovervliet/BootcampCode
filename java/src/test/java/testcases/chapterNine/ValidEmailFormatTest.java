package testcases.chapterNine;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import testcases.TestShopScenarioBrowFact;

public class ValidEmailFormatTest extends TestShopScenarioBrowFact {

    @Test
    public void validEmail(){

        HomePage homePage = new HomePage(driver);
        homePage.clickContactUS();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillInEmailAndClick("nope");

        //class group for the email changes regarding input
        Assertions.assertThat(driver.findElement(By.cssSelector(".form-error")).isEnabled())
                .as("Check if the email format is incorrect")
                .isTrue();


        //Back to the page <- Maybe create generalPage???
        homePage.clickContactUS();

        contactUsPage.fillInEmailAndClick("now@correct.com");

        Assertions.assertThat(driver.findElement(By.cssSelector(".form-ok")).isEnabled())
                .as("Check if the email format is incorrect")
                .isTrue();
    }
}
