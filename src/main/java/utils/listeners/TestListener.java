package utils.listeners;


import base.BaseTest;
import base.DriverManager;
import utils.extentReport.ExtentReportManager;
import utils.extentReport.ExtentTestManager;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener  implements ITestListener {

    private static final Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test suite started :: onStart() - "+context.getName());
        ExtentTestManager.startSuite(context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite finished :: onFinish() - "+context.getName());
        ExtentTestManager.endTest();
        ExtentReportManager.getInstance().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test case started :: onTestStart() - "+result.getMethod().getMethodName());
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test case passed :: onTestSuccess() - "+result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {

        logger.info("Test case failed :: onTestFailure() - "+result.getMethod().getMethodName());

        String screenShotLocation = BaseTest.takeScreenshot(DriverManager.getDriver(), result.getName());

        if ((new File(screenShotLocation)).exists()) {

            logger.info("Screenshot available at the location and trying to attach to the report");

            ExtentTestManager.getTest().fail("Test Case failed check the screenshot below " + ExtentTestManager.getTest().addScreenCaptureFromPath("Screenshots\\" + result.getName() + "_screenshot.png")+result.getThrowable().getMessage());

        } else {
            logger.warn("Screenshot doesn't exist at the location");
        }
        ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test case skipped :: onTestSkipped() - "+result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.SKIP, MarkupHelper.createLabel("Test Skipped", ExtentColor.YELLOW));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("Test case FailedButWithinSuccessPercentage :: onTestFailedButWithinSuccessPercentage() - "+result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.WARNING, MarkupHelper.createLabel("Test Skipped", ExtentColor.AMBER));
    }


}
