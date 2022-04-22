package Helper;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextHelper {

    private static Logger logger = Logger.getLogger(TextHelper.class);
    
    private TextHelper(){
        
    }

    public static void enterText(WebDriver driver, By elementLocator, String text) {

        logger.info("Entering the text into the field " + elementLocator + " :: enterText");
        try {
            driver.findElement(elementLocator).clear();
            driver.findElement(elementLocator).sendKeys(text);
        } catch (Exception e) {
            logger.error("Failed to enter the text to the element " + elementLocator+" :: enterText");
            throw (e);
        }
    }

    public static String getText(WebDriver driver, By elementLocator) {

        logger.info("Getting the text from the given element "+elementLocator+" :: getText: ");
        try {
            return driver.findElement(elementLocator).getText();
        } catch (Exception e) {
            logger.error("Failed to get the text from the element " + elementLocator+" :: getText");
            throw (e);
        }
    }
}
