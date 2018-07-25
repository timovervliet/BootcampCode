package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPage {

    private final WebDriver driver;

    private By emailTextField = By.cssSelector("input#email");

    private By orderIdTextField = By.cssSelector("input#id_order");

    private By messageTextField = By.cssSelector("textarea#message");

    private By sendButton = By.cssSelector("button#submitMessage");

    private By invalidEmailElement = By.cssSelector(".alert.alert-danger>ol>li");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;

    }
    //TODO fix Select
    public void fillInContactForm(String subject, String email, String orderID, String message){
        Select subjectHeading = new Select(driver.findElement(By.cssSelector("select#id_contact")));
        subjectHeading.selectByVisibleText("Customer service");
        driver.findElement(emailTextField).sendKeys(email);
        driver.findElement(orderIdTextField).sendKeys(orderID);
        driver.findElement(messageTextField).sendKeys(message);
        driver.findElement(sendButton).click();
    }

    public String validationMessage(){
        WebElement alertMessageElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert.alert-success")));

        return alertMessageElement.getText();
    }

    public String getInvalidEmailMessage(){
        return driver.findElement(invalidEmailElement).getText();
    }

    public void fillInEmailAndClick(String email){
        driver.findElement(emailTextField).sendKeys(email);
        driver.findElement(orderIdTextField).click();
    }
}
