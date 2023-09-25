package helper;


import base.DriverManager;
import org.openqa.selenium.WebDriver;
import utils.logging.Logger;

import java.util.Iterator;
import java.util.Set;

public class WindowsHelper {

    private WindowsHelper(){}

    private static final Logger logger = Logger.getInstance();

    public static String getParentWindow(){
        logger.info("Getting all window handles :: getAllWindows :: WindowsHelper");
        return DriverManager.getDriver().getWindowHandle();
    }

    public static Set<String> getAllWindows(){
        logger.info("Getting all window handles :: getAllWindows :: WindowsHelper");
        return DriverManager.getDriver().getWindowHandles();
    }

    public static WebDriver switchToChildWindow(){
        String parentWindow = getParentWindow();
        Set<String> allWindows = getAllWindows();
        Iterator<String> iterator = allWindows.iterator();
        while (iterator.hasNext()){
            String window = iterator.next();
            if(!parentWindow.equalsIgnoreCase(window)){
                return DriverManager.getDriver().switchTo().window(window);
            }
        }
        return null;
    }

    public static void switchToParentWindow(String parentWindow){
        logger.info("Switching back to parent window :: switchToParentWindow :: WindowsHelper");
        DriverManager.getDriver().switchTo().window(parentWindow);
    }
}
