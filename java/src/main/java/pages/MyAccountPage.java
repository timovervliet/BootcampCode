package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class MyAccountPage {

    private final WebDriver driver;

    private By welcomeMessageElement = By.cssSelector(".info-account");
    private By myAccountElement = By.cssSelector(".page-heading");


    public MyAccountPage(WebDriver driver){
        this.driver = driver;
    }


    public String getMyAccountHeader(){
        return driver.findElement(myAccountElement).getText();
    }

    public String getWelcomeMessage(){
        return driver.findElement(welcomeMessageElement).getText();
    }
}
