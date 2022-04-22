package PageActions;

import Base.BaseTest;
import Helper.ClickHelper;
import Helper.TextHelper;
import Helper.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static PageObjects.SnapLoginPageObjects.*;

public class SnapLoginPageActions extends BaseTest {

    private static Logger logger = Logger.getLogger(SnapLoginPageActions.class);

    public void clickLetsGoButton(WebDriver driver){
        WaitHelper.waitForElementVisibility(driver, letsGoButton);
        ClickHelper.clickElement(driver, letsGoButton);
    }

    public void enterUserName(WebDriver driver, String user_name){
        WaitHelper.waitForElementVisibility(driver, userName);
        TextHelper.enterText(driver, userName, user_name);
    }

    public void enterPassword(WebDriver driver, String pwd){
        WaitHelper.waitForElementVisibility(driver, password);
        TextHelper.enterText(driver, userName, pwd);
    }

    public void clickNextButton(WebDriver driver){
        WaitHelper.waitForElementVisibility(driver, nextButton);
        ClickHelper.clickElement(driver, nextButton);
    }

    public void enterAnswer(WebDriver driver, String answer){
        WaitHelper.waitForElementVisibility(driver, answerField);
        TextHelper.enterText(driver, answerField, answer);
    }

    public void enterLoginCredentials(WebDriver driver, String user, String pwd, String answer){
        clickLetsGoButton(driver);
        enterUserName(driver, user);
        enterPassword(driver, pwd);
        clickNextButton(driver);
        enterAnswer(driver, answer);
    }

}
