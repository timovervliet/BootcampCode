package testcases.chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import testcases.TestShopScenario;


public class AdjustPersonalInfoTest extends TestShopScenario {

    @Test
    public void adjustPersonalInfo() throws InterruptedException {

        driver.manage().window().maximize();

        if(driver.findElement(By.className("login")).isDisplayed()){
            // Click on the login link
            driver.findElement(By.className("login")).click();

            // Enter username
            driver.findElement(By.id("email")).sendKeys("bootcamper@feelthepain.com");

            // Enter password
            driver.findElement(By.id("passwd")).sendKeys("1qazxsw2");

            // Click the Log in button
            driver.findElement(By.id("SubmitLogin")).click();

            //Click the personal info icon
            driver.findElement(By.cssSelector(".icon-user")).click();

            //Retrieve the current mail adres
            String currentFirstName = driver.findElement(By.id("firstname")).getAttribute("value");
            System.out.println(currentFirstName);
            String changedName;
            if (currentFirstName.equals("Sergeant")){
                driver.findElement(By.id("firstname")).clear();
                driver.findElement(By.id("firstname")).sendKeys("General");
                changedName = "General";
            }
            else if (currentFirstName.equals("General")){
                driver.findElement(By.id("firstname")).clear();
                driver.findElement(By.id("firstname")).sendKeys("Sergeant");
                changedName = "Sergeant";
            }
            else{
                driver.findElement(By.id("firstname")).clear();
                driver.findElement(By.id("firstname")).sendKeys("Seargent");
                changedName = "Seargent";
            }

            driver.findElement(By.id("old_passwd")).sendKeys("1qazxsw2");
            driver.findElement(By.cssSelector("button[name='submitIdentity']")).click();

            //Check if the empty message appears
            Assertions.assertThat(driver.findElement(By.cssSelector(".alert.alert-success")).getText())
                    .as("Validate if succesfull message appears")
                    .isEqualTo("Your personal information has been successfully updated.");

            driver.findElement(By.cssSelector(".account>span")).click();

            //Click the personal info icon
            driver.findElement(By.cssSelector(".icon-user")).click();

            //Check the new name
            //By running the test twice the console outputs should switch!
            System.out.println(driver.findElement(By.id("firstname")).getAttribute("value"));
            Assertions.assertThat(driver.findElement(By.id("firstname")).getAttribute("value"))
                    .as("Validate name change")
                    .isEqualTo(changedName);
        }
        else
        {
            //Assertion or other failsafe. "If" can be removed but this way the bootcampers can try to build a fail
            //safe. Reasoning here is: test shouldn't run if the login is not shown
        }
    }
}
