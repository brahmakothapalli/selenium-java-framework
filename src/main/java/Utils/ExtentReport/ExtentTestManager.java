package Utils.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class ExtentTestManager {

    private static Logger logger = Logger.getLogger(ExtentTestManager.class);

    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

    private static ExtentReports extent = ExtentManager.getInstance();

    private static ExtentTest suite;


    public static synchronized void startSuite(String suiteName) {

        logger.info("Creating suite node - startSuite()");
        suite = extent.createTest(suiteName);
    }


    public static synchronized void startTest(String testName) {
        logger.info("Creating test node - startTest()");
        ExtentTest test = suite.createNode(testName);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));

    }

    public static synchronized void endTest() {
        extent.flush();
    }


}
