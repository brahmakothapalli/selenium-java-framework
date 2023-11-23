package pageobjects;

import org.openqa.selenium.By;

public class ProductsPageObjects {

    public static final By productTitle = By.xpath("//span[text()='Products']");
    public static final By cartTitle = By.xpath("//a[@class='shopping_cart_link']");

    public static final By firstAddToCartButton = By.xpath("//button[text()='Add to cart']");

    public static final By cartItems = By.xpath("//span[@class='shopping_cart_badge']");
    public static final  By filterTitleL = By.xpath("//select[@class='product_sort_container']");
    public static final By cartIcon = By.xpath("//a[@class='shopping_cart_link']");

}
