using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

namespace BootCampCode.testcases.chapterSix
{
    [TestClass]
    public class AdjustPersonalInfoTest
    {
        [TestMethod]
        public void AdjustPersonalInfo ()
        {
            IWebDriver driver = new ChromeDriver();

            WebDriverWait myWaitVar = new WebDriverWait(driver, TimeSpan.FromSeconds(20));

            // Open the website
            driver.Navigate().GoToUrl("https://techblog.polteq.com/testshop/index.php");

            driver.Manage().Window.Maximize();

            if (driver.FindElement(By.ClassName("login")).Displayed)
            {
                // Click on the login link
                driver.FindElement(By.ClassName("login")).Click();

                // Enter username
                driver.FindElement(By.Id("email")).SendKeys("bootcamper@feelthepain.com");

                // Enter password
                driver.FindElement(By.Id("passwd")).SendKeys("1qazxsw2");

                // Click the Log in button
                driver.FindElement(By.Id("SubmitLogin")).Click();

                //Click the personal info icon
                driver.FindElement(By.CssSelector(".icon-user")).Click();

                //Retrieve the current mail adres
                String currentFirstName = driver.FindElement(By.Id("firstname"))
                    .GetAttribute("value");
                Console.WriteLine(currentFirstName);
                String changedName;
                if (currentFirstName.Equals("Sergeant"))
                {
                    driver.FindElement(By.Id("firstname")).Clear();
                    driver.FindElement(By.Id("firstname")).SendKeys("General");
                    changedName = "General";
                }
                else if (currentFirstName.Equals("General"))
                {
                    driver.FindElement(By.Id("firstname")).Clear();
                    driver.FindElement(By.Id("firstname")).SendKeys("Sergeant");
                    changedName = "Sergeant";
                }
                else
                {
                    driver.FindElement(By.Id("firstname")).Clear();
                    driver.FindElement(By.Id("firstname")).SendKeys("Seargent");
                    changedName = "Seargent";
                }

                driver.FindElement(By.Id("old_passwd")).SendKeys("1qazxsw2");
                driver.FindElement(By.CssSelector("button[name='submitIdentity']")).Click();

                //Check if the empty message appears
                Assert.AreEqual("Your personal information has been successfully updated."
                    , driver.FindElement(By.CssSelector(".alert.alert-success")).Text
                    , "Validate if succesfull message appears");

                driver.FindElement(By.CssSelector(".account>span")).Click();

                //Click the personal info icon
                driver.FindElement(By.CssSelector(".icon-user")).Click();

                //Check the new name
                Console.WriteLine(driver.FindElement(By.Id("firstname")).GetAttribute("value"));
                Assert.AreEqual(changedName, driver.FindElement(By.Id("firstname")).GetAttribute("value")
                    , "Validate name change");
            }
        }
    }
}
