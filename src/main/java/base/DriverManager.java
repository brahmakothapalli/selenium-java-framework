package base;

import enums.BrowserType;
import lombok.SneakyThrows;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.extentReport.ExtentReportManager;
import utils.fileReader.ConfigurationReader;
import utils.logging.Logger;

import java.net.URL;


public class DriverManager {

    private static final Logger logger = Logger.getInstance();
    private static String browserType;
    private DriverManager(){}

    private static ThreadLocal<RemoteWebDriver> threadDriver = new ThreadLocal<>();
    static synchronized void setBrowserType(String browser) {
        logger.info("Setting the browserType " + browser + " in :: setBrowserType");
        browserType = browser;
    }

    static String platform = ConfigurationReader.getConfigInstance().getProperty("platform");
    private static final String GRID_URL = "http://localhost:4444/wd/hub";

    @SneakyThrows
    public static synchronized RemoteWebDriver getDriver()  {
        BrowserType browser = BrowserType.valueOf(browserType);
        if (DriverManager.threadDriver.get() == null) {
            switch (browser) {
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    if(platform.equalsIgnoreCase("docker")){
                        threadDriver.set(new RemoteWebDriver(new URL(GRID_URL), options));
                    }else{
                        threadDriver.set(new ChromeDriver(options));
                    }
                    break;
                case FIREFOX:
                    threadDriver.set(new FirefoxDriver());
                    break;
                case EDGE:
                    threadDriver.set(new EdgeDriver());
                    break;
            }
        }
        return threadDriver.get();
    }

    static void quitDriver() {
        ExtentReportManager.logInfoDetails("Closing the browser :: quitDriver");
        if(getDriver()!=null){
            getDriver().quit();
            DriverManager.threadDriver.remove();
        }
    }
}
