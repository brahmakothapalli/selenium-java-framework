package com.qababu;

import com.qababu.Base.BaseTest;
import com.qababu.Base.DriverManager;
import com.qababu.Utility.DataProvider.TestDataProvider;
import com.qababu.pageactions.HomePageActions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Listeners(com.qababu.Utility.TestNGListeners.TestListener.class)
public class HomePage extends BaseTest {

    private static final Logger logger = Logger.getLogger(HomePage.class.getSimpleName());

    private HomePageActions homePageActions;
    //private DashPageActions dashPageActions;

    @BeforeClass
    public void beforeClassTasks() {
        //to create objects of actions classes
        homePageActions = new HomePageActions();
    }

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        DriverManager.getDriver().get("http://www.google.com");
    }

    @Test(dataProvider = "ask-me", dataProviderClass = TestDataProvider.class, description = "testdata")
    public void invalidLoginValidationTest(Map<String, String> map) {
        logger.info("Starting invalidLoginValidationTest");
        System.out.println(DriverManager.getDriver().getTitle());
        //homePageActions.loginAction(DriverManager.getDriver(), "abcnd", "11234");
        String text = DriverManager.getDriver().getTitle();
        System.out.println(map.get("username"));
        Assert.assertEquals(text, "Google");
    }

    @Test
    public void getCurrentCurlTest() {
        logger.info("Starting the getCurrentCurlTest");
        System.out.println(DriverManager.getDriver().getTitle());
        String text = DriverManager.getDriver().getCurrentUrl();
        System.out.println(text);
    }

    @Test
    public void sampleTest2() {
        logger.info("Starting the test2");
    }

    @Test
    public void sampleTest3() {
        logger.info("Starting the test3");
    }

    @AfterMethod
    public void closeBrowser() {

        DriverManager.getDriver().quit();
    }


}
