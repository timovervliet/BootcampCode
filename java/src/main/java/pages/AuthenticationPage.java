package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage {
    private final WebDriver driver;

    private By emailTextfield = By.cssSelector("input#email");

    private By passwordTextfield = By.cssSelector("input#passwd");

    private By loginButton = By.cssSelector("button#SubmitLogin");

    private By invalidEmail = By.cssSelector(".form-group.form-error #email");

    public AuthenticationPage(WebDriver driver) {
      this.driver = driver;
    }

    public void login(String email, String password) {
      driver.findElement(emailTextfield).sendKeys(email);
      driver.findElement(passwordTextfield).sendKeys(password);
      driver.findElement(loginButton).click();
    }
}

