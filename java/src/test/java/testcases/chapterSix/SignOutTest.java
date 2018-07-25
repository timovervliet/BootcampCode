package testcases.chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import testcases.TestShopScenario;

public class SignOutTest extends TestShopScenario {

    @Test
    public void signOut(){
        driver.manage().window().maximize();

        // Click on the login link
        driver.findElement(By.className("login")).click();

        // Enter username
        driver.findElement(By.id("email")).sendKeys("bootcamper@feelthepain.com");

        // Enter password
        driver.findElement(By.id("passwd")).sendKeys("1qazxsw2");

        // Click the Log in button
        driver.findElement(By.id("SubmitLogin")).click();

        // Verify if the logout link is displayed
        Assertions.assertThat(driver.findElement(By.cssSelector("a.logout"))
                .isDisplayed())
                .as("Logout link should be displayed").isTrue();


        // Verify if the MY ACCOUNT text is show
        String validationString = driver.findElement(By.cssSelector("h1.page-heading")).getText();
        Assertions.assertThat(validationString)
                .as("My account element was not found")
                .isEqualTo("MY ACCOUNT");

        //Click the logout link
        driver.findElement(By.className("logout")).click();

        Assertions.assertThat(driver.findElement(By.cssSelector(".login")).getText())
                .as("Check if the Sign in element is present")
                .isEqualTo("Sign in");

        //additional check
        String className = driver.findElement(By.cssSelector(".header_user_info:nth-child(2)>a"))
                .getAttribute("class");

        Assertions.assertThat(className)
                .as("Check if the Sign in element is present")
                .isEqualTo("login");
    }
}
