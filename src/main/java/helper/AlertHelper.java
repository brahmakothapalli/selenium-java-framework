package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHelper {

    private static Logger logger = Logger.getLogger(AlertHelper.class);

    private AlertHelper() {
    }

    public static String getAlertText(WebDriver driver) {
        logger.info("getting the alert text :: getAlertText ");
        try {
            return driver.switchTo().alert().getText();
        } catch (Exception e) {
            logger.error("Failed to get alert text :: getAlertText");
            throw (e);
        }
    }


    public static void acceptAlert(WebDriver driver) {
        logger.info("accepting the alert :: acceptAlert ");
        try {
            if (isAlertPresent(driver)) {
                logger.info("Alert is present");
                Alert alert = driver.switchTo().alert();
                logger.info("Alert Text: " + alert.getText());
                alert.accept();
            } else {
                logger.info("Alert not present");
            }
        } catch (Exception e) {
            logger.error("Failed to switch to alert :: acceptAlert");
            throw (e);
        }

    }


    private static boolean isAlertPresent(WebDriver driver) {
        logger.info("checking whether alert present :: isAlertPresent ");
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }


    public static void dismissAlert(WebDriver driver) {
        logger.info("dismissing the alert :: dismissAlert ");
        try {
            if (isAlertPresent(driver)) {
                Alert alert = driver.switchTo().alert();
                logger.info("Alert Text: " + alert.getText());
                alert.dismiss();
            }
        } catch (Exception e) {
            logger.error("Failed to dismiss the alert :: dismissAlert");
            throw (e);
        }
    }

}
