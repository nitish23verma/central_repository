package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class StoreData {
	public static void toExcel(String filepath, String sheetName, int rIndex, int cIndex, String data) {
		try {
			File f = new File("./data/datatdata.xlsx");
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet st = wb.getSheet(sheetName);
			Row r = st.getRow(rIndex);
			Cell c = r.getCell(cIndex);
			c.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(f);
			wb.write(fos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void toProperties(String filepath, String key, String value, String comments) {
		try {
			File f = new File(filepath);
			FileInputStream fis = new FileInputStream(f);
			Properties prop = new Properties();
			prop.load(fis);
			prop.setProperty(key, value);
			FileOutputStream fos = new FileOutputStream(f);
			prop.store(fos, comments);
		} catch (Exception e) {

		}

	}

}
