package com.qababu.Base;

import com.qababu.Enums.ConstantVariables;
import com.qababu.Utility.ExtentReport.ExtentTestManager;
import com.qababu.Utility.FileReader.ConfigFileReader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {

    private static Properties prop;

    private static final Logger logger = Logger.getLogger(BaseTest.class.getSimpleName());

    @BeforeSuite
    public static void configSetup() throws IOException {

        prop = ConfigFileReader.getConfigPropObject();

        logger.info("The app url - "+prop.getProperty("appURL"));

        DriverManager.setBrowserType(ConstantVariables.CHROME);

    }

    @BeforeMethod
    public static void driverInit(Method method){

        ExtentTestManager.startTest(method.getName());
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(prop.getProperty("appURL"));

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
