using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace BootCampCode.testcases.chapterSix
{
    [TestClass]
    public class SignOutTest
    {
        IWebDriver driver;

        [TestMethod]
        public void SignOut()
        {
            driver = new ChromeDriver();

            driver.Navigate().GoToUrl("https://techblog.polteq.com/testshop/index.php");

            driver.Manage().Window.Maximize();

            // Click on the login link
            driver.FindElement(By.ClassName("login")).Click();

            // Enter username
            driver.FindElement(By.Id("email")).SendKeys("bootcamper@feelthepain.com");

            // Enter password
            driver.FindElement(By.Id("passwd")).SendKeys("1qazxsw2");

            // Click the Log in button
            driver.FindElement(By.Id("SubmitLogin")).Click();

            // Verify if the logout link is displayed
            Assert.IsTrue(driver.FindElement(By.CssSelector("a.logout")).Displayed, "Logout link should be displayed");


            // Verify if the MY ACCOUNT text is show
            String validationString = driver.FindElement(By.CssSelector("h1.page-heading")).Text;
            Assert.AreEqual("MY ACCOUNT", validationString, "My account element was not found");

            //Click the logout link
            driver.FindElement(By.ClassName("logout")).Click();

            Assert.AreEqual("Sign in", driver.FindElement(By.CssSelector(".login")).Text, "Check if the Sign in element is present");

            //additional check
            String className = driver.FindElement(By.CssSelector(".header_user_info:nth-child(2)>a")).GetAttribute("class");
            Assert.AreEqual("login", className, "Check if the Sign in element is present");

            driver.Quit();
        }
    }
}
