package tests;

import base.BaseTest;
import base.DriverManager;
import base.PageObjectManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageactions.CartPageActions;
import pageactions.LoginPageActions;
import pageactions.ProductsPageActions;

public class CartPageTests extends BaseTest {

    LoginPageActions loginPageActions;
    ProductsPageActions productsPageActions;

    CartPageActions cartPageActions;
    @BeforeClass
    public void beforeClassSetUp(){
        loginPageActions = PageObjectManager.getLoginPageActions();
    }
    @Test
    public void testCheckoutProducts(){
        loginPageActions.enterUserCredentials(DriverManager.getDriver(), "standard_user", "secret_sauce");
        productsPageActions = loginPageActions.clickLoginButton(DriverManager.getDriver());
        productsPageActions.addItemToCart(DriverManager.getDriver());
        Assert.assertTrue(productsPageActions.doesTheItemsAddedToCart(DriverManager.getDriver()), "Items not added to cart");
        String itemsCount = productsPageActions.getCartItemsCount(DriverManager.getDriver());
        Assert.assertEquals(itemsCount, String.valueOf(1));
        cartPageActions = productsPageActions.viewCart(DriverManager.getDriver());
    }

    @Test
    public void testRemoveProductsFromCart(){
        loginPageActions.enterUserCredentials(DriverManager.getDriver(), "standard_user", "secret_sauce");
        productsPageActions = loginPageActions.clickLoginButton(DriverManager.getDriver());
        productsPageActions.addItemToCart(DriverManager.getDriver());
        Assert.assertTrue(productsPageActions.doesTheItemsAddedToCart(DriverManager.getDriver()), "Items not added to cart");
        String itemsCount = productsPageActions.getCartItemsCount(DriverManager.getDriver());
        Assert.assertEquals(itemsCount, String.valueOf(1));
        cartPageActions = productsPageActions.viewCart(DriverManager.getDriver());
        cartPageActions.removeItemFromCart(DriverManager.getDriver());
        assert !productsPageActions.doesTheItemsAddedToCart(DriverManager.getDriver());

    }
}
