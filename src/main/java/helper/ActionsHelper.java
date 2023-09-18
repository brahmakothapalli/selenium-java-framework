package helper;

import base.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsHelper {

    private static final Logger logger = Logger.getLogger(ActionsHelper.class);

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
