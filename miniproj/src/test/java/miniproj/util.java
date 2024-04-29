package miniproj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class util {
	

	

	

		public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook wb;
		public static XSSFSheet ws;
		public static XSSFRow row;
		public static XSSFCell cell;
		public static CellStyle style;
		private static XSSFWorkbook workbook;   
		
		public static String readExcelData() throws IOException {

			// TODO Auto-generated method stub

			FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\input.xlsx");


			workbook = new XSSFWorkbook(file);

			XSSFSheet sheet=workbook.getSheet("Sheet1");

			XSSFRow row=sheet.getRow(0);

			XSSFCell cell=row.getCell(0);

			String data=cell.toString();

			return data;
		}

		public static void setCellData(String xlsheet,int rownum,int colnum,String data) throws IOException
		{
		fi=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\OP-DATA.xlsx");
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			if(row==null) {
				row=ws.createRow(rownum);
			}
			cell=row.createCell(colnum);
			cell.setCellValue(data);
			ws.autoSizeColumn(colnum);
			fo=new FileOutputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\OP-DATA.xlsx");
			wb.write(fo);		
			wb.close();
			fi.close();
			fo.close();
					
		}
		public static void rmvExcel() throws IOException {
			fi=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\OP-DATA.xlsx");
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheetAt(0);
			Iterator<Row> rows=ws.iterator();
			while(rows.hasNext()) {
				rows.next();
				rows.remove();
			}
		}

	}




