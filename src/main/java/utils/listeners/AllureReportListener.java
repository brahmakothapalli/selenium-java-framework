package utils.listeners;

import base.BaseTest;
import base.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.extentReport.ExtentReportManager;

import java.io.File;
import java.util.Arrays;

public class AllureReportListener implements ITestListener {

    private static ExtentReports extentReports;

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("onFinish");
    }


    @Override
    public void onFinish(ITestContext context) {
        System.out.println("onFinish");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("onFinish");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("onFinish");
    }

    @Override
    @SneakyThrows
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
       /* String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        String screenShotLocation = null;
        screenShotLocation = BaseTest.takeScreenshot(DriverManager.getDriver(), methodName);*/

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("onFinish");
    }

    @Attachment(value="Page screenshot", type = "image/png")
    public byte[] getScreenshotAsPNG(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }


}
