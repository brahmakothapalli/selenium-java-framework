package base;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.extentReport.ExtentReportManager;
import utils.fileReader.ConfigurationReader;
import utils.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;


public class BaseTest {
    private static String appUrl = null;
    private static final Logger logger = Logger.getInstance();

    @BeforeSuite(alwaysRun = true)
    public static void configSetUpMethod() {
        logger.info("Executing the @BeforeSuite - configSetUpMethod() in BaseTest ");
        Properties prop = ConfigurationReader.getConfigInstance();
        logger.info("Config Properties Initialised");
        String browserName = prop.getProperty("browser");
        logger.info("Selected browserType is: " + browserName);
        DriverManager.setBrowserType(browserName);
        appUrl = prop.getProperty("appUrl");
        logger.info("Given application URL is: " + appUrl);
    }
    @BeforeMethod(alwaysRun = true)
    public static void beforeMethodSetUp(Method method, ITestContext context) {
        logger.info("Initialisation the browser  DriverManager.getDriver()::beforeMethodSetUp");
        WebDriver browserDriver = DriverManager.getDriver();
        browserDriver.manage().deleteAllCookies();
        browserDriver.manage().window().maximize();
        browserDriver.navigate().to(appUrl);
        browserDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    public static File takeScreenshot(WebDriver driver, String testName) {
        logger.info("Capturing the screenshot :: takeScreenshot");
//        ExtentReportManager.logInfoDetails(" Taking the screenshot for the failed test :: takeScreenshot");
        String screenShotPath = null;
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            screenShotPath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_screenshot.png";
            logger.info("The screenshot is saved at " + screenShotPath);
            FileUtils.copyFile(src, new File(screenShotPath));
        } catch (IOException e) {
            logger.error("Failed to capture the screenshot:: takesScreenshot " + e);
        }
        return src;
    }

    @AfterMethod(alwaysRun = true)
    public static synchronized void updateTestStatus(ITestResult result) throws IOException {
        logger.info("updating result of test script " + result.getName() + " to report :: updateTestStatus");
        if(result.getStatus()==ITestResult.FAILURE){
           File screenshot = takeScreenshot(DriverManager.getDriver(), result.getName());
           Allure.addAttachment("Page Screenshot", FileUtils.openInputStream(screenshot));
        }
        DriverManager.quitDriver();
    }

}
