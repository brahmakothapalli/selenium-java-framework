package Tests;

import base.BaseTest;
import base.DriverManager;
import helper.TextHelper;
import Utils.DataProvider.JsonDataProvider;
import Utils.ExtentReport.ExtentReportManager;
import Utils.TestNGListeners.ReportTestListener;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ReportTestListener.class)
public class GoogleSearchTests extends BaseTest {


    @Test(dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class, description = "GoogleSearchTestData")
    public void testSearchMyBlog(JSONObject jsonObject){
        ExtentReportManager.logInfoDetails("Executing the test :: testSearchMyBlog");
        DriverManager.getDriver().get("http://www.google.com");
        TextHelper.enterText(DriverManager.getDriver(), By.name("q"), jsonObject.get("searchKeyword").toString()+Keys.ENTER);
        DriverManager.getDriver().findElement(By.xpath("//h3[contains(text(), 'Brahma')]")).click();
        assert DriverManager.getDriver().getTitle().equalsIgnoreCase(jsonObject.get("title").toString());
    }

}
