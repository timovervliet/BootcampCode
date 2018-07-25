using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

namespace BootCampCode.testcases.chapterSix
{
    [TestClass]
    public class EmptyCartTest
    {
        [TestMethod]
        public void EmptyCart()
        {
            IWebDriver driver = new ChromeDriver();

            WebDriverWait myWaitVar = new WebDriverWait(driver, TimeSpan.FromSeconds(20));

            // Open the website
            driver.Navigate().GoToUrl("https://techblog.polteq.com/testshop/index.php");

            driver.Manage().Window.Maximize();

            //Check if empty element is visible if so add product to cart
            if (driver.FindElement(By.ClassName("ajax_cart_no_product")).Displayed)
            {
                //Click on the iPod Tag
                driver.FindElement(By.CssSelector("a[title='More about ipod']")).Click();
                //Click on the iPod shuffle
                driver.FindElement(By.XPath("//a[contains(text(),'iPod shuffle')]")).Click();
                //Add to cart
                driver.FindElement(By.Id("add_to_cart")).Click();

                //Continue shopping
                myWaitVar.Until(ExpectedConditions.ElementToBeClickable(By.CssSelector("span[title='Continue shopping']")))
                        .Click();

                //Check if cart number is 1
                Assert.AreEqual("1", driver.FindElement(By.ClassName("ajax_cart_quantity")).Text);
            }

            //Click the cart button
            //Overlay is blocking the cart button
            //HARD CODED SLEEP --> C# doesn't support visibilityOfElementLocated (yet!)
            System.Threading.Thread.Sleep(1000);
            myWaitVar.Until(ExpectedConditions.ElementToBeClickable(By.CssSelector(".shopping_cart>a")))
                    .Click();

            //Click the trash icon
            driver.FindElement(By.ClassName("icon-trash")).Click();

            //After clickinh the trash icon the site needs time to delete the webtable and show
            //the empty cart message
            String emptyCartMessage = myWaitVar.Until(ExpectedConditions.ElementIsVisible(By.CssSelector(".alert.alert-warning"))).Text;

            Assert.AreEqual("Your shopping cart is empty.", emptyCartMessage, "Validate if the empty cart message appears");

            //Check if empty element is visible
            Assert.IsTrue(driver.FindElement(By.ClassName("ajax_cart_no_product")).Displayed, "Check if empty element is visible");

            driver.Quit();
        }
    }
}
