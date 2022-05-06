package com.qababu;

import com.qababu.Base.BaseTest;
import com.qababu.Base.DriverManager;
import com.qababu.Base.PageObjectManager;
import com.qababu.Utility.DataProvider.TestDataProvider;
import com.qababu.Utility.ExtentReport.ExtentTestManager;
import com.qababu.pagecctions.BookStorePageActions;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.util.Map;

@Listeners(com.qababu.Utility.TestNGListeners.TestListener.class)
public class BookStorePageTests extends BaseTest {

    private static final Logger logger = Logger.getLogger(BookStorePageTests.class.getSimpleName());

    private BookStorePageActions homePageActions;

    @BeforeClass
    public void beforeClassTasks() {
        ExtentTestManager.startTestSuite("BookStorePageTests");
        homePageActions = PageObjectManager.getHomePageActions();
    }


    @Test(dataProvider = "ask-me", dataProviderClass = TestDataProvider.class, description = "testData")
    public void invalidLoginValidationTest(Map<String, String> map) {
        logger.info("Starting invalidLoginValidationTest");
        homePageActions.loginAction(DriverManager.getDriver(), map.get("uname"), map.get("pwd"));
        String text = homePageActions.getLoginErrorText(DriverManager.getDriver());
    }

    @Test
    public void getCurrentCurlTest() {
        logger.info("Starting the getCurrentCurlTest");
    }

    @Test
    public void sampleTest2() {
        logger.info("Starting the test2");
    }

    @Test
    public void sampleTest3() {
        logger.info("Starting the test3");
    }




}
