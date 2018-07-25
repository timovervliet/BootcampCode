package browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactoryBasic {
    public static WebDriver getDriver(String browser){
        WebDriver driver;

        switch(browser.toLowerCase()) {
            case "firefox":
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver();
                break;
            case "ie":
                InternetExplorerDriverManager.getInstance().setup();
                driver = new InternetExplorerDriver();
                break;
            case "chrome":
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                EdgeDriverManager.getInstance().setup();
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
        } // end switch
        return driver;
    }
}
