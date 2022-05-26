package com.qababu.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickHelper {

    private static final Logger logger = LogManager.getLogger(ClickHelper.class.getSimpleName());

    private ClickHelper(){}


    public static void clickGivenElement(WebDriver driver, By locator){
        logger.info("Clicking on given element :: clickGivenElement");
        try{
            driver.findElement(locator).click();
        }catch (Exception e){
           logger.error("Failed to click the given element :: clickGivenElement", e);
            throw (e);
        }
    }

    public static void clickUsingJS(WebDriver driver, By locator){
        logger.info("Clicking on given element using JS :: clickUsingJS");
        try{
            WebElement element = driver.findElement(locator);
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();", element);
        }catch (Exception e){
            logger.error("Failed to click the given element using JS :: clickUsingJS", e);
            throw (e);
        }
    }
}
