package com.qababu.Utility.FileReader;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelFileReader {

    private static XSSFWorkbook excelWorkBook;
    private static XSSFSheet excelSheet;
    private static XSSFRow excelRow;
    private static XSSFCell excelCell;


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

    public static int getRowCount(XSSFSheet sheet){

        return sheet.getLastRowNum();
    }

    public static String getCellData(XSSFSheet sheetName, int rowNum, int colNum){

        int rowCount = getRowCount(sheetName);


        for(int i=0; i<rowCount; i++){
            System.out.println("In Progress");
            // =

        }

        return null;
    }


}
