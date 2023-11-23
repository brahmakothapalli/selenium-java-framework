package tests;

import base.BaseTest;
import base.DriverManager;
import base.PageObjectManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageactions.LoginPageActions;
import pageactions.ProductsPageActions;
import pageobjects.ProductsPageObjects;

public class ProductsPageTest extends BaseTest {

    LoginPageActions loginPageActions;
    ProductsPageActions productsPageActions;
    @BeforeClass
    public void beforeClassSetUp(){
        loginPageActions = PageObjectManager.getLoginPageActions();
    }

    @Test
    @Description("This test validates products page UI")
    public void testValidateProductsPage(){
        loginPageActions.enterUserCredentials(DriverManager.getDriver(), "standard_user", "secret_sauce");
        productsPageActions = loginPageActions.clickLoginButton(DriverManager.getDriver());
        String productsTitle = productsPageActions.productTitle(DriverManager.getDriver());
        Assert.assertEquals(productsTitle,"Products","Product title not matched");
        WebElement cartIcon = productsPageActions.cartTitle(DriverManager.getDriver());
        Assert.assertTrue(cartIcon.isDisplayed(),"cart icon not displayed");
    }

    @Test
    @Description("This test validates adding product to cart functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddingProductToCart()   {
        loginPageActions.enterUserCredentials(DriverManager.getDriver(), "standard_user", "secret_sauce");
        productsPageActions = loginPageActions.clickLoginButton(DriverManager.getDriver());
        productsPageActions.addItemToCart(DriverManager.getDriver());
        Assert.assertTrue(productsPageActions.doesTheItemsAddedToCart(DriverManager.getDriver()), "Items not added to cart");
        String itemsCount = productsPageActions.getCartItemsCount(DriverManager.getDriver());
        Assert.assertEquals(itemsCount, String.valueOf(1));
        System.out.println(itemsCount);
    }

    @Test
    @Description("This test validates adding multiple products functionality")
    @Severity(SeverityLevel.TRIVIAL)
    public void testAddMultipleProducts()    {
        loginPageActions.enterUserCredentials(DriverManager.getDriver(), "standard_user", "secret_sauce");
        productsPageActions = loginPageActions.clickLoginButton(DriverManager.getDriver());
        productsPageActions.addMultipleProducts(DriverManager.getDriver());
        String count = productsPageActions.getCartItemsCount(DriverManager.getDriver());
        System.out.println(count);
    }
}
