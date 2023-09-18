package utils.extentReport;

import utils.listeners.ReportTestListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {

    private static final Logger logger = Logger.getLogger(ExtentReportManager.class);

    private ExtentReportManager(){}

    public static ExtentReports getInstance() {
        logger.info("Creating extent report instance if it is null - singleton pattern :: getInstance");
        return new ExtentReports();
    }

    public static ExtentReports createInstance(String reportPath) {

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        logger.info("Report template creation using extent spark reporter ::  createInstance");
        sparkReporter.config().setDocumentTitle("Automation Results Report");
        sparkReporter.config().setReportName("Regression Suite Execution Results");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setEncoding("utf-8");

        ExtentReports extentReport = new ExtentReports();

        extentReport.attachReporter(sparkReporter);

        logger.info("Adding system, env detail to report :: createInstance");
        extentReport.setSystemInfo("OS", "Windows 10, 64-bit");
        extentReport.setSystemInfo("browserType", "Chrome");
        extentReport.setSystemInfo("Tester", "Brahma Kothapalli");
        return extentReport;
    }

    public static String getReportPath(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDate = dateTime.format(dateTimeFormatter);
        return System.getProperty("user.dir") + "\\reports\\" + "resultsReport-"+formattedDate+".html";
    }

    public static void logInfoDetails(String info){
        ReportTestListener.extentTest.get().info(MarkupHelper.createLabel(info, ExtentColor.GREY));
    }

    public static void logPassDetails(String info){
        ReportTestListener.extentTest.get().info(MarkupHelper.createLabel(info, ExtentColor.GREEN));
    }

    public static void logFailDetails(String info){
        ReportTestListener.extentTest.get().info(MarkupHelper.createLabel(info, ExtentColor.RED));
    }

    public static void logWarningDetails(String info){
        ReportTestListener.extentTest.get().info(MarkupHelper.createLabel(info, ExtentColor.YELLOW));
    }
}
