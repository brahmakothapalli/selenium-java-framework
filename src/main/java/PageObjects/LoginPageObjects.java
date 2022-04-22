package PageObjects;

import org.openqa.selenium.By;


public class LoginPageObjects {

    private LoginPageObjects(){}

    public static final By userNameField = By.id("uName");

    public static final By passwordField = By.id("pWord");

    public static final By signInButton = By.xpath("//button[@type='submit' and contains(text(), 'Sign In')]");

    public static final By spinnerIcon = By.xpath("//div[@class='spinner-icon']");

    public static final By invalidLoginErrorText = By.xpath("//*[@id='loginPage']/section//form/div[2]");


}
