package tests;

import base.BaseTest;
import base.DriverManager;
import helper.ClickHelper;
import helper.TextHelper;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.dataProvider.JsonDataProvider;
import utils.extentReport.ExtentReportManager;

@Listeners(utils.listeners.ReportTestListener.class)
public class GooglePageTests extends BaseTest {


    @Test(dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class, description = "GoogleSearchTestData")
    public void testSearchMyBlog(JSONObject jsonObject) throws InterruptedException {
        ExtentReportManager.logInfoDetails("Executing the test :: testSearchMyBlog");
        TextHelper.enterText(DriverManager.getDriver(), By.name("q"), jsonObject.get("searchKeyword").toString()+Keys.ENTER);
        ClickHelper.clickElement(DriverManager.getDriver(), By.xpath("//h3[contains(text(), 'Brahma')]"));
        assert DriverManager.getDriver().getTitle().equalsIgnoreCase(jsonObject.get("title").toString());
    }

}
