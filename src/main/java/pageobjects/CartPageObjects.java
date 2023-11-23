package pageobjects;

import org.openqa.selenium.By;

public class CartPageObjects {

    By checkOutL = By.id("checkout");
    By firstNameL = By.id("first-name");
    By lastNameL = By.id("last-name");
    By pinCodeL = By.id("postal-code");
    By continueBtnL = By.id("continue");
    By checkOutOverViewL = By.xpath("//span[text()='Checkout: Overview']");
    By itemAddedToCartL = By.xpath("//div[@class='inventory_item_name']");
    By finishBtnL = By.id("finish");
    By completeHeaderL = By.xpath("//h2[@class='complete-header']");

}
