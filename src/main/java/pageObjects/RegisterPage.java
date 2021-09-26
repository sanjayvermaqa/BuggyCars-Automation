package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import tests.BaseClass;
import utilities.Logging;


public class RegisterPage extends BaseClass {

    public RegisterPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(text(), 'Register with Buggy Cars Rating')]") public WebElement lblTitle;
    @FindBy(id = "username") public WebElement inpUsername;
    @FindBy(id = "firstName") public WebElement inpFName;
    @FindBy(id = "lastName") public WebElement inpLName;
    @FindBy(id = "password") public WebElement inpPassword;
    @FindBy(id = "confirmPassword") public WebElement inpConfirmPassword;
    @FindBy(xpath = "//button[@type='submit'][text()='Register']") public WebElement btnSubmit;
    @FindBy(xpath = "//a[@class='btn'][text()='Cancel']") public WebElement btnCancel;
    @FindBy(xpath = "//a[@class='result alert alert-success']") public WebElement alertSuccess;

    /*
     * Method to validate page elements
     */
    SoftAssert softAssert = new SoftAssert();
    public void elementValidations() throws Exception {
        try {
            Logging.info("Validating elements on the page");
            softAssert.assertEquals(lblTitle.isDisplayed(), "Title field does not exists");
            softAssert.assertEquals(inpUsername.isDisplayed(), "Username field does not exists");
            softAssert.assertTrue(inpFName.isDisplayed(), "First name field does not exists");
            softAssert.assertTrue(inpLName.isDisplayed(), "Last name field does not exists");
            softAssert.assertTrue(inpPassword.isDisplayed(), "Password field does not exists");
            softAssert.assertTrue(btnSubmit.isDisplayed(), "Submit button does not exists");
            softAssert.assertTrue(btnCancel.isDisplayed(), "Cancel button does not exists");

        } catch (Exception e) {
            throw new Exception("Unable to continue with test due to - " + e.getMessage());
        }
    }
}
