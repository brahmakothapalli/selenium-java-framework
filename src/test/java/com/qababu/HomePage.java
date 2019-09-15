package com.qababu;

import com.qababu.Base.BaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.qababu.Utility.TestNGListeners.TestListener.class)
public class HomePage extends BaseTest {

    private static final Logger logger = Logger.getLogger(HomePage.class.getSimpleName());

    @Test
    public void sampleTest(){

        logger.info("Starting the test");
    }

    @Test
    public void sampleTest2(){

        logger.info("Starting the test2");
    }


}
