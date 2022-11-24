package Utils.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    //singleton pattern design
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static void createInstance() {

        String reportPath = System.getProperty("user.dir") + "\\Reports\\" + "results_report.html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        //Report Config
        sparkReporter.config().setDocumentTitle("Automation Results Report");
        sparkReporter.config().setReportName("Regression Suite Execution Results");
        sparkReporter.config().setTheme(Theme.DARK);

        //System Info
        extent.setSystemInfo("OS", "Windows 10, 64-bit");
        extent.setSystemInfo("browserType", "Chrome");
        extent.setSystemInfo("Tester", "VAM Tester");


    }


}
