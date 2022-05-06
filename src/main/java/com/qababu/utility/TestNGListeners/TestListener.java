package com.qababu.utility.TestNGListeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qababu.utility.ExtentReport.ExtentManager;
import com.qababu.utility.ExtentReport.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {


    @Override
    public void onStart(ITestContext context) {

        ExtentTestManager.startTestSuite(context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestManager.endTest();
        ExtentManager.getExtent().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTestManager.getTest().log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.AMBER));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ExtentTestManager.getTest().log(Status.ERROR, MarkupHelper.createLabel(result.getName(), ExtentColor.LIME));
    }



}
