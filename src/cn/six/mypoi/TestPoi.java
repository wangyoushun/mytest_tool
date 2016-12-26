package cn.six.mypoi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPoi {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		String path = "d://tmp//1.xlsx";
		FileInputStream in = new FileInputStream(new File(path));
		XSSFWorkbook xw = new XSSFWorkbook(in);
		XSSFSheet sheet = xw.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		System.out.println(physicalNumberOfRows);
		
		for(int i=0; i<=lastRowNum; i++){
			XSSFRow row = sheet.getRow(i);
			String readRow = readRow(row, physicalNumberOfRows);
			System.out.println(readRow);
		}
		
		in.close();
	}

	private static String readRow(XSSFRow row, int physicalNumberOfRows) {
		String s = "";
		for(int i=0; i<physicalNumberOfRows-1; i++){
			XSSFCell cell = row.getCell(i);
			if(cell==null)
				break;
			cell.setCellType(1);
			 String stringCellValue = String.valueOf(cell.getRichStringCellValue());
			s += stringCellValue;
		}
		return s;
	}
	
	@Test
	public void test1() throws Exception{
		String path = "d://tmp//1.xlsx";
		String[] names = {"abc1","abc2","abc3","abc4","abc5"};
		List<ExcelPerson> readExcel = ExcelUtils.readExcel(path, ExcelPerson.class,names);
		System.out.println(readExcel);
	}
}
