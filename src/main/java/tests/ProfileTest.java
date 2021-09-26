package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.ProfilePage;
import utilities.Constants;
import utilities.GeneralFunctions;
import utilities.Logging;

import static utilities.Constants.*;

public class ProfileTest extends BaseClass {
    HomePage homePage;
    ProfilePage profilePage;

    /*
    *Below test is to do the page validation tests
    */
    @BeforeTest
    public void setUp() throws Exception {
        try {
            GeneralFunctions.navigateToHomePage();
                        homePage = new HomePage(driver);
            int loadTime = ((Long) ((JavascriptExecutor) driver).executeScript(
                    "return performance.timing.loadEventEnd - performance.timing.navigationStart;")).intValue();
            Logging.info("Page took - " + (loadTime / 1000) + "sec to load");

            homePage.elementValidations();

            GeneralFunctions.loginUser();

            Logging.info(String.valueOf(TestResult.TEST_PASSED));
        } catch (Exception e) {
            Logging.error(String.valueOf(TestResult.TEST_FAILED));
            throw new Exception ("Test was unable to complete due to - " + e.getMessage());
        }
    }

    @Test (priority = 0)
    public void updateProfile() throws Exception{
        try{
            GeneralFunctions.waitForPageLoad();
            homePage.lnkProfile.click();
            Logging.info("Update user profile with following data : - " +
                    "\n Gender = " + gender +
                    "\n Age = " + age +
                    "\n Address = " + address +
                    "\n Phone = " + phone
            );
            profilePage = new ProfilePage(driver);
            profilePage.updateAge(age);
            profilePage.updateAddress(address);
            profilePage.updatePhone(phone);

            profilePage.btnSubmit.click();

            GeneralFunctions.logoutUser();
            Logging.info("Test Passed");

        }catch (Exception e){
            Logging.error("Test Failed");
            e.printStackTrace();
            throw new Exception("Test was unable to complete due to - " + e.getMessage());
        }
    }

}
