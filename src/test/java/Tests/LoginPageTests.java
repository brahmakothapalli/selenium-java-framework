package Tests;

import Base.BaseTest;
import Base.PageObjectManager;
import PageActions.LoginPageActions;
import Utils.DataProvider.JsonDataProvider;
import Utils.DataProvider.TestDataProvider;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginPageTests extends BaseTest {

    private static final Logger logger = Logger.getLogger(LoginPageTests.class);

    private LoginPageActions loginPageActions;

    @BeforeClass(alwaysRun = true)
    public void beforeClassSetUp() {
        classLogger = extentReport.createTest("SnapLoginPageTests");
        logger.info("Creating object for LoginPageActions :: beforeClassSetUp");
        loginPageActions = PageObjectManager.getLoginPageActions();
    }

    @Test(dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class, description = "googleTestData")
    public void ValidateLoginFunctionalityTest(Map<String, String> map) {

        System.out.println("Test Case In Progress");
    }


}
