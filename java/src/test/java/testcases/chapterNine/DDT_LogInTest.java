package testcases.chapterNine;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyAccountPage;
import testcases.TestShopScenarioBrowFact;

public class DDT_LogInTest extends TestShopScenarioBrowFact {

    @Parameters({"email", "password"})
    @Test
    public void logIn(String email, String password){
        HomePage homePage = new HomePage(driver);
        homePage.clickLogIn();

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.login(email, password);

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        Assertions.assertThat(myAccountPage.getWelcomeMessage())
                .as("Check if My Account header is shown")
                .contains("Welcome to your account.");
    }
}
