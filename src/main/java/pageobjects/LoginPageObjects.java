package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPageObjects {

    public LoginPageObjects(){}

    public static final By userNameField = By.id("uName");

    public static final By passwordField = By.id("pWord");

    public static final By signInButton = By.xpath("//button[@type='submit' and contains(text(), 'Sign In')]");

    public static final By spinnerIcon = By.xpath("//div[@class='spinner-icon']");

    public static final By invalidLoginErrorText = By.xpath("//*[@id='loginPage']/section//form/div[2]");

    // Sauce Demo

    protected By swagLabsTitleLocator = By.xpath("//div[@class='login_logo']");

   protected By userNameLocator = By.id("user-name");

    protected By passwordLocator = By.id("password");

    protected By loginButtonLocator = By.id("login-button");

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


}
