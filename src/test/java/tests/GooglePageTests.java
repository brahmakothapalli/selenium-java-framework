package tests;

import base.BaseTest;
import base.DriverManager;
import helper.ClickHelper;
import helper.TextHelper;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.dataProvider.JsonDataProvider;
import utils.extentReport.ExtentReportManager;
import utils.listeners.ExtentReportListener;
import utils.logging.Logger;

@Listeners(ExtentReportListener.class)
public class GooglePageTests extends BaseTest {

    private static final Logger logger = Logger.getInstance();

    @Test(dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class, description = "GoogleSearchTestData")
    @Parameters("GoogleSearchTestData")
    public void testSearchMyBlog(JSONObject jsonObject) {
        logger.info("Executing testSearchMyBlog :: GooglePageTests");
        ExtentReportManager.logInfoDetails("Executing the test :: testSearchMyBlog");
        TextHelper.enterText(DriverManager.getDriver(), By.name("q"), jsonObject.get("searchKeyword").toString()+Keys.ENTER);
        ClickHelper.clickElement(By.xpath("//h3[contains(text(), 'QA Automation')]"));
        assert DriverManager.getDriver().getTitle().equalsIgnoreCase(jsonObject.get("title").toString());
    }

}
