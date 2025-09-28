import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.SigninPage;
import utiles.ExtentReports.ExtentReportListener;
import utiles.datareaders.DataProviderUtils;

import java.io.IOException;
import java.util.Iterator;

@Listeners(ExtentReportListener.class)

public class ProductsTest extends BaseTest {
    ProductsPage products;
    SigninPage signIn;
    HomePage home;
    @BeforeTest
    public void setUp() {
        products = new ProductsPage();
        signIn = new SigninPage();
        home=new HomePage();
    }

    @DataProvider
    public Iterator<Object[]> getData() throws IOException {
        return DataProviderUtils.getData("src/test/resources/SearchTestData.json");
    }

    @Test(testName = "search Loads", groups = "regression", dataProvider = "getData")
    public void searchProducts(String searchQuery) {
        home.clickOnProducts();
        products.assertOnProducts();
        products.searchForKeyword(searchQuery).clickOnSearchLens().checkOnRelevantProducts(searchQuery);

        products.clickOnFirstSearchResult();
        products.assertOnProductDetails();
        products.checkOnProductDetails();
    }

}
