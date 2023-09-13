package utils.fileReader;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class ExcelDataReader {

    private static Logger logger = Logger.getLogger(ExcelDataReader.class);
    private static XSSFWorkbook excelWorkbook;

    private ExcelDataReader() {

    }

    public static XSSFSheet getExcelSheet(String filePath, String sheetName) {

        logger.info("Returning the excel sheet in:: getExcelSheet");

        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            excelWorkbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            logger.error("Failed to return the excel sheet in:: getExcelSheet" + e);
        }
        return excelWorkbook.getSheet(sheetName);

    }

    public static int getRowCount(XSSFSheet excelSheet) {

        logger.info("Returning the total row count of the sheet " + excelSheet + "in:: getRowCount");
        try {
            return excelSheet.getLastRowNum();
        } catch (Exception e) {
            logger.error("Returning the total row count of the sheet " + excelSheet + "in:: getRowCount" + e);
            return 0;
        }
    }

    /**
     * This method returns row number of the currently executing test case
     *
     * @param testName - test script name
     * @return currentTestRowNum
     * @author brahma.kottapalli
     */

    public static int getCurrentTestCaseRow(XSSFSheet excelSheet, String testName) {

        logger.info("Getting the current test case row number of the test script " + testName + " in:: getCurrentTestCaseRow");

        int rCount = excelSheet.getLastRowNum();

        logger.info("Total number of rows in the data sheet :: " + rCount);

        int currentTestRowNum = -1;

        for (int i = 0; i <= rCount; i++) {

            XSSFRow excelRow = excelSheet.getRow(i);

            if (excelRow != null) {

                XSSFCell excelCell = excelRow.getCell(0);

                if (excelCell != null && (excelCell.getCellType() == CellType.STRING)) {

                    String cellValue = excelCell.getStringCellValue();

                    if (cellValue.equals(testName)) {

                        currentTestRowNum = i;

                        break;
                    }
                }

            }
        }

        logger.info("Current test case row number :: " + currentTestRowNum);

        if (currentTestRowNum == -1) {

            logger.warn("Test Data is not present in test data sheet");
        }

        return currentTestRowNum;
    }


    public static int[] getTestDataSetsCount(XSSFSheet excelSheet, int currentTestRowNum) {

        logger.info("Returning the total number of test data sets in:: getTestDataSetsCount");

        int totalDataSetsCount = 0;

        int enabledDataSetsCount = 0;

        int[] dataSetsCounts = new int[2];

        XSSFRow row;

        if (currentTestRowNum != -1) {

            currentTestRowNum = currentTestRowNum + 2;

            row = excelSheet.getRow(currentTestRowNum);

            while (row != null) {

                if (Objects.equals(getCellData(excelSheet, currentTestRowNum, 0), "Y")) {

                    enabledDataSetsCount++;
                }
                totalDataSetsCount++;
                currentTestRowNum++;
                row = excelSheet.getRow(currentTestRowNum);
            }
            dataSetsCounts[0] = totalDataSetsCount;

            dataSetsCounts[1] = enabledDataSetsCount;

            logger.info("The total  number of test data sets are " + totalDataSetsCount + " :: getTestDataSetsCount");

            logger.info("The total  number of test data sets enabled are " + enabledDataSetsCount + " :: getTestDataSetsCount");
        } else {

            logger.warn("Test data is not present for the test case in the test data sheet");
        }

        return dataSetsCounts;
    }


    public static String getCellData(XSSFSheet excelSheet, int rowNum, int colNum) {

        XSSFRow row = excelSheet.getRow(rowNum);

        String cellValue;

        if (row != null) {

            XSSFCell cell = row.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            if (cell != null && (cell.getCellType() == CellType.STRING)) {

                return cell.getStringCellValue();

            } else if (cell != null && (cell.getCellType() == CellType.NUMERIC)) {

                cellValue = String.valueOf(cell.getNumericCellValue()).replaceAll("[0]*$", "").replaceAll("\\.$", "");

                return cellValue;
            }
        }

        return null;
    }

    public static int getMatchingRowNum(XSSFSheet sheet, String testName) {

        logger.info("Returning the matched rate number for" + testName + "in :: getMatchingRowNum");

        int rCount = sheet.getLastRowNum();

        logger.info("Total number of rows in the data sheet:: " + rCount);

        int matchedRowNumber = 0;

        for (int i = 0; i <= rCount; i++) {

            XSSFRow excelRow = sheet.getRow(i);

            if (excelRow != null) {

                XSSFCell excelCell = excelRow.getCell(0);

                if (excelCell != null && (excelCell.getCellType() == CellType.STRING)) {

                    String cellValue = excelCell.getStringCellValue();

                    if (cellValue.equals(testName)) {

                        matchedRowNumber = i;
                        break;
                    }
                }

            }
        }
        logger.info("The rate row number is :: " + matchedRowNumber);
        return matchedRowNumber;
    }
}
