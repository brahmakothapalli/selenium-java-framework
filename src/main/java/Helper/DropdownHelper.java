package Helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DropdownHelper {

    private static Logger logger = Logger.getLogger(DropdownHelper.class);

    private DropdownHelper() {
    }

    public static void selectByValueFromDropdown(WebDriver driver, By elementLocator, String value) {
        logger.info("Selecting an option from dropdown using value :: selectByValueFromDropdown " + elementLocator);
        try {
            Select dropdown = new Select(driver.findElement(elementLocator));
            logger.info("The option selecting from dropdown is -"+value);
            dropdown.selectByValue(value);
        } catch (Exception e) {
            logger.error("Failed to select option from dropdown :: selectByValueFromDropdown");
            throw (e);
        }
    }

    public static void selectByVisibleTextFromDropdown(WebDriver driver, By elementLocator, String value) {
        logger.info("Selecting option from dropdown using visible text :: selectByVisibleTextFromDropdown "+elementLocator);
        try {
            WebElement element = driver.findElement(elementLocator);
            Select dropdown = new Select(element);
            logger.info("The value selecting from dropdown is  " + value);
            dropdown.selectByVisibleText(value);
        } catch (Exception e) {
            logger.error("Failed to select option from dropdown using visible text :: selectByVisibleTextFromDropdown");
            throw (e);
        }
    }

    public static String getFirstSelectedOption(WebDriver driver, By elementLocator) {
        logger.info("Returning  the default or first selected option of a dropdown :: getFirstSelectedOption "+elementLocator);
        try {
            Select dropdown = new Select(driver.findElement(elementLocator));
            return dropdown.getFirstSelectedOption().getText();
        } catch (Exception e) {
            logger.error("Failed to select option from dropdown using visible text :: getFirstSelectedOption");
            throw (e);
        }
    }

    public static int sizeOfSelectedDropdown(WebDriver driver, By elementLocator) throws InterruptedException {
        logger.info("Returning  the default or first selected option of a dropdown :: selectByIndexFromDropdown "+elementLocator);
        try {
            List<String> listOfOption= getDropdownValues(driver,elementLocator);

            return listOfOption.size();

        } catch (Exception e) {
            logger.error("Failed to select option from dropdown using index :: selectByIndexFromDropdown");
            throw (e);
        }
    }

    public static void selectByIndexFromDropdown(WebDriver driver, By elementLocator, int index) {
        logger.info("Returning  the default or first selected option of a dropdown :: selectByIndexFromDropdown "+elementLocator);
        try {
            Select dropdown = new Select(driver.findElement(elementLocator));
            logger.info("The index selecting from dropdown is  " + index);
            dropdown.selectByIndex(index);
        } catch (Exception e) {
            logger.error("Failed to select option from dropdown using index :: selectByIndexFromDropdown");
            throw (e);
        }
    }

    public static List<String> getDropdownValues(WebDriver driver, By elementLocator) throws InterruptedException {
        logger.info("Getting the options from dropdown:: getDropdownValues "+elementLocator);
        try {
            Thread.sleep(5000);
            Select dropdown = new Select(driver.findElement(elementLocator));
            List<WebElement> elements = dropdown.getOptions();
            logger.info("The size of the dropdown elements:: "+elements.size());
            List<String> options = new ArrayList<>();
            for(WebElement element : elements){
                options.add(element.getText());
            }
            return options;
        } catch (Exception e) {
            logger.error("Failed to get the options from dropdown:: getDropdownValues");
            throw (e);
        }
    }



}
