package pageactions;

import base.BaseTest;
import io.qameta.allure.Step;
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
    @Step("Entering the user credentials")
    public void enterUserCredentials(WebDriver driver, String uname, String pwd){
        getUserNameElement(driver).sendKeys(uname);
        getPasswordElement(driver).sendKeys(pwd);
    }

    @Step("Clicking on login button")
    public ProductsPageActions clickLoginButton(WebDriver driver){
        getLoginElement(driver).click();
        return new ProductsPageActions();
    }



}
