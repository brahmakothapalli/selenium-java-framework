package pageactions;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.logging.Logger;
import static pageobjects.LoginPageObjects.*;
public class LoginPageActions extends BaseTest {

    private static final Logger logger = Logger.getInstance();

    public String getSwagTitle(WebDriver driver){
        return driver.findElement(swagLabsTitleLocator).getText();
    }

    public WebElement getUserNameElement(WebDriver driver){
        return driver.findElement(userNameLocator);
    }

    public WebElement getPasswordElement(WebDriver driver){
        return driver.findElement(passwordLocator);
    }

    public WebElement getLoginElement(WebDriver driver) {
        return driver.findElement(loginButtonLocator);
    }
    public void enterUserCredentials(WebDriver driver, String uname, String pwd){
        getLoginElement(driver).sendKeys(uname);
        getPasswordElement(driver).sendKeys(pwd);
    }

    public void clickLoginButton(WebDriver driver){
        getLoginElement(driver).click();
    }



}
