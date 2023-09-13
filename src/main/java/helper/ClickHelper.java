package helper;

import base.DriverManager;
import utils.extentReport.ExtentReportManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;


public class ClickHelper {

    private static final Logger logger = Logger.getLogger(ClickHelper.class);

    private ClickHelper() {

    }

    public static void clickElement(WebDriver driver, By elementLocator) throws InterruptedException {
        ExtentReportManager.logInfoDetails("Clicking on the given element :: clickElement");
        logger.info("Clicking on the element " + elementLocator + "in :: clickElement");
        try {
            driver.findElement(elementLocator).click();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
            ExtentReportManager.logWarningDetails("Clicking on the given element thrown an exception :: clickElement "+e.getMessage());
            logger.info("Trying to clicking on the element " + elementLocator + "using JavaScriptExecutor :: clickElement");
            javaScriptExecutorClick(driver, elementLocator);
        } catch (StaleElementReferenceException e) {
            ExtentReportManager.logWarningDetails("Clicking on the given element thrown an exception :: clickElement "+e.getMessage());
            logger.info("Trying to clicking on the element " + elementLocator + "using JavaScript from clickElement method");
            WebElement element = WaitHelper.waitForStaleElement(DriverManager.getDriver(), elementLocator, 10);
            element.click();
        } catch (Exception e) {
            logger.error("Failed to clicking on the element " + elementLocator + "in :: clickElement");
            throw (e);
        }
    }

    public static boolean isElementExist(WebDriver driver, By elementLocator) {
        ExtentReportManager.logInfoDetails("checking whether element exists or not :: isElementExist");
        logger.info("Checking whether the given element exist or not :: isElementExist " + elementLocator);
        return !driver.findElements(elementLocator).isEmpty();
    }

    public static void javaScriptExecutorClick(WebDriver driver, By elementLocator) {
        logger.info("Clicking element using :: JavaScriptExecutor " + elementLocator);
        try {
            WebElement element = driver.findElement(elementLocator);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            logger.error("javaScriptExecutorClick also failed :: javaScriptExecutorClick");
            throw (e);
        }
    }

    }
