package learnJava;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datadriven {
	
	public static void main(String args[]) throws IOException{

	       InputStream ExcelFileToRead = new FileInputStream("C://work/Test.xlsx");
	       XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);

	       XSSFWorkbook test = new XSSFWorkbook();

	       XSSFSheet sheet = wb.getSheetAt(0);
	       XSSFRow row;
	       XSSFCell cell;

	       Iterator rows = sheet.rowIterator();

	       while (rows.hasNext())
	       {
	           row=(XSSFRow) rows.next();
	           Iterator cells = row.cellIterator();
	           while (cells.hasNext())
	           {
	               cell=(XSSFCell) cells.next();

	                   System.out.print(cell.getNumericCellValue());
	           }
	           System.out.println();
	       }

	   }
	}
	class ReadWriteExcelFile {

	   public void readXLSFile() throws IOException {
	       InputStream ExcelFileToRead = new FileInputStream("C://Work//Test.xlsx");
	       HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

	       HSSFSheet sheet = wb.getSheetAt(0);
	       HSSFRow row;
	       HSSFCell cell;

	       Iterator rows = sheet.rowIterator();

	       while (rows.hasNext()) {
	           row = (HSSFRow) rows.next();
	           Iterator cells = row.cellIterator();

	           while (cells.hasNext()) {
	               cell = (HSSFCell) cells.next();

	               System.out.print(cell.getStringCellValue() + " ");
	           }
	           System.out.println();
	       }

	   }
}
