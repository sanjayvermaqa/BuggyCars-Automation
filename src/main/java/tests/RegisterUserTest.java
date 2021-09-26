package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import utilities.Logging;

import static utilities.Constants.*;

public class RegisterUserTest extends BaseClass {
    HomePage homePage;
    RegisterPage registerPage;
    SoftAssert softAssert = new SoftAssert();

    /*
    *Below test is to do the page validation tests
    */
    @BeforeTest
    public void pageValidation() throws Exception {
        try {
            homePage = new HomePage(driver);
            homePage.clickOnRegister();

            registerPage = new RegisterPage(driver);

            int loadTime = ((Long) ((JavascriptExecutor) driver).executeScript(
                    "return performance.timing.loadEventEnd - performance.timing.navigationStart;")).intValue();
            Logging.info("Page took - " + (loadTime / 1000) + "sec to load");

            registerPage.elementValidations();

            Logging.info(String.valueOf(TestResult.TEST_PASSED));
        } catch (Exception e) {
            Logging.error(String.valueOf(TestResult.TEST_FAILED));
            throw new Exception ("Test was unable to complete due to - " + e.getMessage());
        }
    }

    @Test
    public void registerNewUser() throws Exception{
        try{
            Logging.info("Fill all the valid values in the registration form as : - " +
                    "\n Login = " + fName + lName +
                    "\n First name = "+ fName +
                    "\n Last name = " + lName +
                    "\n Password = " + password);
            registerPage.inpUsername.sendKeys(loginName);
            registerPage.inpFName.sendKeys(fName);
            registerPage.inpLName.sendKeys(lName);
            registerPage.inpPassword.sendKeys(password);
            registerPage.inpConfirmPassword.sendKeys(password);

            registerPage.btnSubmit.click();
            Logging.info("All the values input done");

        }catch (Exception e){
            throw new Exception("Test was unable to complete due to - " + e.getMessage());
        }
    }
}
