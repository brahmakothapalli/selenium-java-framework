package com.qababu.pagecctions;

import com.qababu.base.BaseTest;
import com.qababu.helpers.ClickHelper;
import com.qababu.helpers.TextHelper;
import org.openqa.selenium.WebDriver;

import static com.qababu.pageobjects.BookStorePageObjects.*;

public class BookStorePageActions extends BaseTest {


    public void loginAction(WebDriver driver, String uname, String pwd){
        try{
            TextHelper.enterText(driver, userName, uname);
            TextHelper.enterText(driver, password, pwd);
            ClickHelper.clickGivenElement(driver, signIn);
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
