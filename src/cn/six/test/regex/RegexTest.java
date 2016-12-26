package cn.six.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void checkMobNo(){
		String patternStr = "^1[3|4|5|8][0-9]\\d{8}$";
		String str = "18256587160";
		
		Pattern compile = Pattern.compile(patternStr);
		Matcher matcher = compile.matcher(str);
		System.out.println(matcher.matches());
	}
	
	@Test
	public void test1(){
		String str="ceponline@yahoo.com.cn";
		Pattern pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",Pattern.CASE_INSENSITIVE);
	//	Pattern p = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
	}
	
	/**
	 * () 分组， \\1 引用第一个组
	 */
	@Test
	public void test2(){
		//(".*(.)(.*\\1).*");
		
		String str="abaa";
	//	String strPattern = ".*(.)(.*\\1).*";
	//	String strPattern = ".*(.).*\\1.*";
		String strPattern = ".*(.)\\1.*";
		boolean match = match(str, strPattern);
		System.out.println(match);
	}

	//
	public boolean match(String str, String strPattern) {
		Pattern pattern = Pattern.compile(strPattern);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
