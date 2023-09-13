package base;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.extentReport.ExtentReportManager;
import utils.fileReader.ConfigDataReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;


public class BaseTest {
    private static String appUrl = null;
    private static final Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public static void configSetUpMethod() {
        logger.info("Executing the @BeforeSuite - configSetUpMethod() in BaseTest ");
        ExtentReportManager.logInfoDetails("Reading the configuration details from config properties file :: configSetUpMethod");
        Properties prop = ConfigDataReader.ConfigPropInit();
        logger.info("Config Properties Initialised");
        String browserName = prop.getProperty("browserType");
        logger.info("Selected browserType is: " + browserName);
        DriverManager.setBrowserType(browserName);
        appUrl = prop.getProperty("appUrl");
        logger.info("Given application URL is: " + appUrl);
    }

    @BeforeMethod(alwaysRun = true)
    public static void beforeMethodSetUp(Method method, ITestContext context) {
        logger.info("Initialisation the browser  DriverManager.getDriver()::beforeMethodSetUp");
        ExtentReportManager.logInfoDetails(method.getName()+"- test execution started :: beforeMethodSetUp");
        DriverManager.getDriver().manage().deleteAllCookies();
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().navigate().to(appUrl);
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public static String takeScreenshot(WebDriver driver, String testName) {
        logger.info("Capturing the screenshot :: takeScreenshot");
        ExtentReportManager.logInfoDetails(" Taking the screenshot for the failed test :: takeScreenshot");
        String screenShotPath = null;
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            screenShotPath = System.getProperty("user.dir") + "\\Reports\\Screenshots\\" + testName + "_screenshot.png";
            logger.info("The screenshot is saved at " + screenShotPath);
            FileUtils.copyFile(src, new File(screenShotPath));
        } catch (IOException e) {
            logger.error("Failed to capture the screenshot:: takesScreenshot " + e);
        }
        return screenShotPath;
    }

    @AfterMethod(alwaysRun = true)
    public static synchronized void updateTestStatus(ITestResult result) {
        logger.info("updating result of test script " + result.getName() + " to report :: updateTestStatus");
        DriverManager.quitDriver();
    }

}
