using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

namespace BootCampCode.testcases.chapterSix
{
    [TestClass]
    public class FillCartTest
    {

        [TestMethod]
        public void FillCart()
        {
            IWebDriver driver = new ChromeDriver();

            WebDriverWait myWaitVar = new WebDriverWait(driver, TimeSpan.FromSeconds(20));

            // Open the website
            driver.Navigate().GoToUrl("https://techblog.polteq.com/testshop/index.php");

            driver.Manage().Window.Maximize();

            //Check if empty element is visible
            Assert.IsTrue(driver.FindElement(By.ClassName("ajax_cart_no_product")).Displayed, "Check if empty element is visible");

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

            driver.Quit();
        }
    }
}
