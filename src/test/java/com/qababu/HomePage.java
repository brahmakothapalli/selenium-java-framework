package com.qababu;

import com.qababu.Base.BaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class HomePage extends BaseTest {

    private static final Logger logger = Logger.getLogger(HomePage.class.getSimpleName());

    @Test
    public void sampleTest(){
        logger.info("Starting the test");
    }
}
