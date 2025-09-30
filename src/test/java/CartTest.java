import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import utiles.ExtentReports.ExtentReportListener;
import utiles.datareaders.DataProviderUtils;

import java.io.IOException;
import java.util.Iterator;

@Listeners(ExtentReportListener.class)

public class CartTest extends BaseTest {

    CartPage cart;

    @BeforeTest
    public void setUp() {
        cart = new CartPage();
    }

    @DataProvider
    public Iterator<Object[]> getData() throws IOException {
        return DataProviderUtils.getData("src/test/resources/PaymentTestData.json");
    }

    @Test(testName = "Payment", groups = "regression", dataProvider = "getData")
    public void checkoutAndProceed(String name, String number, String cvc, String monthExpire, String yearExpire) {
        cart.clickOnProceedButton().assertOnCheckoutURL();
        cart.clickOnPlaceOrder();
        cart.enterCardName(name).enterCardNumber(number)
                .enterCardCVC(cvc).enterCardExpireMonth(monthExpire)
                .enterCardExpireYear(yearExpire).clickOnConfirmButton().assertOnSuccessPayment();
    }
}
