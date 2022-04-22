package Base;

import PageActions.*;

public class PageObjectManager {

    private static SnapLoginPageActions snapLoginPageActions;

    private PageObjectManager(){

    }


//    public static LoginPageActions getLoginPageActions(){
//
//        return (loginPageActions == null) ? loginPageActions = new LoginPageActions() : loginPageActions;
//    }

    public static SnapLoginPageActions getSnapLoginPageActions(){

        return (snapLoginPageActions == null) ? snapLoginPageActions = new SnapLoginPageActions() : snapLoginPageActions;
    }


}
