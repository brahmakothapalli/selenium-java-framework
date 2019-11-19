package com.qababu.Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickHelper {

    private ClickHelper(){}


    public static void click(WebDriver driver, By locator){
        try{
            driver.findElement(locator).click();
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }

    public static void clickUsingJS(WebDriver driver, By locator){
        try{
            WebElement element = driver.findElement(locator);
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();", element);
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
}
