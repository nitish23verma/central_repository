package com.chintu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadXlsx {
	
	public void readXLSXFile(String fileName,WebDriver driver) {
		InputStream XlsxFileToRead = null;
		XSSFWorkbook workbook = null;
		try {
			XlsxFileToRead = new FileInputStream(fileName);
			
			//Getting the workbook instance for xlsx file
			workbook = new XSSFWorkbook(XlsxFileToRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//getting the first sheet from the workbook using sheet name. 
		// We can also pass the index of the sheet which starts from '0'.
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		XSSFRow row;
		XSSFCell cell;
		StringBuilder printString=new StringBuilder();
		//Iterating all the rows in the sheet
		Iterator rows = sheet.rowIterator();

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			
			//Iterating all the cells of the current row
			Iterator cells = row.cellIterator();

			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();

			
				
				if (!(cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) && row.getRowNum()>=1 ) {
					
					
					
					System.out.print(cell.getStringCellValue() + " ");
					WebElement element1 = driver.findElement(By.id("lst-ib"));
					 Date currentDate = new Date();  
	
				  

				
					printString.append(cell.getStringCellValue());
					printString.append( DateFormat.getTimeInstance(DateFormat.LONG).format(currentDate));
					
					//System.out.println(printString.toString());
					element1.sendKeys(printString);
			//MAking the thread sleep to get some time to view result/do operations and to get unique timing
					try {
						Thread.currentThread().sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Clear current data
					element1.clear();
					printString=new StringBuilder() ;
				}
			}
			
			try {
				XlsxFileToRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

public static void main(String[] args) {
// declaration and instantiation of objects/variables
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\Chintu\\Downloads\\Compressed\\chromedriver.exe");
//WebDriver driver =new ChromeDriver();

WebDriver driver=new FirefoxDriver();
String baseUrl = "https://www.google.com";
//String expectedTitle = "Welcome: Mercury Tours";
//String actualTitle = "";

// launch Firefox and direct it to the Base URL
driver.get(baseUrl);

//Get data from Excel

ReadXlsx readXlsx = new ReadXlsx();
readXlsx.readXLSXFile("C:\\Users\\Chintu\\Desktop\\testXlsxRead.xlsx",driver);	
//close driver

driver.quit();

}

}
