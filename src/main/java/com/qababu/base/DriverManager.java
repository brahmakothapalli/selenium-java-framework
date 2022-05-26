package com.qababu.base;

import com.qababu.enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class.getSimpleName());

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    private static String browserType;

    static void setBrowserType(String browser){

        browserType = browser;
    }

    public static WebDriver getDriver(){

        WebDriver driver = DriverManager.threadDriver.get();

        BrowserType browser = BrowserType.valueOf(browserType);

        if(driver == null){
            switch (browser){
                case CHROME:
                    logger.info("Launching Chrome Browser");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    threadDriver.set(driver);
                    break;
                case FIREFOX:
                    logger.info("Launching Firefox Browser");
                    WebDriverManager.firefoxdriver().arch32().setup();
                    driver = new FirefoxDriver();
                    break;
                case IE:
                    logger.info("Launching IE Browser");
                    WebDriverManager.iedriver().arch32().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case EDGE:
                    logger.info("Launching EDGE Browser");
                    WebDriverManager.edgedriver().arch32().setup();
                    driver = new EdgeDriver();
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
