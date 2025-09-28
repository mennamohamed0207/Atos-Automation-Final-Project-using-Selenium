package utiles.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utiles.DriverMange.DriverManager;

import java.util.Arrays;

public class ExtentReportListener implements ITestListener {
    private static final ExtentReports extent = ExtentReportManager.getInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        ExtentTest extentTest = extent.createTest(testName)
                .assignCategory(className);
        test.set(extentTest);
        test.get().log(Status.INFO, "🚀 Test Started: " + testName);
        if (result.getParameters().length > 0) {
            test.get().log(Status.INFO, "Test Data: " + Arrays.toString(result.getParameters()));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test.get() != null) {
            test.get().log(Status.PASS, "✅ Test Passed in " +
                    (result.getEndMillis() - result.getStartMillis()) + " ms");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());
            test.get().addScreenCaptureFromBase64String(captureScreenshot(), "Failure Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (test.get() != null) {
            test.get().log(Status.SKIP, "⚠️ Test Skipped: " + result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public static String captureScreenshot() {
        try {
            TakesScreenshot scrShot = (TakesScreenshot) DriverManager.getDriver();
            return scrShot.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            return "Screenshot capture failed: " + e.getMessage();
        }
    }
}
