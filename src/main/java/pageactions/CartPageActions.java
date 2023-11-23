package pageactions;

import org.openqa.selenium.WebDriver;
import static pageobjects.CartPageObjects.*;

public class CartPageActions {

    public void removeItemFromCart(WebDriver driver){
        driver.findElement(removeButton).click();
    }
}
