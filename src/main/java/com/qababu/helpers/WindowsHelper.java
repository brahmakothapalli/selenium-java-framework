package com.qababu.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class WindowsHelper {

    private static final Logger logger = LogManager.getLogger(WindowsHelper.class.getSimpleName());

    private WindowsHelper(){}

    public static int getWindowsCount(WebDriver driver){
        logger.info("Getting total windows count :: getWindowsCount");
        try{
           return driver.getWindowHandles().size();
        }catch (Exception e){
            logger.error("Failed get the windows count :: getWindowsCount", e);
            throw e;
        }
    }

    public static WebDriver switchToChildWindow(WebDriver driver, By elementLocator) throws InterruptedException {
        logger.info("Switching to child window :: switchToChildWindow");
        try{
            String parentWindow = driver.getWindowHandle();
            logger.info("clicking on the element that pops-up child window");
            driver.findElement(elementLocator).click();
            WaitHelper.pause(3000);
            Set<String> allWindows = driver.getWindowHandles();
            Iterator<String> iterator = allWindows.iterator();
            while (iterator.hasNext()){
               String childWindow = iterator.next();
               if(!parentWindow.equalsIgnoreCase(childWindow)){
                   driver.switchTo().window(childWindow);
                   return driver;
               }
            }
        } catch (Exception e) {
            logger.error("Failed to switch to child window :: switchToChildWindow", e);
            throw e;
        }
        return driver;
    }

    public static void switchToDefaultWindow(WebDriver driver){
        logger.info("Switching to default window :: switchToDefaultWindow");
        try{
            driver.switchTo().defaultContent();
        }catch (Exception e){
            logger.error("Failed to switch to default window :: switchToDefaultWindow");
            throw e;
        }
    }


}
