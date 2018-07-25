package browserDriven;


import browser.BrowserFactoryAdvanced;
import browser.BrowserFactoryMedior;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestShopScenarioBrowerDriven {

    protected WebDriver driver;
    //Step1: add parameter to the BeforeMethod
    //Step2: build the testng.xml
    //Step3: adjust the beforeMethod to take the param
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(BrowserFactoryAdvanced.Browser browser){
        //driver = BrowserFactoryBasic.getDriver("Chrome");
        //driver = BrowserFactoryMedior.getDriver(browser);
        driver = BrowserFactoryAdvanced.getDriver(browser);

        // Open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
