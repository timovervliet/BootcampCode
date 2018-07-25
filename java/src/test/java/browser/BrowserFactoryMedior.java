package browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactoryMedior {
    public static WebDriver getDriver(String browser){
        switch(browser.toLowerCase()) {
            case "firefox":
                return createFireFoxBrowser();
            case "ie":
                return createIEBrowser();
            case "chrome":
                return createChromeBrowser();
            default:
                return createChromeBrowser();
        } // end switch
    }

    private static WebDriver createChromeBrowser() {
        //Chrome options are chrome specific
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("ignore-certificate-errors");
        options.addArguments("disable-infobars");
        //options.addArguments("headless");
        //Capabilities can used for WebDriver capabilities ie: proxy
        options.setCapability("chrome.switches", "--verbose");
        ChromeDriverManager.getInstance().setup();
        return new ChromeDriver(options);
    }

    private static WebDriver createFireFoxBrowser() {
        FirefoxOptions options = new FirefoxOptions();
        //options.setHeadless(true);
        options.setCapability("javascriptEnabled", true);
        FirefoxDriverManager.getInstance().setup();
        return new FirefoxDriver(options);
    }

    private static WebDriver createIEBrowser() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.withInitialBrowserUrl("http://www.nu.nl");
        InternetExplorerDriverManager.getInstance().setup();
        return new InternetExplorerDriver(options);
    }
}
