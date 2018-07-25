package testcases;


import browser.BrowserFactoryAdvanced;
import browser.BrowserFactoryBasic;
import browser.BrowserFactoryMedior;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestShopScenarioBrowFact {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        //driver = BrowserFactoryBasic.getDriver("Chrome");
        //driver = BrowserFactoryMedior.getDriver("Chrome");
        driver = BrowserFactoryAdvanced.getDriver(BrowserFactoryAdvanced.Browser.CHROME);

        // Open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
