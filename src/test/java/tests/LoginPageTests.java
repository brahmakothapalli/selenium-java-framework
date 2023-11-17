package tests;

import base.BaseTest;
import base.DriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.LoginPageObjects;

public class LoginPageTests extends BaseTest {

    LoginPageObjects loginPageObjects;

    @BeforeClass
    public void setUp() {
        loginPageObjects = new LoginPageObjects();
    }

    @Test
    public void testLoginPageUI() {
        String title = loginPageObjects.getSwagTitle(DriverManager.getDriver());
        Assert.assertEquals(title, "Swag Labs", "Title is not as expected");
        Assert.assertTrue(loginPageObjects.getUserNameElement(DriverManager.getDriver()).isDisplayed());
        Assert.assertTrue(loginPageObjects.getPasswordElement(DriverManager.getDriver()).isDisplayed());
        Assert.assertTrue(loginPageObjects.getLoginElement(DriverManager.getDriver()).isDisplayed());
    }

    @Test
    public void testLoginScenarios() {
        loginPageObjects.getUserNameElement(DriverManager.getDriver()).sendKeys("standard_user");
        loginPageObjects.getPasswordElement(DriverManager.getDriver()).sendKeys("secret_sauce");
        loginPageObjects.getLoginElement(DriverManager.getDriver()).click();
    }

}
