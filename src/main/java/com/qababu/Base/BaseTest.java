package com.qababu.Base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public static void configSetup(){


    }

    @BeforeMethod
    public static void driverInit(){

        

    }

    @AfterMethod
    public static void driverClose(){


    }


    @AfterSuite
    public static void tearDown(){

    }
}
