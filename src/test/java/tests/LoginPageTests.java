package tests;

import base.BaseTest;
import base.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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
    @Description("This test validates login page UI")
    public void testLoginPageUI() {
        String title = loginPageActions.getSwagTitle(DriverManager.getDriver());
        Assert.assertEquals(title, "Swag Labs", "Title is not as expected");
        Assert.assertTrue(loginPageActions.getUserNameElement(DriverManager.getDriver()).isDisplayed());
        Assert.assertTrue(loginPageActions.getPasswordElement(DriverManager.getDriver()).isDisplayed());
        Assert.assertTrue(loginPageActions.getLoginElement(DriverManager.getDriver()).isDisplayed());
    }

    @Test
    @Description("This test validates login functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginScenarios() {
        loginPageActions.enterUserCredentials(DriverManager.getDriver(), "standard_user", "secret_sauce");
        loginPageActions.clickLoginButton(DriverManager.getDriver());
    }

}
