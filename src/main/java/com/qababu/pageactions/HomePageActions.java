package com.qababu.pageactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.qababu.pageobjects.HomePageObjects.*;

public class HomePageActions {


    public void selectAttendeesAction(WebDriver driver, String value){

        //WebElement element = driver.findElement(participantTypeDrpdwn);

       // Select dropdown = new Select(element);

        //dropdown.selectByVisibleText(value);
    }

    public void loginAction(WebDriver driver, String userName, String paasw){

        driver.findElement(username).sendKeys(userName);

        driver.findElement(password).sendKeys(paasw);

        driver.findElement(signIn).click();
    }

    public String getLoginErrorText(WebDriver driver){

        return driver.findElement(loginErrorText).getText();
    }

    public void fillingRegistrationDetails(WebDriver driver, String value){

//        WebElement element = driver.findElement(participantTypeDrpdwn);
//
//        Select drop       =dropdown.selectByVisibleText(value);
    }
}
