package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExtentManager {

    private static ExtentReports extent;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // ----------------- Extent Report Setup -----------------
    public static ExtentReports createInstance() {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("AutomationExercise Test Report");
        spark.config().setDocumentTitle("Automation Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester", "Tejashwini");
        extent.setSystemInfo("Browser", "Chrome");
        return extent;
    }

    // ----------------- WebDriver Setup -----------------
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
