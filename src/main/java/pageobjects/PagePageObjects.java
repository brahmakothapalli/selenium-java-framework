package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PagePageObjects {

    By productTitle = By.xpath("//span[text()='Products']");
    By cartTitle = By.xpath("//a[@class='shopping_cart_link']");

    By cartItems = By.xpath("//span[@class='shopping_cart_badge']");
    By filterTitleL = By.xpath("//select[@class='product_sort_container']");
    By addToCartL = By.xpath("//span[@class='shopping_cart_link']");
    By cartIcon = By.xpath("//span[@class='shopping_cart_badge']");

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
        driver.findElement(addToCartL).click();
    }

    public boolean doesItemsAddedToCart(WebDriver driver){
        return driver.findElement(cartItems).isDisplayed();
    }

    public String getCartItemsCount(WebDriver driver){
        return driver.findElement(cartItems).getText();
    }

    public CartPageObjects viewCart(WebDriver driver) {
        driver.findElement(addToCartL).click();
        return new CartPageObjects();
    }




}
