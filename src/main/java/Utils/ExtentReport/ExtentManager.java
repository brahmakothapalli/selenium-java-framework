package Utils.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    //singleton pattern design
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    private static void createInstance() {

        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");

        Date date = new Date();

        String reportPath = System.getProperty("user.dir") + "\\Reports\\" + "results_report.html";

        //String reportPath = System.getProperty("user.dir") + "\\Reports\\" + dateFormat.format(date) + "_Parallel_ResultsReport.html";

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);

        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);

        //Report Config
        htmlReporter.config().setDocumentTitle("Automation Results Report");
        htmlReporter.config().setReportName("Regression Suite Execution Results");
        htmlReporter.config().setTheme(Theme.DARK);

        //System Info
        extent.setSystemInfo("OS", "Windows 10, 64-bit");
        extent.setSystemInfo("browserType", "Chrome");
        extent.setSystemInfo("Tester", "VAM Tester");

    }


}
