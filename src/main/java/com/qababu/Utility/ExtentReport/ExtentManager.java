package com.qababu.Utility.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {

        if (extent == null)
            createReport();

        return extent;
    }

    //Initiate Extent Report
    private static ExtentReports createReport() {

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//extentReport//report.html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        //report configuration
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setDocumentTitle("ExtentReport");

        //test environment info
        extent.setSystemInfo("Operating System", "Windows 10, 64 bit");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Brahma");
        extent.setSystemInfo("Release", "R27");

        return extent;
    }
}
