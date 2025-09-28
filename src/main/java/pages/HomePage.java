package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.ElementHelper;

public class HomePage {
    WebDriver driver;
    By SignInUp = By.linkText("Signup / Login");
    By productsNavBar=By.xpath("//a[@href='/products']");
    By contactUsNavBar=By.xpath("//a[@href='/contact_us']");

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
}
