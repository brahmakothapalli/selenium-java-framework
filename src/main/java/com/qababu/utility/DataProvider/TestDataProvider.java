package com.qababu.utility.DataProvider;

import com.qababu.enums.ConstantVariables;
import com.qababu.utility.FileReader.ExcelFileReader;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestDataProvider {

    private static final Logger logger = Logger.getLogger(TestDataProvider.class.getSimpleName());

    private static String testDateFilePath;

    @DataProvider(name = "ask-me")
    public static Object[][] dataProvider(Method method) {

        testDateFilePath = System.getProperty("user.dir") + ConstantVariables.TEST_DATA_FILE_PATH;

        String testName = method.getName();

        Test test = method.getAnnotation(Test.class);

        String dataSheetName = test.description();

        Object[][] data = null;

        try{

            data = getTestData(dataSheetName, testName);

        }catch (Exception e){

            logger.error("Failed to get the test data for test script -"+testName+ "in :: dataProvider");
        }

        return data;
    }

    private static Object[][] getTestData(String sheetName, String testName) {


        XSSFSheet currentDataSheet = ExcelFileReader.getSheet(testDateFilePath, sheetName);

        int currentTestRowNum = ExcelFileReader.getCurrentTestRowNum(currentDataSheet, testName);

        logger.info("Current tes script row number: " + currentTestRowNum);

        if (currentTestRowNum != -1) {

            int firstDataSetRowNum = currentTestRowNum + 2;

            int[] testDataSetsCount = ExcelFileReader.getTestDataSetsCount(currentDataSheet, currentTestRowNum);

            int totalTestDataRows = testDataSetsCount[0];

            logger.info("Total test data sets of test script " + testName + " are - " + totalTestDataRows);

            int enabledTestDataRows = testDataSetsCount[1];

            logger.info("Enabled test data sets of test script " + testName + " are - " + enabledTestDataRows);

            int lastDataSetRowNum = currentTestRowNum + totalTestDataRows + 1;

            int parametersCount = currentDataSheet.getRow(currentTestRowNum + 1).getLastCellNum();

            Object[][] excelData = new Object[enabledTestDataRows][1];

            int set = 0;

            for (int r = firstDataSetRowNum, k = 0; r <= lastDataSetRowNum && k < totalTestDataRows; r++, k++) {

                Map<Object, Object> dataMap = new HashMap<>();

                if (ExcelFileReader.getCellData(currentDataSheet, r, 0).equals("Y")) {

                    for (int c = 0; c < parametersCount; c++) {

                        dataMap.put(ExcelFileReader.getCellData(currentDataSheet, firstDataSetRowNum - 1, c + 1), ExcelFileReader.getCellData(currentDataSheet, r, c + 1));

                    }

                }

                excelData[set][0] = dataMap;

                set++;
            }
            return excelData;
        } else {

            logger.warn("Test data is not present for the test script - " + testName);

            return new Object[0][];
        }

    }
}
