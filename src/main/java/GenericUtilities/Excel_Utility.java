package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Utility {
public String FetchDataFromExcelFile(String sheetname,int rowindex,int cellindex) throws EncryptedDocumentException, IOException
{
	//fetch the data from excelsheet
		FileInputStream efis=new FileInputStream("./src/test/resources/vtiger task.xlsx");
		
		Workbook wb= WorkbookFactory.create(efis);
		 Sheet sh = wb.getSheet(sheetname);

			Row r= sh.getRow(rowindex);
			
			Cell c=r.getCell(cellindex);
			String data=c.toString();
			return data;
}

public void WriteBackDataToExcel(String sheetname,int rowindex,int cellindex,String data) throws EncryptedDocumentException, IOException {

	//fetch the data from excelsheet
		FileInputStream efis=new FileInputStream("./src/test/resources/vtiger task.xlsx");
		
		Workbook wb= WorkbookFactory.create(efis);
		 Sheet sh = wb.getSheet(sheetname);

			Row r= sh.getRow(rowindex);
			
			Cell c=r.getCell(cellindex);
			c.setCellValue(data);
			FileOutputStream fos = new FileOutputStream("./src/test/resources/vtiger task.xlsx");
			wb.write(fos);
			wb.close();
		

}
}
