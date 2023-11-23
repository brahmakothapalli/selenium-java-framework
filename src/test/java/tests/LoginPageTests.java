package tests;

import base.BaseTest;
import base.DriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageactions.LoginPageActions;
import pageobjects.LoginPageObjects;

public class LoginPageTests extends BaseTest {

    LoginPageActions loginPageActions;

    @BeforeClass
    public void setUp() {
        loginPageActions = new LoginPageActions();
    }

    @Test
    public void testLoginPageUI() {
        String title = loginPageActions.getSwagTitle(DriverManager.getDriver());
        Assert.assertEquals(title, "Swag Labs", "Title is not as expected");
        Assert.assertTrue(loginPageActions.getUserNameElement(DriverManager.getDriver()).isDisplayed());
        Assert.assertTrue(loginPageActions.getPasswordElement(DriverManager.getDriver()).isDisplayed());
        Assert.assertTrue(loginPageActions.getLoginElement(DriverManager.getDriver()).isDisplayed());
    }

    @Test
    public void testLoginScenarios() {
        loginPageActions.enterUserCredentials(DriverManager.getDriver(), "standard_user", "secret_sauce");
        loginPageActions.clickLoginButton(DriverManager.getDriver());
    }

}
