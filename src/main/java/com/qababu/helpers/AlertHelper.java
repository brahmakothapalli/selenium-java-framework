package com.qababu.helpers;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHelper {

    private AlertHelper(){}

    public boolean verifyIfAlertPresent(WebDriver driver){
        try{
            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException e){
            return false;
        }
    }

    public void acceptAlertIfPresent(WebDriver driver){
        try{
            driver.switchTo().alert().accept();
        }catch (NoAlertPresentException e){
            e.printStackTrace();
            throw e;
        }
    }

    public String getAlertText(WebDriver driver){
        try{
            return driver.switchTo().alert().getText();
        }catch (NoAlertPresentException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void dismissAlertIfPresent(WebDriver driver){
        try{
            driver.switchTo().alert().dismiss();
        }catch (NoAlertPresentException e){
            e.printStackTrace();
            throw e;
        }
    }
}
