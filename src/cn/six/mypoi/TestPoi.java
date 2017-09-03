package cn.six.mypoi;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import cn.six.utils.DBUtil;
import cn.six.utils.MyJdbcUtils;

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
