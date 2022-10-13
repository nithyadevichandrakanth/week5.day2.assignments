package week5.day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.cucumber.java.sl.Ce;

public class ServiceNowExcel {

	public static String[] [] readExcelData(String excelName) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook("data/"+excelName+".xlsx");
		XSSFSheet worksheet = workbook.getSheet("Sheet1");
		int lastRowNum = worksheet.getLastRowNum();
		short lastCellNum = worksheet.getRow(0).getLastCellNum();
		String[][] data = new String[lastRowNum][lastCellNum];
		
		for (int i = 1; i <= lastRowNum; i++) {
			XSSFRow row = worksheet.getRow(i);
			
			for (int j = 0; j <lastCellNum; j++) {
				XSSFCell cell = row.getCell(j);
				String stringCellValue = cell.getStringCellValue();
				System.out.println(stringCellValue);
				data[i-1][j]=stringCellValue;
			}
			System.out.println("###########################");
		}
		
		workbook.close();
		return data;

	}

}
