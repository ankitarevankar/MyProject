package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException {
	FileInputStream fis= new FileInputStream("./testScriptData/testScriptData.xlsx");
    Workbook wb   =WorkbookFactory.create(fis); 
 
    String data =wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
    return data;
}
public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
	FileInputStream fis1= new FileInputStream("./testScriptData/testScriptData.xlsx");
	Workbook wb   =WorkbookFactory.create(fis1); 
    int rowcount = wb.getSheet(sheetName).getLastRowNum();
	return rowcount;
}
public void setDataIntoExcel(String sheetName,int rowNum,int cellNum,String data) throws EncryptedDocumentException, IOException {
	FileInputStream fis2= new FileInputStream("./testScriptData/testScriptData.xlsx");
	Workbook wb   =WorkbookFactory.create(fis2); 
Cell ce	=wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
	ce.setCellValue(data);
	FileOutputStream fos= new FileOutputStream("./testScriptData/testScriptData.xlsx");
	wb.write(fos);
	wb.close();
    
}
}
