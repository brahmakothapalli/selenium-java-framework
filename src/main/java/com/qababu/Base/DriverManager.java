package com.qababu.Base;

import com.qababu.Enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverManager {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    private static String browserType;

    public static void setBrowserType(String browser){

        browserType = browser;
    }

    public static WebDriver getDriver(){

        WebDriver driver = DriverManager.threadDriver.get();

        BrowserType browser = BrowserType.valueOf(browserType);

        if(driver == null){
            switch (browser){
                case CHROME:
                    System.out.println("Launching Chrome Browser");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    threadDriver.set(driver);
                    break;
                case FIREFOX:
                    System.out.println("Launching Firefox Browser");
                    WebDriverManager.firefoxdriver().arch32().setup();
                    /*driver = new FirefoxDriver();*/
                    break;
                case IE:
                    System.out.println("Launching IE Browser");
                    WebDriverManager.iedriver().arch32().setup();
                    driver = new InternetExplorerDriver();
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
