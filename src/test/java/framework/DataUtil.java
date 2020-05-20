package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtil {
	
	private XSSFWorkbook getWorkBook(String filePath) {
		try {
			if (Utilities.isFileExists(filePath)) {			 
				return new XSSFWorkbook(new FileInputStream(filePath));
			} else {
				return null;
			}
			
		} catch (IOException e) {
			return null;		
		}

	}
	
	private XSSFSheet getSheet(XSSFWorkbook workbook, String sheetName) {
		
		int sheetIndex = workbook.getSheetIndex(sheetName);
		
		if (sheetIndex < 0) {
			return null;
		} else {
			return workbook.getSheet(sheetName);
		}
		
	}
	
	private ArrayList<String> getAllColumns(XSSFSheet sheet) {
		ArrayList<String> allColumns = new ArrayList<>();
		
		XSSFRow row = sheet.getRow(0);			
		int totalColumns = row.getLastCellNum();			
		for (int colIndex = 0; colIndex <= totalColumns-1; colIndex++) {
			allColumns.add(row.getCell(colIndex).toString().toUpperCase());
		}		
		
		return allColumns;
		
	}
	
	
	
	public Object[][] get_data_from_excel_to_array(String filePath, String sheetName) {
		Object[][] tcData;
		XSSFWorkbook wb = getWorkBook(filePath);
		if (wb != null) {
			XSSFSheet sheet = getSheet(wb, sheetName);
			int totalRows = sheet.getLastRowNum();			
			tcData = new Object[totalRows+1][];
			
			for (int rowIndex = 0; rowIndex <= totalRows; rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);
				int totalColumns = row.getLastCellNum();
				tcData[rowIndex] = new Object[totalColumns];
				
				for (int colIndex = 0; colIndex <= totalColumns-1; colIndex++) {
						XSSFCell cell = row.getCell(colIndex);
						if (cell != null) {
							tcData[rowIndex][colIndex] = cell.toString();
						} else {
							tcData[rowIndex][colIndex] = "";
						}
						
						
				}
				
			}
			
			try {
				wb.close();
			} catch (IOException e) {
			}
			
			return tcData;
		} else {
			System.out.println("data file : " + filePath+ " does not exist.");
		}
		
		return null;
		
	}
	
	
	
}
