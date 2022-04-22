package Utils.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {

    private static Logger logger = Logger.getLogger(ExtentReport.class);

    private ExtentReport(){

    }

    public static ExtentReports ExtentReportInit(){

        logger.info("Initialising the ExtentReport in:: ExtentReport");

        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        Date date = new Date();

        String reportPath = System.getProperty("user.dir")+"\\Reports\\"+dateFormat.format(date)+"_MotorResultsReport.html";

        logger.info("Report path is - "+reportPath);

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);

        ExtentReports extent = new ExtentReports();

        extent.attachReporter(htmlReporter);

        //Report Config
        htmlReporter.config().setDocumentTitle("Motor Automation Results Report");
        htmlReporter.config().setReportName("Regression Test Results Report");
        htmlReporter.config().setTheme(Theme.DARK);


        //System Info
        extent.setSystemInfo("OS", "Windows 10, 64-bit");
        extent.setSystemInfo("browserType", "Chrome");
        extent.setSystemInfo("Tester", "VAM Tester");
        logger.info("Returning the ExtentReports Object in:: ExtentReport");
        return extent;
    }
}
