package com.qababu.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class TextHelper {

    private static final Logger logger = LogManager.getLogger(TextHelper.class.getSimpleName());

    private TextHelper(){}

    public static void enterText(WebDriver driver, By locator, String input){
        logger.info("Enter text to the given element :: enterText");
        try{
            driver.findElement(locator).sendKeys(input);
        }catch (Exception e){
            logger.error("Failed to enter text to the given element :: enterText", e);
            throw (e);
        }
    }

    public static String getText(WebDriver driver, By locator){
        logger.info("Get text from the given element :: getText");
        try{
            return driver.findElement(locator).getText();
        }catch (Exception e){
            logger.error("Failed to get text from the given element :: getText", e);
            throw (e);
        }
    }

    public static List<String> getTextFromAllElements(WebDriver driver, By locator){
        logger.info("Get text from the given element :: getText");
        try{
            List<String> allElementsText = new ArrayList<>();
            driver.findElements(locator).stream().forEach(ele -> allElementsText.add(ele.getText()));
            allElementsText.forEach(text -> System.out.println(text));
            return allElementsText;
        }catch (Exception e){
            logger.error("Failed to get text from the given element :: getText", e);
            throw (e);
        }
    }
}
