package Utils.TestNGListeners;

import base.BaseTest;
import base.DriverManager;
import Utils.ExtentReport.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.util.Arrays;

public class ReportTestListener implements ITestListener {

    private static ExtentReports extentReports;

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onStart(ITestContext context) {
        String reportPath = ExtentReportManager.getReportPath();
        extentReports = ExtentReportManager.createInstance(reportPath);
    }

    public void onFinish(ITestContext context) {
        if(extentReports!=null){
            extentReports.flush();
        }
    }

    public void onTestStart(ITestResult result) {
        ExtentTest testReport = extentReports.createTest(result.getTestClass().getName()+"-"+result.getMethod().getMethodName());
        extentTest.set(testReport);
    }

    public void onTestSuccess(ITestResult result) {
        String logText = "<b> Test Method "+result.getMethod().getMethodName()+" is Successful </b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);
    }

    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        String screenShotLocation = BaseTest.takeScreenshot(DriverManager.getDriver(), methodName);
        extentTest.get().fail("<details><summary><b><font color=red>"+"Exception Occured, click to see the details </font></b></summary>"+
                exceptionMessage.replaceAll(",", "<b>")+"</details> \n");
        if(new File(screenShotLocation).exists()){
            extentTest.get().fail("Screenshot of the failed test is <b> "+extentTest.get().addScreenCaptureFromPath(screenShotLocation));
        }
        String logText = "<b> Test Method "+methodName+" is Failed </b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
    }

    public void onTestSkipped(ITestResult result) {
        String logText = "<b> Test Method "+result.getMethod().getMethodName()+" is Skipped </b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, m);
    }


}
