package examples;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ExampleFirstSeleniumTest {

    @Test
    public void logInSuccesFull(){
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://techblog.polteq.com/testshop/index.php");

        driver.findElement(By.className("login")).click();

        driver.findElement(By.id("email")).sendKeys("bootcamper@polteq.com");
        driver.findElement(By.id("SubmitLogin")).click();

        driver.quit();
    }
}
