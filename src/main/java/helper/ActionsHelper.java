package helper;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import utils.logging.Logger;

public class ActionsHelper {

    private static final Logger logger = Logger.getInstance();

    private ActionsHelper(){
    }

    public static void mouseHover(By elementLocator) {
        logger.info("Mouse hovering on the given element :: mouseHover");
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(DriverManager.getDriver().findElement(elementLocator)).build().perform();
        } catch (Exception e) {
            logger.error("Failed to hover the mose on the given element :: mouseHover"+e.getMessage());
            throw (e);
        }
    }


}
