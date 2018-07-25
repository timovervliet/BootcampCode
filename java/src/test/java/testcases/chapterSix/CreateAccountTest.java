package testcases.chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import testcases.TestShopScenario;
import org.openqa.selenium.support.ui.Select;

import java.util.UUID;

public class CreateAccountTest extends TestShopScenario {

    @Test
    public void createAccount(){
        String email = UUID.randomUUID() + "@foobar.com";
        email = email.replace("-", "");
        WebDriverWait wait = new WebDriverWait(driver,20);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("a.login")).click();

        driver.findElement(By.id("email_create")).sendKeys(email);
        driver.findElement(By.id("SubmitCreate")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender2"))).click();
        //driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("Tester");
        driver.findElement(By.id("customer_lastname")).sendKeys("QA");
        driver.findElement(By.id("passwd")).sendKeys("1qazxsw2");

        new Select(driver.findElement(By.id("days"))).selectByValue("25");
        new Select(driver.findElement(By.id("months"))).selectByValue("10");
        new Select(driver.findElement(By.id("years"))).selectByValue("1971");

        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();
        driver.findElement(By.id("submitAccount")).click();

        Assertions.assertThat(driver.findElement(By.cssSelector("a.account > span")).getText())
                .as("Username in header").isEqualTo("Tester QA");
        Assertions.assertThat(driver.findElement(By.cssSelector("p.alert.alert-success")).getText())
                .as("Message that I have created an account").isEqualTo("Your account has been created.");
        Assertions.assertThat(driver.findElement(By.tagName("h1")).getText())
                .as("Page Title of My Account Page").isEqualTo("MY ACCOUNT");
        Assertions.assertThat(driver.getTitle())
                .as("Browser title").contains("My account");
    }
}
