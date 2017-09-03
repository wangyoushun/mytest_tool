package cn.six.mypoi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelUtils {
	private final static Log log = LogFactory.getLog(ExcelUtils.class);
	private final static int starSheet = 0;
	private static List<String> colList = new ArrayList<String>();

	public static List<String> readExcel(String path) throws Exception {
		if (path == null || "".equals(path))
			return null;

		File file = new File(path);
		if (!file.exists())
			return null;
		Workbook work = null;
		InputStream in = new FileInputStream(file);
		List<String> rowList = new ArrayList<String>();
		String suffix = path.substring(path.lastIndexOf(".") + 1);
		try {
			work = createWork(work, in, suffix);
			Sheet sheet = work.getSheetAt(starSheet);
			int rows = sheet.getLastRowNum() + 1;
			if (rows < 1)
				return null;

			String str = "";
			int cols = sheet.getRow(0).getPhysicalNumberOfCells();
			for (int i = 1; i < rows; i++) {
				str = "";
				Row row = sheet.getRow(i);
				for (int j = 0; j < cols; j++) {
					Cell cell = row.getCell(j);
					if(cell!=null){
						cell.setCellType(Cell.CELL_TYPE_STRING);
						str += cell.getStringCellValue() + ",";
					}else{
						str += ",";
					}
					
				}
				if (str.length() > 1)
					str = str.substring(0, str.length() - 1);

				rowList.add(str);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowList;
	}

	public static <E> List<E> readExcel(String path, Class<?> clazz,
			String[] excludeFiled) throws Exception {
		if (path == null || "".equals(path))
			return null;

		File file = new File(path);
		if (!file.exists())
			return null;

		Workbook work = null;
		InputStream in = new FileInputStream(file);
		List<E> list = new ArrayList<E>();
		String suffix = path.substring(path.lastIndexOf(".") + 1);
		System.out.println(suffix);
		try {
			work = createWork(work, in, suffix);
			Sheet sheet = work.getSheetAt(starSheet);
			int rows = sheet.getLastRowNum() + 1;
			if (rows < 1)
				return null;

			int cols = sheet.getRow(0).getPhysicalNumberOfCells();
			// headList = readHead(sheet, cols);
			setColums(clazz);
			for (int i = 1; i < rows; i++) {
				Row row = sheet.getRow(i);
				E obj = readRow(cols, row, excludeFiled, clazz);
				list.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	private static void setColums(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			colList.add(field.getName());
		}
	}

	@SuppressWarnings("unchecked")
	private static <E> E readRow(int cols, Row row, String[] excludeFiled,
			Class<?> clazz) throws Exception {
		String stringCellValue = "";

		List<String> asList = Arrays.asList(excludeFiled);
		E obj = (E) clazz.newInstance();
		for (int j = 0; j < cols; j++) {
			String name = colList.get(j);
			if (asList.contains(name)) {
				continue;
			}

			Cell cell = row.getCell(j);
			if (cell == null) {
				continue;
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			stringCellValue = cell.getStringCellValue();

			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method method : declaredMethods) {
				String methName = method.getName();
				String upperCase = name.substring(0, 1).toUpperCase();
				String substring = name.substring(1);
				if (methName.equals("set" + upperCase + substring)) {
					Class<?>[] parameterTypes = method.getParameterTypes();
					if (parameterTypes[0] == Integer.class) {
						method.invoke(obj, Integer.parseInt(stringCellValue));
					} else if (parameterTypes[0] == Long.class) {
						method.invoke(obj, Long.parseLong(stringCellValue));
					} else if (parameterTypes[0] == Double.class) {
						method.invoke(obj, Double.parseDouble(stringCellValue));
					} else if (parameterTypes[0] == Float.class) {
						method.invoke(obj, Float.parseFloat(stringCellValue));
					} else if (parameterTypes[0] == Date.class) {
						Date javaDate = DateUtil.getJavaDate(Double
								.parseDouble(stringCellValue));
						method.invoke(obj, javaDate);
					} else {
						method.invoke(obj, stringCellValue);
					}
				}
			}
		}
		return obj;
	}

	@SuppressWarnings("unused")
	private static List<String> readHead(Sheet sheet, int cols) {
		List<String> headList = new ArrayList<String>();
		Row row = sheet.getRow(0);
		for (int i = 0; i < cols; i++) {
			Cell cell = row.getCell(i);
			if (cell == null)
				continue;
			String stringCellValue = cell.getStringCellValue();
			if (stringCellValue.length() > 0) {
				headList.add(stringCellValue);
			}
		}
		return headList;
	}

	private static Workbook createWork(Workbook work, InputStream in,
			String suffix) throws IOException, Exception {
		if ("xlsx".equals(suffix)) {
			work = new XSSFWorkbook(in);
		} else if ("xls".equals(suffix)) {
			work = new HSSFWorkbook(in);
		} else {
			log.error("#error# ==> excel文件类型错误， 请检查");
			throw new Exception("文件类型错误");
		}
		return work;
	}
	
	//导出excel
	private static <E> OutputStream exportExcel(List<E> rsList, String[] titles, Class<?> clazz){
	
		
		
		return null;
	}
	
	
	

	
	
}
