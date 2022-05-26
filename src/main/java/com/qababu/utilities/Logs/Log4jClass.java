package com.qababu.utilities.Logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jClass {

    private void Log4jLog4jClass() {
        String log4jXmlPath = System.getProperty("user.dir")+"/src/main/resources/Log4j2.xml";
        System.setProperty("logoutputpath", System.getProperty("user.dir"));
        System.setProperty("log4j.configurationFile", log4jXmlPath);
    }

    /*private static Logger logger = LogManager.getLogger(Log4jClass.class.getName());

    public static void startTestCase(String testCaseName){
        logger.info("******************************************************");
        logger.info("$$$$$$$$$$$$$$ "+testCaseName+" $$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("******************************************************");
    }

    public static void endTestCase(String testCaseName){
        logger.info("***********       "+"E----N----D"+"   ****************");
    }*/



}
