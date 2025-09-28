import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import pages.ProductsPage;
import pages.SigninPage;
import utiles.ExtentReports.ExtentReportListener;
import utiles.datareaders.DataProviderUtils;

import java.io.IOException;
import java.util.Iterator;

@Listeners(ExtentReportListener.class)

public class ContactUsTest extends BaseTest{
    SigninPage signIn;
    HomePage home;
    ContactUsPage contactus;
    @BeforeTest
    public void setUp() {
        contactus=new ContactUsPage();
        home=new HomePage();
    }

    @DataProvider
    public Iterator<Object[]> getData() throws IOException {
        return DataProviderUtils.getData("src/test/resources/ContactUsTestData.json");
    }
    @Test(testName = "contact us", groups = "regression", dataProvider = "getData")
    public void contactUsFunction(String name,String email,String subject,String message,String filePath)
    {
        home.clickOnContactUs();
        contactus.enterNameField(name).enterEmailField(email).enterMessageField(message).uploadFile(filePath).enterSubjectField(subject).clickOnSubmit();
        contactus.assertOnPopUp();
        contactus.assertOnSuccessMessage();
    }

}
