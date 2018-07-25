using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

namespace BootCampCode.testcases.chapterSix
{
    [TestClass]
    public class CreateAccountTest
    {
        [TestMethod]
        public void CreateAccount()
        {
            IWebDriver driver = new ChromeDriver();

            WebDriverWait myWaitVar = new WebDriverWait(driver, TimeSpan.FromSeconds(20));

            // Open the website
            driver.Navigate().GoToUrl("https://techblog.polteq.com/testshop/index.php");

            driver.Manage().Window.Maximize();

            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));

            driver.FindElement(By.ClassName("login")).Click();

            Assert.AreEqual("Authentication - TestShop", driver.Title);

            IWebElement createEmail = wait.Until(ExpectedConditions.ElementToBeClickable(By.Id("email_create")));
            createEmail.Click();

            Random random = new Random();

            int emailAddition = random.Next(1, 10000);

            string emailBase = "bootcampmania";
            string emailProvider = "@test.com";
            createEmail.SendKeys(emailBase + emailAddition + emailProvider);

            IWebElement createAccount = driver.FindElement(By.Id("SubmitCreate"));
            createAccount.Click();

            IWebElement firstGender = wait.Until(ExpectedConditions.ElementToBeClickable(By.Id("uniform-id_gender2")));
            firstGender.Click();

            IWebElement fName = driver.FindElement(By.Id("customer_firstname"));
            fName.SendKeys("T");

            IWebElement lName = driver.FindElement(By.Id("customer_lastname"));
            lName.SendKeys("Test");

            IWebElement password = driver.FindElement(By.Id("passwd"));
            password.SendKeys("1qazxsw2");

            var daySelection = driver.FindElement(By.Id("days"));
            var selectDay = new SelectElement(element: daySelection);
            selectDay.SelectByValue("1");

            var monthSelection = driver.FindElement(By.Id("months"));
            var selectMonth = new SelectElement(element: monthSelection);
            selectMonth.SelectByValue("1");

            var yearSelection = driver.FindElement(By.Id("years"));
            var selectYear = new SelectElement(element: yearSelection);
            selectYear.SelectByValue("1982");

            IWebElement submitButton = driver.FindElement(By.Id("submitAccount"));
            Console.WriteLine(submitButton.Text);
            submitButton.Click();
            
            // Verify if the MY ACCOUNT text is show
            String validationString = driver.FindElement(By.CssSelector("h1.page-heading")).Text;
            Assert.AreEqual("MY ACCOUNT", validationString, "My account element was not found");

            driver.Quit();
        }
    }
}
