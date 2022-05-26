package com.qababu.helpers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    private static final Logger logger = LogManager.getLogger(WaitHelper.class.getSimpleName());

    public static void pause(long time) throws InterruptedException {
        Thread.sleep(time);
    }

    public static void waitForElementVisibility(WebDriver driver, By elementLocator){
        try{
            logger.info("Wait till the element is visible :: waitForElementVisibility");
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        }catch (Exception e){
            logger.error("Element is not visible :: waitForElementVisibility", e);
            throw (e);
        }
    }

    public static void waitForElementToBeClickable(WebDriver driver, By elementLocator){
        try{
            logger.info("Wait till the element is clickable :: waitForElementToBeClickable");
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        }catch (Exception e){
            logger.error("Element is not clickable :: waitForElementToBeClickable", e);
            throw (e);
        }
    }

    public static WebElement getStaleElement(WebDriver driver, By elementLocator) throws InterruptedException {

        for(int count=0; count<5; count++){
            try{
                return driver.findElement(elementLocator);
            }catch (StaleElementReferenceException ser){
                WaitHelper.pause(2000);
                logger.warn("StaleElementReferenceException is thrown, trying again");
            }catch (Exception e){
                logger.error("Stale element is not found :: getStaleElement ", e);
                throw (e);
            }
        }
        return null;
    }

    public static boolean isElementDisplayed(WebDriver driver, By elementLocator){
        try{
            WebElement element = driver.findElement(elementLocator);
            return element.isDisplayed();
        }catch (Exception e){
            logger.info("Element is not displayed :: isElementDisplayed", e);
            throw (e);
        }
    }

    public static boolean isElementEnabled(WebDriver driver, By elementLocator){
        try{
            WebElement element = driver.findElement(elementLocator);
            return element.isEnabled();
        }catch (Exception e){
            logger.info("Element is not enabled :: isElementEnabled", e);
            throw (e);
        }
    }


}
