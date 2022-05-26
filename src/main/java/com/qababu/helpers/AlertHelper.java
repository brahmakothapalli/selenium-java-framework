package com.qababu.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHelper {

    private static final Logger logger = LogManager.getLogger(AlertHelper.class.getSimpleName());

    private AlertHelper(){}

    public boolean verifyIfAlertPresent(WebDriver driver){
        logger.info("Verifying if alert is present :: verifyIfAlertPresent");
        try{
            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException e){
            return false;
        }
    }

    public void acceptAlertIfPresent(WebDriver driver){
        logger.info("Accepting the alert if present :: acceptAlertIfPresent");
        try{
            driver.switchTo().alert().accept();
        }catch (NoAlertPresentException e){
            e.printStackTrace();
            throw e;
        }
    }

    public String getAlertText(WebDriver driver){
        logger.info("Fetching the alert text :: getAlertText");
        try{
            return driver.switchTo().alert().getText();
        }catch (NoAlertPresentException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void dismissAlertIfPresent(WebDriver driver){
        logger.info("Dismiss the alert if present :: dismissAlertIfPresent");
        try{
            driver.switchTo().alert().dismiss();
        }catch (NoAlertPresentException e){
            e.printStackTrace();
            throw e;
        }
    }
}
