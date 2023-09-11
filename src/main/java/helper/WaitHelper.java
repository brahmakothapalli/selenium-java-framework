package helper;

import Base.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitHelper {

    private static final Logger logger = Logger.getLogger(WaitHelper.class);
    private static final long TIMEOUT_PERIOD = 60;
    private static final long POLLING_PERIOD = 1;

    private WaitHelper() {
    }


    public static void waitForElementVisibility(By elementLocator) {
        logger.info("Waits for element visibility with the given locator:: waitForElementVisibility");
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (Exception e) {
            logger.error("Failed - Element not visible on the page with given locator"+elementLocator+" :: waitForElementVisibility ", e);
            throw (e);
        }
    }

    public static void waitForElementVisibility(WebElement element) {
        logger.info("Waits for element visibility with the given element:: waitForElementVisibility");
        try {
            waitFor(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Failed - Element not visible on the page with given locator"+element+" :: waitForElementVisibility ", e);
            throw (e);
        }
    }

    public static WebElement waitForStaleElement(WebDriver driver, By elementLocator, long pollingPeriod) throws InterruptedException {
        logger.info("Handling the stale element with given locator "+elementLocator+" :: waitForStaleElement");
        WebElement element = null;
        while(pollingPeriod>0){
            try {
                 element = driver.findElement(elementLocator);
            } catch (StaleElementReferenceException e) {
                logger.error("Element is stale, trying to find again :: waitForStaleElement");
                Thread.sleep(3000);
                pollingPeriod = pollingPeriod -1;
                pollingPeriod-=1;
            }
        }
        return element;
    }

    public static void waitForElementClickable(By elementLocator) {
        logger.info("Waiting for the element till it is clickable :: waitForElementClickable");
        try {
            waitFor(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (Exception e) {
            logger.error("Failed to wait till the element is clickable :: waitForElementClickable::" + elementLocator);
            throw (e);
        }
    }

    public static boolean waitForElementToBeDisplayed(By elementLocator) {
        logger.info("waiting for the element to be displayed locator :: "+elementLocator+" :: waitForElementToBeDisplayed");
        try {
            return getFluentWait(TIMEOUT_PERIOD, POLLING_PERIOD)
                    .until(ExpectedConditions.visibilityOfElementLocated(elementLocator))
                    .isDisplayed();
        } catch (Exception e) {
            logger.error("Element is not displayed on the page locator :: "+elementLocator+" :: waitForElementToBeDisplayed");
            return false;
        }
    }

    public static boolean isElementDisplayed(WebDriver driver, By elementLocator) {
        logger.info("waiting for the element to be displayed locator :: "+elementLocator+" :: isElementDisplayed");
        try {
            return driver.findElement(elementLocator).isDisplayed();
        } catch (Exception e) {
            logger.error("Element is not displayed on the page in the given time period, locator :: "+elementLocator+" :: waitForElementToBeDisplayed");
            throw (e);
        }
    }

    public static boolean isElementEnabled(WebElement element) {
        logger.info("checks if the given element is enabled :: "+element+" :: isElementEnabled");
        try {
            return element.isEnabled();
        } catch (Exception e) {
            logger.error("Element is not displayed on the page in the given time period, locator :: "+element+" :: waitForElementToBeDisplayed");
            throw (e);
        }
    }

    /**
     * Waits for the given condition
     * @param condition the condition that needs to be satisfied
     * @param <T> type of the condition
     */
    public static <T> void waitFor(ExpectedCondition<T> condition){
        waitFor(condition, TIMEOUT_PERIOD, POLLING_PERIOD);
    }

    /**
     * @param condition the condition that needs to be satisfied
     * @param timeout the maximum time period to wait for the condition to be satisfied
     * @param pollingPeriod time interval between each condition check
     * @param <T> type of the condition
     */
    public static <T> void waitFor(ExpectedCondition<T> condition, long timeout, long pollingPeriod){
        getFluentWait(timeout, pollingPeriod).until(condition);
    }


    /**
     * this method creates and returns FluentWait instance for the given timeout and polling period
     * @param timeout the maximum time period the given condition to be satisfied
     * @param pollingPeriod the waiting period between each check for the condition
     * @return instance of FluentWait
     */
    public static FluentWait<WebDriver> getFluentWait(long timeout, long pollingPeriod){
        return new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingPeriod))
                .ignoring(WebDriverException.class);
    }
}
