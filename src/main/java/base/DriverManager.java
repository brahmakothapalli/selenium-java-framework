package base;

import enums.BrowserType;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.extentReport.ExtentReportManager;
import utils.fileReader.ConfigDataReader;


public class DriverManager {

    private static final Logger logger = Logger.getLogger(DriverManager.class);

    private static String browserType;

    private DriverManager(){

    }
    private static ThreadLocal<RemoteWebDriver> threadDriver = new ThreadLocal<>();
    static synchronized void setBrowserType(String browser) {
        logger.info("Setting the browserType " + browser + " in :: setBrowserType");
        ExtentReportManager.logInfoDetails("The selected browser for execution is - "+browser);
        browserType = browser;
    }
    public static synchronized RemoteWebDriver getDriver()  {
        RemoteWebDriver driver = DriverManager.threadDriver.get();
        BrowserType browser = BrowserType.valueOf(browserType);
        if (driver == null) {
            switch (browser) {
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    driver = new ChromeDriver(options);
                    ExtentReportManager.logInfoDetails("Chrome driver is initialized :: getDriver");
                    threadDriver.set(driver);
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    ExtentReportManager.logInfoDetails("Firefox driver is initialized :: getDriver");
                    threadDriver.set(driver);
                    break;
                case EDGE:
                    driver = new EdgeDriver();
                    ExtentReportManager.logInfoDetails("Edge driver is initialized :: getDriver");
                    threadDriver.set(driver);
                    break;
            }
        }
        return driver;
    }

    static void quitDriver() {
        ExtentReportManager.logInfoDetails("Closing the browser :: quitDriver");
        getDriver().quit();
        DriverManager.threadDriver.remove();
    }
}
