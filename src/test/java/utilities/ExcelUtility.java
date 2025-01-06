package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
		
		public  FileInputStream fi;
		public  FileOutputStream fo;
		public  XSSFWorkbook workbook;
		public  XSSFSheet sheet;
		public  XSSFRow row;
		public  XSSFCell cell;
		public  CellStyle style;
		public String path;
		
		
		public ExcelUtility(String path) {
			this.path=path;
		}
		
		public  int getRowCount(String sheetName) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			int rowcount =sheet.getLastRowNum();
			fi.close();
			workbook.close();
			
			return rowcount;
		}
		
		
		public  int getCellCount(String sheetName,int rownum) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			int cellcount=row.getLastCellNum();
			workbook.close();
			fi.close();
			return cellcount;
		}
		
		
		public String getCellData(String sheetName,int rownum,int column) throws IOException
		
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			cell=row.getCell(column);
			
			String data;
			try {
				data=cell.toString();
			}
			catch (Exception e)
			{
				data="";
			}
			workbook.close();
			fi.close();
			return data;
			
			
		}
		
			public  void setCellData(String sheetName,int rownum,int column,String data) throws IOException 
			{
				fi= new FileInputStream(path);
				workbook=new XSSFWorkbook(fi);
				sheet=workbook.getSheet(sheetName);
				row=sheet.getRow(rownum);
				
				cell=row.createCell(column);
				cell.setCellValue(data);
				fo=new FileOutputStream(path);
				workbook.write(fo);
				workbook.close();
				fi.close();
				fo.close();
				
			}
			
		public  void fillGreenColor(String sheetName,int rownum,int column) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			cell=row.getCell(column);
			
			style=workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			cell.setCellStyle(style);
			fo=new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		}
		
		public  void fillRedColor(String sheetName,int rownum,int column) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			cell=row.getCell(column);
			
			style=workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			cell.setCellStyle(style);
			fo=new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		}
		
	}



