package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import tests.BaseClass;
import utilities.GeneralFunctions;
import utilities.Logging;


public class HomePage extends BaseClass {

    public HomePage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "login") public WebElement inpLoginName;
    @FindBy(name = "password") public WebElement inpPassword;
    @FindBy(xpath = "//button[@type='submit'][text()='Login']") public WebElement btnLogin;
    @FindBy(xpath = "//a[@class='btn btn-success-outline'][text()='Register']") public WebElement btnRegister;
    @FindBy(xpath = "//div/ul/li[1]/span[contains(text(), 'Hi')]") public WebElement lblWelcome;
    @FindBy(xpath = "//a[@class='nav-link'][text()='Profile']") public WebElement lnkProfile;
    @FindBy(xpath = "//a[@class='nav-link'][text()='Logout']") public WebElement lnkLogout;
    @FindBy(xpath = "//div/div[1]/div/a/img[@class='img-fluid center-block']") public WebElement lnkMake;
    @FindBy(xpath = "//div/div[2]/div/a/img[@class='img-fluid center-block']") public WebElement lnkModel;
    @FindBy(xpath = "//div/div[3]/div/a/img[@class='img-fluid center-block']") public WebElement lnkOverall;

    /*
     * Method to validate page elements
     */
    SoftAssert softAssert = new SoftAssert();
    public void elementValidations() throws Exception {
        try {
            Logging.info("Validating elements on the page");
            softAssert.assertEquals(inpLoginName.isDisplayed(), "Login name field does not exists");
            softAssert.assertTrue(inpPassword.isDisplayed(), "Password field does not exists");
            softAssert.assertTrue(btnLogin.isDisplayed(), "Login button does not exists");
            softAssert.assertTrue(btnRegister.isDisplayed(), "Register button does not exists");

        } catch (Exception e) {
            throw new Exception("Unable to continue with test due to - " + e.getMessage());
        }
    }

    /*
     * Method to click on Register button
     */
    public void clickOnRegister() throws Exception{
        try{
            btnRegister.click();
        }catch (Exception e){
            throw new Exception("Unable to navigate to registration page due to error - "+ e.getMessage());
        }
    }

    /*
     * Method to navigate to model page
     */
    public void navigateToModelPage() {
        Logging.info("Navigate to Model page");
        lnkModel.click();
        GeneralFunctions.waitForPageLoad();
    }

    /*
     * Method to navigate to make page
     */
    public void navigateToMakePage(){
        Logging.info("Navigate to Make page");
        lnkMake.click();
        GeneralFunctions.waitForPageLoad();
    }

    /*
     * Method to navigate to overall page
     */
    public void navigateToOverallPage(){
        Logging.info("Navigate to Overall Rating page");
        lnkOverall.click();
        GeneralFunctions.waitForPageLoad();
    }
}
