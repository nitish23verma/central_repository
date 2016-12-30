package com.rashmi;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
//Scenario 1

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelData{
 // Create a method to read data from Excel Sheet
 public String getExcelData(String SheetName,int rowno,int colno) throws EncryptedDocumentException, InvalidFormatException, IOException {
	  String fp="./data/tdata.xlsx";
	  FileInputStream fis = new FileInputStream(fp);
	  Workbook wb = WorkbookFactory.create(fis);
	  Sheet sh = wb.getSheet("test");
	  Row r = sh.getRow(rowno);
	  
	  String data= r.getCell(1).getStringCellValue(); // read data from second column
	  return data;
	  
	  }

	  
 }

