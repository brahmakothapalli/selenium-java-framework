package com.qababu.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextHelper {

    private TextHelper(){}

    public static void enterText(WebDriver driver, By locator, String input){
        try{
            driver.findElement(locator).sendKeys(input);
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }

    public static String getText(WebDriver driver, By locator){
        try{
            return driver.findElement(locator).getText();
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
}
