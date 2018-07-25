package testcases.homeWork;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import testcases.TestShopScenario;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DeleteWishListTest extends TestShopScenario {

    @Test
    public void deleteWishList() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();

        // Open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");

        // Click on the login link
        driver.findElement(By.className("login")).click();

        // Enter username
        driver.findElement(By.id("email")).sendKeys("bootcamper@feelthepain.com");

        // Enter password
        driver.findElement(By.id("passwd")).sendKeys("1qazxsw2");

        // Click the Log in button
        driver.findElement(By.id("SubmitLogin")).click();

        //Click the wishlistButton
        driver.findElement(By.cssSelector("i.icon-heart")).click();

        //Step 1: Locate the webtable.
        WebElement wishListTable = driver.findElement(By.cssSelector(".table.table-bordered"));

        //Step 2: Locate rows(horizontal) of the webtable within the webtable itself.
        List<WebElement> rowsInTable = wishListTable.findElements(By.tagName("tr"));

        //Step 2b: Calculate no of rows In the webtable.
        int rowsCount = rowsInTable.size();
        System.out.println(rowsInTable.size());

        //Step 4: Get the first column(vertical) from the first row which has all the header titles
        //        th -> table header
        List<WebElement> headerRowInTable = rowsInTable.get(0).findElements(By.tagName("th"));

        //Step 4b: Calculate no of columns present
        int columsCount = headerRowInTable.size();
        System.out.println(columsCount);

        //Step 5: Get the index for the Name column and Delete column. We achieve this by looping
        //        over the List from the headerRowInTable containing the columns
        int nameColumn = -1;
        int deleteColumn = -1;
        for (int i = 0; i < headerRowInTable.size(); i++) {
            System.out.println(headerRowInTable.get(i).getText());
            if (headerRowInTable.get(i).getText().equals("Name")) {
                nameColumn = i;
            } else if (headerRowInTable.get(i).getText().equals("Delete")) {
                deleteColumn = i;
            }
        }

        //Step 6: Assert that both Name and Delete have been found
        Assertions.assertThat(nameColumn > -1 && deleteColumn > -1)
                .as("Check if Name and Delete were found in the headerRow")
                .isTrue();

        System.out.println(nameColumn);
        System.out.println(deleteColumn);

        //Step 7: Find the correct row which contains the whislist we are looking for
        //        SKIP HEADER ROW
        String listToDelete = "Cardio Grind";

        //----------Method building
        Assertions.assertThat(checkWebTableForName(rowsInTable, nameColumn, listToDelete))
                .as("Check if name can be found in the webtable")
                .isTrue();
        //----------Mehtod building

        boolean wishListFound = false;
        for (int i = 1; i < rowsInTable.size(); i++) {
            //Step 7b: System.out.println("Row" + i + ":");
            //         Grab one of the rows in the webtable by using the index (i), the get all the columns
            //         and place them in a new List. Now wishListColumn represents all the columns in row(i)
            List<WebElement> wishListColumn = rowsInTable.get(i).findElements(By.tagName("td"));
            for(WebElement webElement : wishListColumn ){
                System.out.println(webElement.getText());
            }
            // Step 7c: Since we now which column number holds the names of the whishlist (step5 nameColumn) we can
            //          directly get that column number and check the contents of this cell
            if (wishListColumn.get(nameColumn).getText().equals(listToDelete)) {
                //System.out.println(wishListColumn.get(deleteColumn).getAttribute("class"));
                //Step 7d: We have the correct row which has the name of the wishlist as first column
                //         Now we can click the delete icon, again a column number we already now (step5 deleteColumn)
                wishListColumn.get(deleteColumn).findElement(By.cssSelector("a.icon")).click();
                wishListFound = true;
                //Step 7e: We break the loop otherwise java continue but the website is trying to process the delete
                break;
            }
        }
        //Step 7f: Assert we found the wishlist
        Assertions.assertThat(wishListFound).as("Could not find the given wishList").isTrue();

        //Step 8: Accept the Javascript alers
        try {
            //Wait 10 seconds till alert is present
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            //Accepting alert.
            alert.accept();
            System.out.println("Accepted the alert successfully.");

        } catch (Throwable e) {
            System.err.println("Error came while waiting for the alert popup. " + e.getMessage());
        }


        //Step 9: Refresh the WebTable, element has become stale meaning the webtable in memory of the code
        //        is not the same anymore as the webtable on the site (- 1 wishlist)
        //        We wait for the "Feel the pain" text to be gone by inverting the TextToPresentInElement

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(wishListTable, listToDelete)));
        wishListTable = driver.findElement(By.cssSelector(".table.table-bordered"));
        rowsInTable = wishListTable.findElements(By.tagName("tr"));

        //Step 10: Check if the deletion was succesfull. We can reuse code by looking for the name and place it in a method
        //         called checkWebTableForName
        Assertions.assertThat(checkWebTableForName(rowsInTable, nameColumn, listToDelete))
                .as("Check if delete was succesfull")
                .isFalse();

        //Step 11: Add the wishlist back so the can is 100% re-runable with out any code or function adjustments
        driver.findElement(By.cssSelector("input#name")).sendKeys(listToDelete);
        driver.findElement(By.cssSelector("button#submitWishlist")).click();

        //Step 12: Refresh WebTable, element has become stale. A wishList has been added. We wait for the
        //         Webtable to reload after adding the list back and set the variable wishListTable again
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".table.table-bordered")));
        wishListTable = driver.findElement(By.cssSelector(".table.table-bordered"));
        rowsInTable = wishListTable.findElements(By.tagName("tr"));

        //Step 13: Assert if the newly added wishlist can be found
        //----------Method building
        Assertions.assertThat(checkWebTableForName(rowsInTable, nameColumn, listToDelete))
                .as("Check if adding the list back was succesfull")
                .isTrue();
        //----------Method building

        driver.quit();
    }


    public boolean checkWebTableForName(List<WebElement> rowsInWebTable, int columnToCheck, String nameToFind){
        for(int i = 1; i < rowsInWebTable.size(); i++){
            List<WebElement> wishListColumn = rowsInWebTable.get(i).findElements(By.tagName("td"));

            if (wishListColumn.get(columnToCheck).getText().equals(nameToFind)){
                return true;
            }
        }
        return false;
    }
}
