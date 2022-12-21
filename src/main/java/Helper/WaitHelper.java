package Helper;

import Enums.ConstantVariable;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private static final Logger logger = Logger.getLogger(WaitHelper.class);

    private WaitHelper() {
    }

    public static void pause(long millis) throws InterruptedException {

      Thread.sleep(millis);
    }

    public static void waitForElementPresent(WebDriver driver, By elementLocator) {

        logger.info("Waiting for the element till it is present :: waitForElementPresent");
        try {

            WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(ConstantVariable.THIRTY_SECONDS)).ignoring(StaleElementReferenceException.class);
            wait.until((ExpectedCondition<Boolean>) webDriver -> {
                assert webDriver != null;
                WebElement element = webDriver.findElement(elementLocator);
                return element != null && element.isDisplayed();
            });
        } catch (Exception e) {
            logger.error("Failed to wait till the element is present :: waitForElementPresent::" + elementLocator);
            throw (e);
        }
    }

    public static void waitForElementVisibility(WebDriver driver, By elementLocator) {

        logger.info("Waiting for element visibility:: waitForElementVisibility");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConstantVariable.THIRTY_SECONDS));
            wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
            logger.info("Element is visibility");
        } catch (Exception e) {
            logger.error("Failed - Element not visible or present :: waitForElementVisibility ", e);
            throw (e);
        }
    }

    public static void waitForElementVisibilityCustom(WebDriver driver, By elementLocator, int seconds) {

        logger.info("Waiting for element visibility:: waitForElementVisibility");
        try {
            int period = seconds/3;
            while(period > 0){
                boolean result = ClickHelper.isElementExist(driver, elementLocator);
                if(result){
                    break;
                }else{
                    period-=1;
                }
            }
        } catch (Exception e) {
            logger.error("Failed - Element not visible or present :: waitForElementVisibility ",e);
            throw (e);
        }
    }

    public static WebElement waitForStaleElement(WebDriver driver, By elementLocator) {

        logger.info("Handling the stale element :: waitForStaleElement");
        try {
            return driver.findElement(elementLocator);
        } catch (StaleElementReferenceException e) {
            logger.error("Element is stale, trying to find again :: waitForStaleElement");
            return waitForStaleElement(driver, elementLocator);
        }
    }

    public static void waitForElementClickable(WebDriver driver, By elementLocator) {

        logger.info("Waiting for the element till it is clickable :: waitForElementClickable");
        try {
            WebElement element = driver.findElement(elementLocator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConstantVariable.THIRTY_SECONDS));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            logger.error("Failed to wait till the element is clickable :: waitForElementClickable::" + elementLocator);
            throw (e);
        }
    }

    public static boolean isElementDisplayed(WebDriver driver, By elementLocator) {

        logger.info("Checking whether element displayed or not in :: isElementDisplayed");
        try {
            WebElement element = driver.findElement(elementLocator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            logger.error("Element not displayed :: isElementDisplayed");
            throw (e);
        }
    }
}