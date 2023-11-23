package pageactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static pageobjects.ProductsPageObjects.*;

public class ProductsPageActions {

    public String productTitle(WebDriver driver) {
        return driver.findElement(productTitle).getText();
    }

    public WebElement cartTitle(WebDriver driver) {
        return driver.findElement(cartTitle);
    }

    public String filterTitle(WebDriver driver) {
        return driver.findElement(filterTitleL).getText();
    }


    public void addItemToCart(WebDriver driver) {
        driver.findElement(firstAddToCartButton).click();
    }

    public void addMultipleProducts(WebDriver driver) {
        List<WebElement> productAddButtons = driver.findElements(firstAddToCartButton);
        for (WebElement button : productAddButtons ) {
            button.click();
        }
    }

    public boolean doesTheItemsAddedToCart(WebDriver driver){
        return !driver.findElements(cartItems).isEmpty();
    }

    public String getCartItemsCount(WebDriver driver){
        return driver.findElement(cartItems).getText();
    }

    public CartPageActions viewCart(WebDriver driver) {
        driver.findElement(cartIcon).click();
        return new CartPageActions();
    }
}
