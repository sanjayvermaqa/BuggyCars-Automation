package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import utilities.Constants;
import utilities.GeneralFunctions;
import utilities.Logging;

import static utilities.Constants.*;

public class LoginTest extends BaseClass {
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    /*
    *Below test is to do the setup and page validation
    */
    @BeforeTest
    public void pageValidation() throws Exception {
        try {
            Logging.info("Navigate to home page");
            driver.navigate().to(Constants.url);

            homePage = new HomePage(driver);
            int loadTime = ((Long) ((JavascriptExecutor) driver).executeScript(
                    "return performance.timing.loadEventEnd - performance.timing.navigationStart;")).intValue();
            Logging.info("Page took - " + (loadTime / 1000) + "sec to load");

            homePage.elementValidations();

            Logging.info(String.valueOf(TestResult.TEST_PASSED));
        } catch (Exception e) {
            Logging.error(String.valueOf(TestResult.TEST_FAILED));
            throw new Exception ("Test was unable to complete due to - " + e.getMessage());
        }
    }

    /*
     * Test to validate login functionality
     */
    @Test (priority = 0)
    public void loginUser() throws Exception{
        try{
            Logging.info("Login user with credentials as : - " +
                    "\n Login = " + fName + lName +
                    "\n Password = " + password);
            GeneralFunctions.loginUser();
            validateSuccessfulLogin(fName);

            Logging.info("Test Passed");

        }catch (Exception e){
            Logging.error("Test Failed");
            e.printStackTrace();
            throw new Exception("Test was unable to complete due to - " + e.getMessage());
        }
    }

    /*
     * Test to validate logout functionality
     */
    @Test (priority = 1, dependsOnMethods = {"loginUser"})
    public void logoutUser() throws Exception{
        try{
            homePage.lnkLogout.click();
            Assert.assertTrue(homePage.inpLoginName.isDisplayed(), "Login name field not displayed");
            Assert.assertTrue(homePage.inpPassword.isDisplayed(), "Password field not displayed");
            Assert.assertTrue(homePage.btnLogin.isDisplayed(), "Login button not displayed");
            Assert.assertTrue(homePage.btnRegister.isDisplayed(), "Register button not displayed");

            Logging.info(String.valueOf(TestResult.TEST_PASSED));
        }catch (Exception e){
            Logging.error(String.valueOf(TestResult.TEST_FAILED));
            throw new Exception ("Test was unable to complete due to - " + e.getMessage());
        }
    }

    /*
     * Method to validate successful login
     */
    private boolean validateSuccessfulLogin(String name){
        GeneralFunctions.waitForPageLoad();
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.lblWelcome.getText().contains(name), "User Name not displayed after login");
        Assert.assertTrue(homePage.lnkProfile.isDisplayed(), "Profile link not displayed");
        Assert.assertTrue(homePage.lnkLogout.isDisplayed(), "Logout link not displayed");
        return true;
    }

}
