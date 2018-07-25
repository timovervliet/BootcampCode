package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final WebDriver driver;

    private By contactButton = By.cssSelector("li#header_link_contact > a");
    private By logInButton = By.cssSelector(".login");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickContactUS(){
        driver.findElement(contactButton).click();
    }

    public void clickLogIn(){
        driver.findElement(logInButton).click();
    }
}
