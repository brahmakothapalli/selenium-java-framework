package com.qababu.pageactions;

import com.qababu.Base.BaseTest;
import com.qababu.Helpers.ClickHelper;
import com.qababu.Helpers.TextHelper;
import org.openqa.selenium.WebDriver;

import static com.qababu.pageobjects.HomePageObjects.*;

public class HomePageActions extends BaseTest {


    public void loginAction(WebDriver driver, String uname, String pwd){
        try{
            TextHelper.enterText(driver, userName, uname);
            TextHelper.enterText(driver, password, pwd);
            ClickHelper.click(driver, signIn);
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }

    public String getLoginErrorText(WebDriver driver){
        try{
            return  TextHelper.getText(driver, loginErrorText);
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }


}
