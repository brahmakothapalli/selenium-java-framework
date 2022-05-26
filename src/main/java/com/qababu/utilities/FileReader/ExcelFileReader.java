package com.qababu.utilities.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelFileReader {

    private static final Logger logger = LogManager.getLogger(ExcelFileReader.class.getSimpleName());


    private static XSSFWorkbook excelWorkBook;



    public static XSSFSheet getSheet(String filePath, String sheetName) {
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            excelWorkBook = new XSSFWorkbook(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelWorkBook.getSheet(sheetName);
    }

    public static int getRowCount(XSSFSheet sheet) {

        return sheet.getLastRowNum();
    }

    public static String getCellData(XSSFSheet excelSheet, int rowNum, int colNum) {

        XSSFRow row = excelSheet.getRow(rowNum);

        String cellValue = null;
        try {
            if (row != null) {

                XSSFCell cell = row.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                if (cell != null && (cell.getCellType() == CellType.STRING)) {

                    cellValue = cell.getStringCellValue();

                } else if (cell != null && (cell.getCellType() == CellType.NUMERIC)) {

                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cellValue;
    }

    public static int getCurrentTestRowNum(XSSFSheet sheet, String testName) {

        int currentTestRowNum = -1;

        int totalRowCount = getRowCount(sheet);
        try {
            for (int i = 0; i < totalRowCount; i++) {

                XSSFRow row = sheet.getRow(i);

                if (row != null) {

                    XSSFCell cell = row.getCell(0);

                    if ((cell != null) && (cell.getCellType() == CellType.STRING)) {

                        String cellValue = cell.getStringCellValue();

                        if (cellValue.equals(testName)) {
                            currentTestRowNum = i;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentTestRowNum;
    }

    public static int[] getTestDataSetsCount(XSSFSheet excelSheet, int currentTestRowNum) {

        int totalDataSetsCount = 0;

        int enabledDataSetsCount = 0;

        int[] dataSetsCount = new int[2];

        XSSFRow row;

        if (currentTestRowNum != -1) {

            currentTestRowNum = currentTestRowNum + 2;

            row = excelSheet.getRow(currentTestRowNum);

            while (row != null) {

                if (getCellData(excelSheet, currentTestRowNum, 0).equals("Y")) {
                    enabledDataSetsCount += 1;
                }
                totalDataSetsCount += 1;
                currentTestRowNum += 1;
                row = excelSheet.getRow(currentTestRowNum);
            }
            dataSetsCount[0] =totalDataSetsCount;
            dataSetsCount[1] = enabledDataSetsCount;
        }else {
            logger.info("The test data is not present in the TestData file");
        }
        return dataSetsCount;
    }

}
