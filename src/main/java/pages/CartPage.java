package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;
import utiles.config.LoadProperties;

public class CartPage {
    WebDriver driver;
    By commentField = By.tagName("textarea");
    By placeOrderButton = By.xpath("//a[@href=\"/payment\"]");
    By cardName = By.xpath("//input[@data-qa=\"name-on-card\"]");
    By cardNumber = By.xpath("//input[@data-qa=\"card-number\"]");
    By cardCVC = By.xpath("//input[@data-qa=\"cvc\"]");
    By cardMonth = By.xpath("//input[@data-qa=\"expiry-month\"]");
    By cardYear = By.xpath("//input[@data-qa=\"expiry-year\"]");
    By confirmButton = By.xpath("//button[@data-qa=\"pay-button\"]");
    static final String checkoutURL = LoadProperties.checkoutURL;
    static final String paymentURL = LoadProperties.paymentURL;

    public CartPage() {
        this.driver = DriverManager.getDriver();

    }

    public CartPage clickOnProceedButton() {
        WebElement proceedButton = ElementHelper.findElementByTextContains("Proceed To Checkout", driver);
        proceedButton.click();
        return this;
    }

    public CartPage assertOnCheckoutURL() {
        AssertionHelper.assertUrl(driver, checkoutURL);
        return this;
    }

    public CartPage clickOnPlaceOrder() {
        ElementHelper.sendText(driver, commentField, "sending comment");
        ElementHelper.click(driver, placeOrderButton);
        AssertionHelper.assertUrl(driver, paymentURL);
        return this;
    }

    public CartPage enterCardName(String name) {
        ElementHelper.sendText(driver, cardName, name);
        return this;
    }

    public CartPage enterCardNumber(String number) {
        ElementHelper.sendText(driver, cardNumber, number);
        return this;

    }

    public CartPage enterCardCVC(String cvc) {
        ElementHelper.sendText(driver, cardCVC, cvc);
        return this;

    }

    public CartPage enterCardExpireYear(String year) {
        ElementHelper.sendText(driver, cardYear, year);
        return this;

    }

    public CartPage enterCardExpireMonth(String month) {
        ElementHelper.sendText(driver, cardMonth, month);
        ElementHelper.scrollToElement(driver, cardMonth);
        return this;

    }

    public CartPage clickOnConfirmButton() {

        ElementHelper.click(driver, confirmButton);
        return this;
    }

    public CartPage assertOnSuccessPayment() {
        WebElement successMessage = ElementHelper.findElementByTextContains("Congratulations", driver);
        AssertionHelper.assertNotNull(successMessage, "No success message");
        return this;
    }
}
