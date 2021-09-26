package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OverallPage;
import utilities.GeneralFunctions;
import utilities.Logging;

import java.util.List;

import static utilities.Constants.TestResult;

public class OverallPageTest extends BaseClass {
    HomePage homePage;
    OverallPage overallPage;

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

            Logging.info(String.valueOf(TestResult.TEST_PASSED));
        } catch (Exception e) {
            Logging.error(String.valueOf(TestResult.TEST_FAILED));
            throw new Exception ("Test was unable to complete due to - " + e.getMessage());
        }
    }

    @Test (priority = 0)
    public void navigationTest() throws Exception{
        try{
            homePage.navigateToOverallPage();
            overallPage = new OverallPage(driver);
            overallPage.elementValidations();
            action("Make");

            action("Model");

            //action("Rank");

            action("Votes");

            action("Engine");

            Logging.info("Test Passed");

        }catch (Exception e){
            Logging.error("Test Failed");
            e.printStackTrace();
            throw new Exception("Test was unable to complete due to - " + e.getMessage());
        }
    }

    private void action(String sortBy) throws InterruptedException {
        List<WebElement> lastResult;
        Logging.info("Sort by " + sortBy);
        lastResult = overallPage.ratings;
        if (sortBy == "Make"){
            overallPage.clickOnMake();
        }else if (sortBy == "Model"){
            overallPage.clickOnModel();
        }else if (sortBy == "Rank"){
            overallPage.clickOnRank();
        }else if (sortBy == "Votes"){
            overallPage.clickOnVotes();
        }else if (sortBy == "Engine"){
            overallPage.clickOnEngine();
        }
        GeneralFunctions.waitForPageLoad();
        overallPage = new OverallPage(driver);
        Thread.sleep(1000);
        GeneralFunctions.pageRefresh();

        int i = 0;
        for(WebElement e : lastResult){
            Assert.assertNotEquals(e.getText(), overallPage.ratings.get(i), "Sort by " + sortBy + " failed");
            i++;
        }
    }
}
