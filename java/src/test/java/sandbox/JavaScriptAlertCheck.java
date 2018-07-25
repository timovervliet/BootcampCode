package sandbox;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by MiesMSI on 6-7-2017.
 */
public class JavaScriptAlertCheck {

    @Test
    public void logInSuccesFull(){
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();

        // Open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");

        // Click on the login link
        driver.findElement(By.className("login")).click();

        // Enter username
        driver.findElement(By.id("email")).sendKeys("tester@test.com");

        // Enter password
        driver.findElement(By.id("passwd")).sendKeys("1qazxsw2");

        // Click the Log in button
        driver.findElement(By.id("SubmitLogin")).click();

        //Click the
        driver.findElement(By.cssSelector("i.icon-heart")).click();

        driver.findElement(By.cssSelector("i.icon-remove")).click();

        try{
            //Wait 10 seconds till alert is present
            WebDriverWait wait = new WebDriverWait(driver, 10);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            //Accepting alert.
            alert.accept();
            System.out.println("Accepted the alert successfully.");
        }
        catch(Throwable e){
            System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
        }
    }
}
