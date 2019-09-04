package com.qababu.Base;

import com.qababu.Enums.BrowserType;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.remote.BrowserType.*;

public class DriverManager {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    private static String browserType;

    public static void setyBrowserType(String browser){

        browserType = browser;
    }

    public static WebDriver getDriver(){

        WebDriver driver = DriverManager.threadDriver.get();

        if(driver == null){

            switch (browserType){
                case CHROME:
                    System.out.println("Launching Chrome Browser");
                    break;
                case FIREFOX:
                    System.out.println("Launching Firefox Browser");
                    break;
                default:
                    break;
            }
        }

        return driver;
    }

    public static void quitDriver(){
        DriverManager.getDriver().quit();
        DriverManager.threadDriver.set(null);
    }

}
