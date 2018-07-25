package testcases.chapterNine;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyAccountPage;
import testcases.TestShopScenarioBrowFact;

public class LogInTest extends TestShopScenarioBrowFact {

    @Test
    public void logIn(){
        HomePage homePage = new HomePage(driver);
        homePage.clickLogIn();

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.login("bootcamper@feelthepain.com", "1qazxsw2");

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        Assertions.assertThat(myAccountPage.getMyAccountHeader())
                .as("Check if My Account header is shown")
                .isEqualTo("MY ACCOUNT");
    }
}
