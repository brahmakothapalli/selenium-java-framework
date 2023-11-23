package tests;

import base.BaseTest;
import base.DriverManager;
import helper.ClickHelper;
import helper.TextHelper;
import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.dataProvider.JsonDataProvider;
import utils.extentReport.ExtentReportManager;
import utils.listeners.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class GoogleSearchTests extends BaseTest {


    @Test(dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class)
    @Parameters("GoogleSearchTestData")
    public void testSearchMyBlog(JSONObject jsonObject) {
        ExtentReportManager.logInfoDetails("Executing the test :: testSearchMyBlog");
        DriverManager.getDriver().get("http://www.google.com");
        TextHelper.enterText(DriverManager.getDriver(), By.name("q"), jsonObject.get("searchKeyword").toString()+Keys.ENTER);
        ClickHelper.clickElement(By.xpath("//h3[contains(text(), 'QA Automation')]"));
        assert DriverManager.getDriver().getTitle().equalsIgnoreCase(jsonObject.get("title").toString());
    }

}
