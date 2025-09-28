import org.testng.annotations.*;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SigninPage;
import utiles.ExtentReports.ExtentReportListener;
import utiles.commonHelper.ElementHelper;
import utiles.datareaders.DataProviderUtils;

import java.io.IOException;
import java.util.Iterator;

@Listeners(ExtentReportListener.class)
public class SignInTest  extends  BaseTest{
    HomePage Home;
    SigninPage SigninPage;

    @BeforeTest
    public  void setUp(){
        Home =new HomePage();
        SigninPage=new SigninPage();
    }
    @DataProvider
    public Iterator<Object[]> getData() throws IOException {
        return DataProviderUtils.getData("src/test/resources/SignInTestData.json");
    }
    @Test
    public  void SignIn(){
        Home.clickOnSignInUpLink();
        SigninPage.EnterLoginEmail().enterLoginPassword().clickOnLoginBtn();
    }
    @Test(testName = "SignIn", groups = "regression",dataProvider = "getData")
    public void signInExistingEmail(String email,String password)
    {
        Home.clickOnSignInUpLink();
        SigninPage.EnterLoginFromFile(email).enterLoginPasswordFromFile(password).clickOnLoginBtn();
        SigninPage.assertURLRedirection();
    }
}
