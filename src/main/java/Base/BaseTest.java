package Base;

import Utils.DataProvider.TestDataProvider;
import Utils.ExtentReport.ExtentManager;
import Utils.FileReader.ConfigDataReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Properties;


public class BaseTest {

    private static String appUrl;
    protected static ExtentReports extentReport;
    protected static ExtentTest classLogger;
    protected static ExtentTest testLogger;
    private static int index = 0;

    private static final Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public static void configSetUpMethod() {

        logger.info("Executing the @BeforeSuite - configSetUpMethod() in BaseTest ");

        Properties prop;

        prop = ConfigDataReader.ConfigPropInit();

        logger.info("Config Properties Initialised");

        String browserName = prop.getProperty("browserType");

        logger.info("Selected browserType is: " + browserName);

        DriverManager.setBrowserType(browserName);

        appUrl = prop.getProperty("appUrl");

        logger.info("Given application URL is: " + appUrl);

        logger.info("Initialising extent report");

        extentReport = ExtentManager.getInstance();
    }

    @BeforeMethod(alwaysRun = true)
    public static void beforeMethodSetUp(Method method, ITestContext context) {
        logger.info("Initialisation the browser  DriverManager.getDriver()::beforeMethodSetUp");
        List<String> list = TestDataProvider.getTestDescription();
        testLogger = classLogger.createNode(method.getName() + "_" + list.get(index));
        index++;
        DriverManager.getDriver().manage().deleteAllCookies();
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().navigate().to(appUrl);
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    public static String takeScreenshot(WebDriver driver, String testName) {

        logger.info("Capturing the screenshot :: takeScreenshot");

        String screenShotPath = null;

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {
            screenShotPath = System.getProperty("user.dir") + "\\Reports\\Screenshots\\" + testName + "_screenshot.png";
            //screenShotPath = System.getProperty("user.dir") + "\\Public\\Public Pictures\\Report\\" + testName + "_screenshot.png";

            logger.info("The screenshot is saved at " + screenShotPath);

            FileUtils.copyFile(src, new File(screenShotPath));

        } catch (IOException e) {

            logger.error("Failed to capture the screenshot:: takesScreenshot " + e);

        }
        return screenShotPath;
    }

    private static synchronized void logTestStatusToReport(WebDriver driver, ITestResult result) throws IOException {

        logger.info("Executing logTestStatusToReport() method");

        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("updating test result as PASS in :: logTestStatusToReport");
            testLogger.log(Status.PASS,
                    MarkupHelper.createLabel(result.getName() + " - Test Case PASSED", ExtentColor.GREEN));

        } else if (result.getStatus() == ITestResult.FAILURE) {
            logger.info("updating test result as FAIL in :: logTestStatusToReport");
            testLogger.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName() + " - Test Case FAILED", ExtentColor.RED));

            String screenShotLocation = takeScreenshot(driver, result.getName());

            if ((new File(screenShotLocation)).exists()) {

                logger.info("Screenshot available at the location and trying to attach to the report");

                testLogger.fail("Test Case failed check the screenshot below " + testLogger.addScreenCaptureFromPath("Screenshots\\" + result.getName() + "_screenshot.png"));
            } else {

                logger.warn("Screenshot doesn't exist at the location");
            }

        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.info("updating test result as SKIP in :: logTestStatusToReport");
            testLogger.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + " - Test Case SKIPPED", ExtentColor.BLUE));

        }
    }

    @AfterMethod(alwaysRun = true)
    public static synchronized void updateTestStatus(ITestResult result) {

        logger.info("updating result of test script " + result.getName() + " to report :: updateTestStatus");
        try {
            logTestStatusToReport(DriverManager.getDriver(), result);
        } catch (IOException e) {
            logger.error("Failed to update the status of the test case:: updateTestStatus" + e);
        }
        DriverManager.quitDriver();
        testLogger.log(Status.PASS, "Closed the browser successfully");
    }


    @AfterSuite(alwaysRun = true)
    public static void endExecution() {
        logger.info("Flushing the extent report in:: endExecution");
        extentReport.flush();
    }
}
