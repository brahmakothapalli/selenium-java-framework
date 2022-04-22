package Tests;

import Base.BaseTest;
import Base.DriverManager;
import Base.PageObjectManager;
import PageActions.SnapLoginPageActions;
import Utils.DataProvider.TestDataProvider;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class SnapLoginPageTests extends BaseTest {

    private static Logger logger = Logger.getLogger(SnapLoginPageTests.class);

    private SnapLoginPageActions snapLoginPageActions;

    @BeforeClass(alwaysRun = true)
    public void beforeClassSetUp() {
        classLogger = extentReport.createTest("SnapLoginPageTests");
        logger.info("Creating object for SnapLoginPageActions :: beforeClassSetUp");
        snapLoginPageActions = PageObjectManager.getSnapLoginPageActions();
    }

    @Test(dataProvider = "ask-me", dataProviderClass = TestDataProvider.class, description = "LoginPageData")
    public void ValidateLoginFunctionalityTest(Map<String, String> map) throws InterruptedException {

        logger.info("Executing the test ValidateLoginFunctionalityTest");
        snapLoginPageActions.enterLoginCredentials(DriverManager.getDriver(), map.get("userName"), map.get("password"), map.get("answer"));

        switch (map.get("userType")) {
            case "valid":
                String currentUrl = DriverManager.getDriver().getCurrentUrl();
                logger.info("Current Url: " + currentUrl);
                Assert.assertTrue(currentUrl.contains("dashboard"));
                break;
            case "invalid":
//                String expected = loginPageActions.getLoginErrorText(DriverManager.getDriver());
//                logger.info("Actual error text Url: " + expected);
//                Assert.assertEquals(map.get("errorText"), expected);
                DriverManager.getDriver().navigate().refresh();
                break;
        }

    }
}
