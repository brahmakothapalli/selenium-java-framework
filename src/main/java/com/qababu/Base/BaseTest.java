package com.qababu.Base;

import com.qababu.Utility.ExtentReport.ExtentTestManager;
import com.qababu.Utility.FileReader.ConfigFileReader;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static Properties prop;

    private static final Logger logger = Logger.getLogger(BaseTest.class.getSimpleName());
    private static String appUrl;

    @BeforeSuite
    public static void configSetup() throws IOException {
        prop = ConfigFileReader.getConfigPropObject();
        appUrl = prop.getProperty("appURL");
        logger.info("The application url is :: "+ appUrl);
        String browserName = prop.getProperty("browserType");
        logger.info("The browser to be used is :: "+ browserName);
        DriverManager.setBrowserType(browserName);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.info("Executing the test - "+method.getName());
        ExtentTestManager.startTest(method.getName());
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        DriverManager.getDriver().get(appUrl);
    }


    @AfterMethod
    public static void driverClose(){
        ExtentTestManager.endTest();
        DriverManager.quitDriver();
    }

    @AfterSuite
    public static void tearDown(){

    }
}
