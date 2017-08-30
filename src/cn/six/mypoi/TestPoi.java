package cn.six.mypoi;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import cn.six.utils.DateUtil;
import cn.six.utils.StringTool;

public class TestPoi<V> {

	String path = "D:\\tmp\\file\\";

	// 导出excle表格测试
	@Test
	public void testExport() {
		Random random = new Random();
		int length = 9;
		List<ExcelPerson> personList = new ArrayList<ExcelPerson>();

		for (int i = 0; i < 10; i++) {
			ExcelPerson excelPerson = new ExcelPerson();
			excelPerson.setName(StringTool.generateRandomName());
			excelPerson.setAge(random.nextInt(100));
			excelPerson.setFligtDate(new Date());
			excelPerson.setAbc("abc-" + i);
			excelPerson.setAbc1("abc1-" + i);
			excelPerson.setAbc2("abc2-" + i);
			excelPerson.setAbc3("abc3-" + i);
			excelPerson.setAbc4("abc4-" + i);
			excelPerson.setAbc5("abc5-" + i);
			personList.add(excelPerson);
		}

		String[] title = { "name", "age", "date", "abc", "abc1", "abc2",
				"abc3", "abc5", "abc4" };

		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("new sheet");
		Row headRow = sheet.createRow(0);
		Cell createCell = headRow.createCell(0);
		createCell.setCellValue("标题头");

		// 设置单元格风格，居中对齐.
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置字体:
		Font font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs.setFont(font);// 要用到的字体格式

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, length - 1));// 横向：合并第一行的第2列到第4列
		createCell.setCellStyle(cs);

		CellStyle csCenter = wb.createCellStyle();
		
		csCenter.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置标题
		Row titleRow = sheet.createRow(1);
		for (int i = 0; i < title.length; i++) {
			Cell cell = titleRow.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(csCenter);
			sheet.setColumnWidth(i, title[i].getBytes().length*3*256+1000);
		}

		List<String> list = new ArrayList<String>();
		list.add("name");
		list.add("age");
		list.add("fligtDate");
		list.add("abc");
		list.add("abc1");
		list.add("abc2");
		list.add("abc3");
		list.add("abc5");
		list.add("abc4");

		Class<ExcelPerson> clazz = ExcelPerson.class;
		// 设置行数
		for (int i = 0; i < personList.size(); i++) {
			Row row = sheet.createRow(i + 2);
			ExcelPerson person = personList.get(i);
			for (int j = 0; j < list.size(); j++) {
				try {
					Method method = clazz.getMethod("get"+StringTool.firstToUp(list.get(j)));
					String name = method.invoke(person)+"";
					if("fligtDate".equals(list.get(j))){
						name = DateUtil.Date();
					}
					Cell cell = row.createCell(j);
					cell.setCellValue(name);
					cell.setCellStyle(csCenter);
					sheet.setColumnWidth(j, name.getBytes().length*3*256+1000);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}

		try {
			FileOutputStream fileOut = new FileOutputStream("D://workbook.xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test2Map() {
		List<String> list = new ArrayList<String>();
		list.add("name");
		list.add("age");
		list.add("abc");
		list.add("abc1");
		list.add("abc2");
		list.add("abc3");
		list.add("abc4");
		list.add("abc5");
	}

	@Test
	public void testClazz() throws Exception {
		Class<ExcelPerson> clazz = ExcelPerson.class;
		ExcelPerson excelPerson = new ExcelPerson();
		excelPerson.setName("32323");
		Method method = clazz.getMethod("getName");
		String name = (String) method.invoke(excelPerson);
		System.out.println(name);

	}

	// test 以字符串形式读取excel数据
	@Test
	public void testReadByString() throws Exception {
		path += "001.xlsx";
		List<String> readExcel = ExcelUtils.readExcel(path);
		for (String string : readExcel) {
			System.out.println(string);
		}
	}

	// test 用实体读取excel数据
	@Test
	public void testReadByEntity() throws Exception {
		path += "001.xlsx";
		List<User> user = ExcelUtils.readExcel(path, User.class, new String[0]);
		System.out.println(user);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		String path = "d://tmp//1.xlsx";
		FileInputStream in = new FileInputStream(new File(path));
		XSSFWorkbook xw = new XSSFWorkbook(in);
		XSSFSheet sheet = xw.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		System.out.println(physicalNumberOfRows);

		for (int i = 0; i <= lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			String readRow = readRow(row, physicalNumberOfRows);
			System.out.println(readRow);
		}

		in.close();
	}

	private static String readRow(XSSFRow row, int physicalNumberOfRows) {
		String s = "";
		for (int i = 0; i < physicalNumberOfRows - 1; i++) {
			XSSFCell cell = row.getCell(i);
			if (cell == null)
				break;
			cell.setCellType(1);
			String stringCellValue = String.valueOf(cell
					.getRichStringCellValue());
			s += stringCellValue;
		}
		return s;
	}

}
