package testcases;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestShopScenario  {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        // Open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
