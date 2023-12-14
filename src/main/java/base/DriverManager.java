package base;

import enums.BrowserType;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.extentReport.ExtentReportManager;
import utils.fileReader.ConfigurationReader;
import utils.logging.Logger;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;


public class DriverManager {

    private static final Logger logger = Logger.getInstance();

    private DriverManager() {
    }

    private static ThreadLocal<RemoteWebDriver> threadDriver = new ThreadLocal<>();

    static synchronized void setBrowserType(String browser) {
        logger.info("Setting the browserType " + browser + " in :: setBrowserType");
    }

    static String platform = ConfigurationReader.getConfigInstance().getProperty("platform");
    static String browser = ConfigurationReader.getConfigInstance().getProperty("browser");
    private static final String GRID_URL = "http://localhost:4444/wd/hub";

    @SneakyThrows
    public static synchronized RemoteWebDriver getDriver() {
        RemoteWebDriver driver = DriverManager.threadDriver.get();
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("download.default_directory", System.getProperty("user.home")+"//Downloads//");
        preferences.put("download.prompt_for_download", false);
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("profile.content_settings.exception.automatic_downloads.*.settings", 1);
        if (driver != null) {
            return driver;
        }
        switch (browser) {
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("--no-sandbox");
                options.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal");
                options.setCapability("goog:loggingPrefs", loggingPreferences);
                options.setExperimentalOption("prefs", preferences);
                if (platform.equalsIgnoreCase("docker")) {
                    threadDriver.set(new RemoteWebDriver(new URL(GRID_URL), options));
                } else {
                    threadDriver.set(new ChromeDriver(options));
                }
                break;
            case "FIREFOX":
                threadDriver.set(new FirefoxDriver());
                break;
            case "EDGE":
                threadDriver.set(new EdgeDriver());
                break;
            default:
                logger.error("Unsupported browser");
                throw new IllegalArgumentException(String.format("The given browser %s is not supported in this framework", browser));
        }
        return threadDriver.get();
    }

    public static void quitDriver() {
//        ExtentReportManager.logInfoDetails("Closing the browser :: quitDriver");
        if (DriverManager.threadDriver.get() != null) {
            DriverManager.threadDriver.get().quit();
            DriverManager.threadDriver.remove();
        }
    }
}
