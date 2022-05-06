package com.qababu.utility.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {


    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    private static ExtentReports extent = ExtentManager.getExtent();

    private static ExtentTest suite;


    public static synchronized void startTestSuite(String suiteName) {

        suite = extent.createTest(suiteName);
    }

    public static synchronized void startTest(String testName) {

        ExtentTest test = suite.createNode(testName);

        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

    }

    public static ExtentTest getTest() {

        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));

    }

    public static void endTest() {
        extent.flush();
    }
}
