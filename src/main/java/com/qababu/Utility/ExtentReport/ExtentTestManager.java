package com.qababu.Utility.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {


    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    static ExtentReports extent = ExtentManager.getExtent();

    static ExtentTest suite;


    public static synchronized void startTestSuite(String suiteName) {

        suite = extent.createTest(suiteName);
    }

    public static synchronized ExtentTest startTest(String testName) {

        ExtentTest test = suite.createNode(testName);

        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

        return test;
    }

    public static ExtentTest getTest() {

        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));

    }

    public static void endTest() {
        extent.flush();
    }
}
