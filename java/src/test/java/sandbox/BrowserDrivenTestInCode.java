package sandbox;

import browser.BrowserFactoryAdvanced;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import testcases.TestShopScenario;

public class BrowserDrivenTestInCode {

    //Step 1: create an dataprovider method
    @DataProvider(name = "BrowserChoice")
    public static Object[][]browser(){
        //the object has the enum from the browser factory
        return new Object[][]{{BrowserFactoryAdvanced.Browser.CHROME}};
    }

    //Step 2: alter/remove the TestShopScenario
    //Step 3: add the dataprovider
    //Step 4: add the parameter to the test
    //Step 5: replace the paramater in the method for the hard coded browser enum
    @Test(dataProvider = "BrowserChoice")
    public void fillInForm(BrowserFactoryAdvanced.Browser browser){
        WebDriver driver;
        // Open the contact page
        driver = BrowserFactoryAdvanced.getDriver(browser);

        // Open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");

        HomePage homePage = new HomePage(driver);
        homePage.clickContactUS();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillInContactForm("Customer service", "test@tester.com" , "15", "Duurt lang!");

        Assertions.assertThat(contactUsPage.validationMessage()).as("Validate succesfull contactform submit")
                .isEqualToIgnoringCase("your message has been successfully sent to our team.");

        driver.quit();
    }
}
