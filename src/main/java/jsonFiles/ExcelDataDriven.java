package jsonFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven {
	
	public ArrayList<String> getExcelData(String excelname,String sheetname,String reqColumn,String testcasename) throws IOException
	{
		ArrayList<String> a=new ArrayList<String>();
		
		FileInputStream fis=new FileInputStream("src\\main\\java\\jsonFiles\\"+excelname+".xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
		int sheets=workbook.getNumberOfSheets();
		
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetname))
			{
				XSSFSheet sheet= workbook.getSheetAt(i);
				
				Iterator<Row> rows=sheet.iterator();
				Row firstrow=rows.next();
				Iterator<Cell> cells=firstrow.cellIterator();
				int k=0;
				int coloumn = 0;
				while(cells.hasNext())
				{
					Cell ce=cells.next();
					if(ce.getStringCellValue().equalsIgnoreCase(reqColumn))
					{
						coloumn=k;
					}
					k++;
				}
				while(rows.hasNext())
				{
					Row row=rows.next();
					if(row.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcasename))
					{
						Iterator<Cell> c=row.cellIterator();
						while(c.hasNext())
						{
							Cell cv=c.next();
							if(cv.getCellTypeEnum()==CellType.STRING)
							{
							a.add(cv.getStringCellValue());
							}
							else
							{
								a.add(NumberToTextConverter.toText(cv.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return a;
	}
	
	
}
