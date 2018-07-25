using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

namespace BootCampCode.testcases.chapterSix
{
    [TestClass]
    public class ShortCutAdjustmentTest
    {
        [TestMethod]
        public void ShortCutAdjustment()
        {
            IWebDriver driver = new ChromeDriver();

            WebDriverWait myWaitVar = new WebDriverWait(driver, TimeSpan.FromSeconds(20));

            // Open the website
            driver.Navigate().GoToUrl("https://techblog.polteq.com/testshop/index.php");

            driver.Manage().Window.Maximize();

            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
            IWebElement signInButton = driver.FindElement(By.ClassName("login"));
            if (signInButton.Displayed)
            {
                signInButton.Click();
                IWebElement email = driver.FindElement(By.Id("email"));
                IWebElement password = driver.FindElement(By.Id("passwd"));
                email.Clear();
                email.SendKeys("bootcamper@feelthepain.com");
                password.Clear();
                password.SendKeys("1qazxsw2");

                IWebElement submitSignIn = driver.FindElement(By.Id("SubmitLogin"));
                submitSignIn.Click();
            }

            //Go to the homepage
            driver.FindElement(By.CssSelector("img.logo")).Click();

            //personal info page can be found below
            driver.FindElement(By.CssSelector("a[title='Manage my personal information']")).Click();

            IWebElement fNameField = driver.FindElement(By.Id("firstname"));
            String fName = fNameField.GetAttribute("value");
            Console.WriteLine(fName);
            String newName = "General";
            if (fName == "Seargeant")
            {
                fNameField.Click();
                fNameField.Clear();
                fNameField.SendKeys(newName);

            }
            else if (fName == newName || fName != "Seargeant")
            {
                fNameField.Click();
                fNameField.Clear();
                fNameField.SendKeys("Seargeant");
            }

            IWebElement currentPwField = driver.FindElement(By.Id("old_passwd"));
            String currentPw = "1qazxsw2";
            currentPwField.Click();
            currentPwField.SendKeys(currentPw);

            IWebElement saveButton = driver.FindElement(By.XPath("//button//span[contains(text(), 'Save')]"));
            saveButton.Click();

            IWebElement saveIsSuccesful = wait.Until(ExpectedConditions.ElementIsVisible(By.CssSelector(".alert.alert-success")));
            Assert.IsTrue(saveIsSuccesful.Displayed);
            Console.WriteLine(saveIsSuccesful.Text);

            driver.Quit();
        }
    }
}
