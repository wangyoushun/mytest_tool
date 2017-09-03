package cn.six.mypoi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFFont;
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


public class TestPoi {


	String path = "D:\\tmp\\file\\";

	// test 以字符串形式读取excel数据
	@SuppressWarnings("deprecation")
	@Test
	public void testReadByString3() throws Exception {
		path = "f://003.xlsx";
		String cityName = "广州	佛山	东莞	湛江	杭州	上海	绍兴	金华	深圳	中山	汕头	温州	宁波	义乌	惠州	茂名";
		String[] cityArray = cityName.split("	");
		List<String> readExcel = ExcelUtils.readExcel(path);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<City> cityList = new ArrayList<City>();
		for (String string : readExcel) {
			System.out.println(string);
			String[] split = string.split(",");
			Map<String, Object> map = new HashMap<String, Object>();

			String date = split[0];
			for (int i = 1; i < split.length; i++) {
				City city = new City();
				String str = split[i];
				if (str != null && !"".equals(str)) {
					map.put("city", cityArray[i - 1]);
					map.put("date", date);
					map.put("money", split[i]);
					list.add(map);
					// System.out.println(list);
					// System.out.println(map);

					city.setCity(cityArray[i - 1]);
					city.setYear(Integer.parseInt(date.substring(0, 4)));
					String string2 = date.split("年")[1];
					String substring = string2.substring(0, string2.length() - 1);
					city.setMonth(Integer.parseInt(substring));
					city.setMoney(Double.parseDouble(split[i]));
					cityList.add(city);
					System.out.println(city);
				}
			}

		}

		// System.out.println(cityList);
		for (City city : cityList) {
			// System.out.println(city);
			String str = "insert into city(city, year, month, money) values ('" + city.getCity() + "'," + city.getYear()
					+ "," + city.getMonth() + "," + city.getMoney() + ");";
			System.out.println(str);
		}

	}

	// test 以字符串形式读取excel数据
	@SuppressWarnings("deprecation")
	@Test
	public void testReadByString2() throws Exception {
		path = "f://002.xlsx";
		List<String> readExcel = ExcelUtils.readExcel(path);
		List<UserReport> list = new ArrayList<UserReport>();
		for (String string : readExcel) {
//			System.out.println(string);
			String[] split = string.split(",");
//			System.out.println(Arrays.toString(split));
			String date = split[0];
			UserReport userReport = new UserReport();

			Integer.parseInt(date.substring(0, 4));
			String string2 = date.split("年")[1];
			String substring = string2.substring(0, string2.length() - 1);
			userReport.setYear(Integer.parseInt(date.substring(0, 4)));
			userReport.setMonth(Integer.parseInt(substring));
			userReport.setAddBuyers(Integer.parseInt(split[1]));
			userReport.setAddSeller(getInt(split[2]));
			userReport.setNewUser(getInt(split[3]));
			userReport.setActiveBuyers(getInt(split[4]));
			userReport.setActiveSeller(getInt(split[5]));
			userReport.setBuyerActivityRate(split[6]);
			userReport.setSellerActivityRate(split[7]);
			userReport.setDealBuyers(getInt(split[8]));
			userReport.setDealSeller(getInt(split[9]));
			userReport.setAccumulatedBuyers(getInt(split[10]));
			userReport.setAccumulatedSeller(getInt(split[11]));
			userReport.setTotalUser(getInt(split[12]));
			list.add(userReport);
		}
		System.out.println(list);
		
		for (UserReport userReport : list) {
			String str = "insert into user_report(`year`, `month`,  `add_buyers`,  `add_seller` , `new_user` , `active_buyers`, `active_seller`, `buyer_activity_rate` "
					+ ",seller_activity_rate, deal_buyers, deal_seller, accumulated_buyers, accumulated_seller, total_user) values (" 
					+userReport.getYear()+","+userReport.getMonth()+","+userReport.getAddBuyers()+","+userReport.getAddSeller()+","+userReport.getNewUser()
					+","+userReport.getActiveBuyers()+","+userReport.getActiveSeller()+",'"+userReport.getBuyerActivityRate()
					+"','"+userReport.getSellerActivityRate()+"',"+userReport.getDealBuyers()+","+userReport.getDealSeller()+","+userReport.getAccumulatedBuyers()
					+","+userReport.getAccumulatedSeller()+","+userReport.getTotalUser()+ ");";
			System.out.println(str);
		}
	}

	public int getInt(String s) {
		return Integer.parseInt(s);
	}



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
			String stringCellValue = String.valueOf(cell.getRichStringCellValue());
			s += stringCellValue;
		}
		return s;
	}

}
