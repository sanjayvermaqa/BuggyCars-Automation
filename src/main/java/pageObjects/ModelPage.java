package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import tests.BaseClass;
import utilities.Logging;

import java.util.List;


public class ModelPage extends BaseClass {

    public ModelPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id='comment']") public WebElement inpComment;
    @FindBy(xpath = "//button[@class='btn btn-success'][text()='Vote!']") public WebElement btnVote;
    @FindBy(xpath = "//table/tbody/tr[1]") public WebElement latestVote;

    /*
     * Method to validate page elements
     */
    SoftAssert softAssert = new SoftAssert();
    public void elementValidations() throws Exception {
        try {
            Logging.info("Validating elements on the page");
            softAssert.assertTrue(inpComment.isDisplayed(), "Comment field does not exists");
            softAssert.assertTrue(btnVote.isDisplayed(), "Vote button does not exists");

        } catch (Exception e) {
            throw new Exception("Unable to continue with test due to - " + e.getMessage());
        }
    }

    /*
     * Method to add comment
     */
    public void addComment(String name){
        inpComment.clear();
        inpComment.sendKeys("Test Comment from "+ name);
        btnVote.click();
    }

}
