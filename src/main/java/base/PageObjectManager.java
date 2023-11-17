package base;

import pageactions.*;

public class PageObjectManager {

    private static LoginPageActions loginPageActions;

    private PageObjectManager(){

    }


    public static LoginPageActions getLoginPageActions(){

        return (loginPageActions == null) ? loginPageActions = new LoginPageActions() : loginPageActions;
    }




}
