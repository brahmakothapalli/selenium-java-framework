package com.qababu;

import com.qababu.base.BaseTest;
import com.qababu.base.DriverManager;
import com.qababu.base.PageObjectManager;
import com.qababu.utility.DataProvider.TestDataProvider;
import com.qababu.utility.ExtentReport.ExtentTestManager;
import com.qababu.pagecctions.BookStorePageActions;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.util.Map;

@Listeners(com.qababu.utility.TestNGListeners.TestListener.class)
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
