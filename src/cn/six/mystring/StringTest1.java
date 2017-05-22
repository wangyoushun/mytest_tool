package cn.six.mystring;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.opslab.FileUtil;


public class StringTest1 {

	 public static List<String> lines(File file) {
	        List<String> list = new ArrayList<>();
	        try {
	                BufferedReader reader = new BufferedReader(new FileReader(file));
	   
	            String line;
	            while ((line = reader.readLine()) != null) {
	                list.add(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	
	//sql 以java字符串形式连接
		@Test
		public void convertUpperCase() throws Exception {
			List<String> lines = FileUtil.lines(new File("e://test.txt"));
			for (String string : lines) {
				System.out.println(string.toUpperCase());
			}
		}
	
	//sql 以java字符串形式连接
	@Test
	public void sqlToStr() throws Exception {
		List<String> lines = FileUtil.lines(new File("e://test.txt"));
		for (String string : lines) {
			System.out.println("+\" "+string.trim()+"\" ");
		}
	}
	
	/*
	 * 从文件读取数据，驼峰转为下划线，增加javatype
	 */
	@Test
	public void testName1() throws Exception {
		List<String> lines = FileUtil.lines(new File("e://test.txt"));
		System.out.println(lines);
		String str="";
		for (String string : lines) {
			String[] split = string.split(" ");
			str=str+"#{"+underlineToCamel(split[0])+",jdbcType="+split[1].toUpperCase()+"},";
//			System.out.println(string);
		}
		System.out.println(str);
	}
	
	/*
	 * 从文件读取数据，驼峰转为下划线
	 */
	@SuppressWarnings("resource")
	@Test
	public void testName2() throws Exception {
		List<String> lines = FileUtil.lines(new File("e://test.txt"));
		System.out.println(lines);
		for (String string : lines) {
			String underlineToCamel = camelTounderline(string);
			System.out.println(underlineToCamel);
		}
	}
	/*
	 * 从文件读取数据，下划线字符串转为驼峰
	 */
	@SuppressWarnings("resource")
	@Test
	public void testName() throws Exception {
		List<String> lines = FileUtil.lines(new File("e://test.txt"));
		System.out.println(lines);
		for (String string : lines) {
			String underlineToCamel = underlineToCamel(string);
			System.out.println(underlineToCamel);
		}
	}
	
	@Test
	public void test05() {
		String tickNo = "781455678||";
		String substring = tickNo.substring(0,6);
		System.out.println(substring);
		if (tickNo.length() > 5
				&& ("781455".equals(tickNo.substring(0,6)) || "781456"
						.equals(tickNo.substring(0,6)))) {
			System.out.println(222);
		}
	}
	
	@Test
	public void test01() {
		String str = "2c9f8220537b978f01542b33d4995288||";
		String regex = "^[a-z0-9]+$";
		boolean matches = str.matches(regex);
		System.out.println(matches);
	}

	@Test
	public void datetoString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String newPath = format.format(new Date());
		System.out.println(newPath);

		String s = "2/2/11.xls";
		int lastIndexOf = s.lastIndexOf("/");
		String substring2 = s.substring(0, lastIndexOf + 1);
		String substring = s.substring(lastIndexOf + 1);

		System.out.println(substring2);
		System.out.println(substring);

	}

	@Test
	public void testArrayToString() {
		Integer[] a = { 1, 2, 3, 4, 5 };
		String[] s = { "1", "2" };
		System.out.println(a.toString());
		System.out.println(s);
		String join = StringUtils.join(a, ",");
		System.out.println(join);
		String string = Arrays.toString(s);
		System.out.println(string);
	}

	@Test
	public void testSplit() {
		String str = "1,2,3";
		String[] split = str.split(",");
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
	}

	@Test
	public void testString() {
		String str = "1,2,3";
		String substring = str.substring(0, 1);
		System.out.println(substring);

		String str2 = "78145677";
		String substring2 = str2.substring(0, 6);
		System.out.println(substring2);
	}

	@Test
	public void testString2() {
		String str = "3.01";
		if (str.contains("."))
			str = str.substring(0, str.indexOf("."));
		System.out.println(str);
	}
	
	public static String underlineToCamel(String param) {
		char ch = '_';
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == ch) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static String camelTounderline(String param) {
		// a-z：97-122
		// A-Z：65-90
		// 0-9：48-57
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);

			if (i > 0 && c >= 65 && c <= 90) { // 大写字符
				if (i < len) {
					sb.append("_");
					sb.append(Character.toLowerCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString().toUpperCase();
	}
}
