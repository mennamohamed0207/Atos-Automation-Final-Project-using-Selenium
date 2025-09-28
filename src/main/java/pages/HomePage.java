package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;
import utiles.config.LoadProperties;

public class HomePage {
    WebDriver driver;
    By SignInUp = By.linkText("Signup / Login");
    By productsNavBar=By.xpath("//a[@href='/products']");
    By contactUsNavBar=By.xpath("//a[@href='/contact_us']");
    By logOutNavBar=By.xpath("//a[@href=\"/logout\"]");
     final static String loginURL= LoadProperties.loginURL;
public HomePage() {
        this.driver = DriverManager.getDriver();

    }

    public void clickOnSignInUpLink() {
        ElementHelper.click(driver,SignInUp);
    }
    public void clickOnProducts(){
    ElementHelper.click(driver,productsNavBar);
    }
    public void clickOnContactUs()
    {
        ElementHelper.click(driver,contactUsNavBar);
    }
    public  void clickOnLogOut()
    {
        ElementHelper.click(driver,logOutNavBar);
    }
    public void assertRedirection()
    {
        AssertionHelper.assertUrl(driver,loginURL);
    }
}
