package Base;

import Enums.BrowserType;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverManager {

    private static final Logger logger = Logger.getLogger(DriverManager.class);

    private static String browserType = "CHROME";

    private DriverManager(){

    }

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    static synchronized void setBrowserType(String browser) {

        logger.info("Setting the browserType " + browser + " in :: setBrowserType");
        browserType = browser;
    }

    public static synchronized WebDriver getDriver()  {

        WebDriver driver = DriverManager.threadDriver.get();

        BrowserType browser = BrowserType.valueOf(browserType);

        if (driver == null) {

            switch (browser) {
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    driver = new ChromeDriver(options);
                    threadDriver.set(driver);
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    threadDriver.set(driver);
                    break;
                case EDGE:
                    driver = new InternetExplorerDriver();
                    threadDriver.set(driver);
                    break;
            }
        }
        return driver;
    }

    static void quitDriver() {
        getDriver().quit();
        DriverManager.threadDriver.set(null);
    }
}
