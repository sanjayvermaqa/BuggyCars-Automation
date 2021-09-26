package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ModelPage;
import pageObjects.ProfilePage;
import utilities.GeneralFunctions;
import utilities.Logging;

import static utilities.Constants.*;

public class VotingTest extends BaseClass {
    HomePage homePage;
    ModelPage modelPage;

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
    public void addVote() throws Exception{
        try{
            homePage = new HomePage(driver);
            homePage.navigateToModelPage();
            Logging.info("Adding vote with comments");

            modelPage = new ModelPage(driver);
            String lastComment = modelPage.latestVote.getText();
            modelPage.addComment(fName);
            GeneralFunctions.pageRefresh();

            modelPage = new ModelPage(driver);
            Logging.info("Last voting record found as - " + lastComment);
            Logging.info("Latest voting after voting - " + modelPage.latestVote.getText());
            Assert.assertNotEquals(modelPage.latestVote.getText(), lastComment, "Comment not displayed");

            GeneralFunctions.logoutUser();
            Logging.info("Test Passed");

        }catch (Exception e){
            Logging.error("Test Failed");
            e.printStackTrace();
            throw new Exception("Test was unable to complete due to - " + e.getMessage());
        }
    }

}
