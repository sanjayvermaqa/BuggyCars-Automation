package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import tests.BaseClass;
import utilities.Logging;


public class ProfilePage extends BaseClass {

    public ProfilePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "age") public WebElement inpAge;
    @FindBy(id = "address") public WebElement inpAddress;
    @FindBy(id = "phone") public WebElement inpPhone;
    @FindBy(xpath = "//button[@type='submit'][text()='Save']") public WebElement btnSubmit;
    @FindBy(xpath = "//a[@class='btn'][text()='Cancel']") public WebElement btnCancel;
    @FindBy(xpath = "//a[@class='result alert alert-success hidden-md-down']") public WebElement alertSuccess;

    /*
     * Method to validate page elements
     */
    SoftAssert softAssert = new SoftAssert();
    public void elementValidations() throws Exception {
        try {
            Logging.info("Validating elements on the page");
            softAssert.assertEquals(inpAge.isDisplayed(), "Age field does not exists");
            softAssert.assertEquals(inpAddress.isDisplayed(), "Address field does not exists");
            softAssert.assertEquals(inpPhone.isDisplayed(), "Phone field does not exists");
            softAssert.assertTrue(btnSubmit.isDisplayed(), "Submit button does not exists");
            softAssert.assertTrue(btnCancel.isDisplayed(), "Cancel button does not exists");

        } catch (Exception e) {
            throw new Exception("Unable to continue with test due to - " + e.getMessage());
        }
    }

    /*
     * Method to update age field
     */
    public void updateAge(String age){
        inpAge.clear();
        inpAge.sendKeys(age);
    }

    /*
     * Method to update address field
     */
    public void updateAddress(String address){
        inpAddress.clear();
        inpAddress.sendKeys(address);
    }

    /*
     * Method to update phone field
     */
    public void updatePhone(String phone){
        inpPhone.clear();
        inpPhone.sendKeys(phone);
    }

}
