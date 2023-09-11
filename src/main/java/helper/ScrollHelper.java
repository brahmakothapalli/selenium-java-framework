package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollHelper {

    private static Logger logger = Logger.getLogger(ScrollHelper.class);

    private ScrollHelper() {
    }

    public static void scrollElementIntoView(WebDriver driver, By elementLocator) {

        logger.info("Scrolling element into view using JavaScript:: scrollElementIntoView");
        try {
            WebElement element = driver.findElement(elementLocator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            logger.error("Failed to scroll element into view:: scrollElementIntoView");
            throw (e);
        }
    }

    public static void scrollToBottom(WebDriver driver) {

        logger.info("Scrolling to bottom of the page:: scrollElementIntoView");
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } catch (Exception e) {
            logger.error("Failed to scroll to bottom of the page:: scrollElementIntoView");
            throw (e);
        }


    }
}
