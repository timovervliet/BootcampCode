package testcases.chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import testcases.TestShopScenario;

import java.util.List;

public class ValidateSupplierProductTest extends TestShopScenario {

    @Test
    public void validateSupplierProduct(){

        driver.manage().window().maximize();

        Select dropdown = new Select(driver.findElement(By.cssSelector("select[name='supplier_list']")));
        dropdown.selectByVisibleText("AppleStore");

        List<WebElement> supplierProducts = driver.findElements(By.cssSelector("h5[itemprop='name']>a"));

        //option - bit messy but works
        boolean macBookFound = false;
        for(int i = 0; i < supplierProducts.size(); i++){
            if (supplierProducts.get(i).getText().equals("MacBook Air")){
                macBookFound = true;
                break;
            }
        }

        Assertions.assertThat(macBookFound)
                .as("Check if MacBook Air is in the list")
                .isTrue();

        driver.quit();
    }
}
