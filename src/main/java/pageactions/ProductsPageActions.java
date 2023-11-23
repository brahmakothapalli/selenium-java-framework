package pageactions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static pageobjects.ProductsPageObjects.*;

public class ProductsPageActions {

    @Step("Getting product title")
    public String productTitle(WebDriver driver) {
        return driver.findElement(productTitle).getText();
    }

    @Step("Getting Cart title")
    public WebElement cartTitle(WebDriver driver) {
        return driver.findElement(cartTitle);
    }

    public String filterTitle(WebDriver driver) {
        return driver.findElement(filterTitleL).getText();
    }


    @Step("Adding items to cart")
    public void addItemToCart(WebDriver driver) {
        driver.findElement(firstAddToCartButton).click();
    }

    @Step("Adding multiple products")
    public void addMultipleProducts(WebDriver driver) {
        List<WebElement> productAddButtons = driver.findElements(firstAddToCartButton);
        for (WebElement button : productAddButtons ) {
            button.click();
        }
    }

    @Step("Checking items of cart")
    public boolean doesTheItemsAddedToCart(WebDriver driver){
        return !driver.findElements(cartItems).isEmpty();
    }

    @Step("Getting items count from cart")
    public String getCartItemsCount(WebDriver driver){
        return driver.findElement(cartItems).getText();
    }

    public CartPageActions viewCart(WebDriver driver) {
        driver.findElement(cartIcon).click();
        return new CartPageActions();
    }
}
