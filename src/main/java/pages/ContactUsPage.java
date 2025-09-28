package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;
import utiles.commonHelper.Global;

public class ContactUsPage {
    WebDriver driver;
    Global global;
    By nameField= By.xpath("//input[@data-qa=\"name\"]");
    By emailField= By.xpath("//input[@data-qa=\"email\"]");
    By subjectField= By.xpath("//input[@data-qa=\"subject\"]");
    By messageField= By.xpath("//textarea[@data-qa=\"message\"]");
    By fileUploadField= By.xpath("//input[@name=\"upload_file\"]");
    By submitButton= By.xpath("//input[@data-qa=\"submit-button\"]");
    public ContactUsPage() {
        this.driver = DriverManager.getDriver();
        global = Global.getInstance();
    }
    public ContactUsPage enterNameField(String name)
    {
        ElementHelper.sendText(driver,nameField,name);
        return this;
    }
    public ContactUsPage enterEmailField(String email)
    {
        ElementHelper.sendText(driver,emailField,email);
        return this;
    }
    public ContactUsPage enterSubjectField(String subject)
    {
        ElementHelper.sendText(driver,subjectField,subject);
        return this;
    }
    public ContactUsPage enterMessageField(String message)
    {
        ElementHelper.sendText(driver,messageField,message);
        return this;
    }
    public ContactUsPage uploadFile(String filePath)
    {
        ElementHelper.sendText(driver,fileUploadField,filePath);

        return this;
    }
    public ContactUsPage clickOnSubmit()
    {
        ElementHelper.scrollToElement(driver,submitButton);
        ElementHelper.click(driver,submitButton);
        return this;
    }
    public void assertOnPopUp()
    {
        AssertionHelper.assertPopUpAppearance(driver);

    }
    public void assertOnSuccessMessage()
    {
        WebElement successMessage=ElementHelper.findElementByText("Success! Your details have been submitted successfully.",driver);
        AssertionHelper.assertNotNull(successMessage,"The request was not send");
    }

}
