package cn.six.mystring;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class StringTest1 {

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
}
