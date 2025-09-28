import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.SigninPage;

public class HomeTest extends BaseTest{

    HomePage home;
    @BeforeTest
    public void setUp() {
        home=new HomePage();
    }
    @Test
    public void logOut()
    {
        home.clickOnLogOut();
        home.assertRedirection();

    }
}
