package testcases.chapterSix;


import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import testcases.TestShopScenario;

import java.util.concurrent.TimeUnit;

public class FillCartTest extends TestShopScenario{

    @Test
    public void fillCart() throws InterruptedException {
        WebDriverWait myWaitVar = new WebDriverWait(driver,20);

        // Open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");
        driver.manage().window().maximize();

        //Check if empty element is visible
        Assertions.assertThat(driver.findElement(By.className("ajax_cart_no_product")).isDisplayed())
                .as("Check if empty element is visible")
                .isTrue();

        //Click on the iPod Tag
        driver.findElement(By.cssSelector("a[title='More about ipod']")).click();

        //Click on the iPod shuffle
        driver.findElement(By.xpath("//a[contains(text(),'iPod shuffle')]")).click();

        //Add to cart
        driver.findElement(By.id("add_to_cart")).click();

        //Continue shopping
        myWaitVar.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='Continue shopping']")))
                .click();

        //Check if cart number is 1
        Assertions.assertThat(driver.findElement(By.className("ajax_cart_quantity")).getText())
                .as("Check if number is 1")
                .isEqualTo("1");
    }
}
