package Base;

import Enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverManager {

    private static Logger logger = Logger.getLogger(DriverManager.class);

    private static String browserType;

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

                  /*  DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setPlatform(Platform.LINUX);
                    caps.setBrowserName(org.openqa.selenium.remote.BrowserType.CHROME);
                    try {

                        driver = new RemoteWebDriver(new URL("http://localhost:32768/wd/hub"),caps);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }*/
//                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    driver = new ChromeDriver(options);
                    threadDriver.set(driver);
                    break;
                case FIREFOX:
//                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    threadDriver.set(driver);
                    break;
                case IE:
//                    WebDriverManager.iedriver().setup();
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
