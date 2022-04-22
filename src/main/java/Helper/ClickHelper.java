package Helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;


public class ClickHelper {

    private static Logger logger = Logger.getLogger(ClickHelper.class);

    private ClickHelper() {

    }

    public static void clickElement(WebDriver driver, By elementLocator) {

        logger.info("Clicking on the element " + elementLocator + "in :: clickElement");
        try {
            driver.findElement(elementLocator).click();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
            logger.info("Trying to clicking on the element " + elementLocator + "using JavaScriptExecutor :: clickElement");
            javaScriptExecutorClick(driver, elementLocator);
        } catch (StaleElementReferenceException e) {
            logger.info("Trying to clicking on the element " + elementLocator + "using JavaScript from clickElement method");
            WebElement element = WaitHelper.waitForStaleElement(driver, elementLocator);
            element.click();
        } catch (Exception e) {
            logger.error("Failed to clicking on the element " + elementLocator + "in :: clickElement");
            throw (e);
        }
    }

    public static boolean isElementExist(WebDriver driver, By elementLocator) {
        boolean exist = false;
        logger.info("Checking whether the given element exist or not :: isElementExist " + elementLocator);
        try {
            driver.findElement(elementLocator);
            exist = true;
        }catch (NoSuchElementException e) {
            logger.info("Element doesn't exist :: isElementExist");        }
        catch (Exception e) {
            logger.error("Given element not present on the web page :: isElementExist");
            throw (e);
        }
        return exist;
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
