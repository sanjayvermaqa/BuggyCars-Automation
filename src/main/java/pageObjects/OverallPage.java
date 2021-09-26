package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import tests.BaseClass;
import utilities.GeneralFunctions;
import utilities.Logging;

import java.util.List;


public class OverallPage extends BaseClass {

    public OverallPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[text()='Make']") public WebElement lnkMake;
    @FindBy(xpath = "//a[text()='Model']") public WebElement lnkModel;
    @FindBy(xpath = "//a[text()='Rank']") public WebElement lnkRank;
    @FindBy(xpath = "//a[text()='Votes']") public WebElement lnkVotes;
    @FindBy(xpath = "//a[text()='Engine']") public WebElement lnkEngine;
    @FindBy(xpath = "//th[@class='comments'][text()='Comments']") public WebElement lnkComments;
    @FindBy(xpath = "//div/div/table[@class='cars table table-hover']/tbody") public List<WebElement> ratings;

    /*
     * Method to validate page elements
     */
    SoftAssert softAssert = new SoftAssert();
    public void elementValidations() throws Exception {
        try {
            Logging.info("Validating elements on the page");
            softAssert.assertTrue(lnkMake.isDisplayed(), "Make link does not exists");
            softAssert.assertTrue(lnkModel.isDisplayed(), "Model link does not exists");
            softAssert.assertTrue(lnkRank.isDisplayed(), "Rank link does not exists");
            softAssert.assertTrue(lnkVotes.isDisplayed(), "Votes button does not exists");
            softAssert.assertTrue(lnkEngine.isDisplayed(), "Engine link does not exists");
            softAssert.assertTrue(lnkComments.isDisplayed(), "Comments field does not exists");

        } catch (Exception e) {
            throw new Exception("Unable to continue with test due to - " + e.getMessage());
        }
    }

    /*
     * Method to click on make link
     */
    public void clickOnMake(){
        lnkMake.click();
        GeneralFunctions.waitForPageLoad();
    }

    /*
     * Method to click on model link
     */
    public void clickOnModel(){
        lnkModel.click();
        GeneralFunctions.waitForPageLoad();
    }

    /*
     * Method to click on rank link
     */
    public void clickOnRank(){
        lnkRank.click();
        GeneralFunctions.waitForPageLoad();
    }

    /*
     * Method to clink on votes link
     */
    public void clickOnVotes(){
        lnkVotes.click();
        GeneralFunctions.waitForPageLoad();
    }

    /*
     * Method to click on engine link
     */
    public void clickOnEngine(){
        lnkVotes.click();
        GeneralFunctions.waitForPageLoad();
    }
}
