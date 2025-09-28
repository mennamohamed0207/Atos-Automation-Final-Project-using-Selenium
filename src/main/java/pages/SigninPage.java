package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;
import utiles.commonHelper.Global;
import utiles.config.LoadProperties;

// WE use fluent pattern (Method chainning )
public class SigninPage {
    WebDriver driver;
    Global global;
    By LoginEmail =By.xpath("//input[@data-qa='login-email']");
    By  LoginPassword=By.xpath("//input[@data-qa='login-password']");
    By LoginButton =By.xpath("//button[@data-qa='login-button']");
    static final String HomeURL= LoadProperties.URL;
    public  SigninPage( ){
        this.driver= DriverManager.getDriver();
        global= Global.getInstance();
    }
    public  SigninPage EnterLoginEmail(){
        ElementHelper.sendText(driver,LoginEmail,global.getEmail());
        return this ;
    }
    public SigninPage enterLoginPassword(){
        ElementHelper.sendText(driver,LoginPassword,global.getPassword());
        return this ;
    } public  SigninPage EnterLoginFromFile(String email){
        ElementHelper.sendText(driver,LoginEmail,email);
        return this ;
    }
    public SigninPage enterLoginPasswordFromFile(String password){
        ElementHelper.sendText(driver,LoginPassword,password);
        return this ;
    }
    public SigninPage  clickOnLoginBtn() {
        ElementHelper.click(driver,LoginButton);
        return this ;
    }
    public void assertURLRedirection()
    {
        ElementHelper.waitForInvisibility(driver,LoginButton);
        AssertionHelper.assertUrl(driver,HomeURL);
    }
}
