package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsHelper {

    private static Logger logger = Logger.getLogger(ActionsHelper.class);

    private ActionsHelper(){

    }

    public static void mouseHover(WebDriver driver, By elementLocator) throws InterruptedException {

        logger.info("Mouse hovering on the given element :: mouseHover");
        try {
            Thread.sleep(3000);

            Actions action = new Actions(driver);

            action.moveToElement(driver.findElement(elementLocator)).build().perform();

        } catch (Exception e) {
            logger.error("Failed to get alert text :: getAlertText");
            throw (e);
        }
    }


}
