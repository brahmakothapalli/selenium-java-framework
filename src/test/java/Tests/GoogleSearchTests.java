package Tests;

import Utils.DataProvider.JsonDataProvider;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoogleSearchTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class, description = "GoogleSearchTestData")
    public void testSearchMyBlog(JSONObject jsonObject){
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys(jsonObject.get("searchKeyword").toString(), Keys.ENTER);
        driver.findElement(By.xpath("//h3[contains(text(), 'Brahma')]")).click();
        assert driver.getTitle().equalsIgnoreCase(jsonObject.get("title").toString());
        driver.quit();
    }

}
