package com.qa.opencart.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static final String TEST_DATA_SHEET_PATH ="./src/test/resources/TestData/openCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheetRef;
	
	public static Object[][] getTestData(String sheetName) {
		
		Object data[][] = null;
		try {
				FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
				book = WorkbookFactory.create(ip);
				sheetRef = book.getSheet(sheetName);
				
		}catch(FileNotFoundException f1 ) {
			f1.printStackTrace();
		}catch(IOException fe) {
			fe.printStackTrace();
		}catch(InvalidFormatException e2) {
			e2.printStackTrace();
		}
		
		data = new Object[sheetRef.getLastRowNum()][sheetRef.getRow(0).getLastCellNum()];
		
		for(int i=0;i<sheetRef.getLastRowNum();i++) {
			for(int j=0;j<sheetRef.getRow(0).getLastCellNum();j++) {
				data[i][j]=sheetRef.getRow(i+1).getCell(j).toString();
			}
		}
		
		return data;
		
	}

}
