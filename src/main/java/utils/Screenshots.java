package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshots {

    public static String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.out.println("Driver is null, skipping screenshot for: " + testName);
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = System.getProperty("user.dir")
                + "/src/test/resources/screenshots/"
                + screenshotName;

        try {
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            FileHandler.copy(sourceFile, destFile);
            System.out.println("✅ Screenshot saved at: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            System.out.println("❌ Screenshot failed: " + e.getMessage());
            return null;
        }
    }
}
