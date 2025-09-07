package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * This class is a TestNG Listener.
 * It listens to the test execution and:
 *   - Logs results in ExtentReports
 *   - Takes screenshots on Pass, Fail, Skip
 */
public class TestListener implements ITestListener {

    // Create ExtentReports instance (report generator)
    private static ExtentReports extent = ExtentManager.createInstance();

    // ThreadLocal ensures tests running in parallel get correct report entries
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Runs when a test case starts.
     */
    @Override
    public void onTestStart(ITestResult result) {
        // Create a test entry in Extent Report with the test method name
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    /**
     * Runs when a test passes.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = ExtentManager.getDriver(); // Get current driver
        String screenshotPath = Screenshots.takeScreenshot(driver, result.getMethod().getMethodName());

        try {
            // Log PASS and attach screenshot
            test.get().log(Status.PASS, "✅ Test Passed",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            test.get().log(Status.PASS, "✅ Test Passed (screenshot attach failed)");
        }
    }

    /**
     * Runs when a test fails.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ExtentManager.getDriver();
        String screenshotPath = Screenshots.takeScreenshot(driver, result.getMethod().getMethodName());

        try {
            // Log FAIL + error + screenshot
            test.get().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            test.get().log(Status.FAIL, "❌ Test Failed (screenshot attach failed): " + result.getThrowable());
        }
    }

    /**
     * Runs when a test is skipped.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        WebDriver driver = ExtentManager.getDriver();
        String screenshotPath = Screenshots.takeScreenshot(driver, result.getMethod().getMethodName());

        try {
            // Log SKIPPED and attach screenshot
            test.get().log(Status.SKIP, "⚠️ Test Skipped",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            test.get().log(Status.SKIP, "⚠️ Test Skipped (screenshot attach failed)");
        }
    }

    /**
     * Runs after all tests are finished.
     */
    @Override
    public void onFinish(ITestContext context) {
        // Save and write everything to Extent Report
        extent.flush();
    }
}
