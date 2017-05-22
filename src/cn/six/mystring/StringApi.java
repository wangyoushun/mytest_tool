package cn.six.mystring;

import static org.junit.Assert.*;

import org.junit.Test;


public class StringApi {

	private String pre = "${";
	private String end = "}";
	
	@Test
	public void substr(){
		String str = "select * from user  t where t.id=${id} sf";
		int startIndex = str.indexOf(pre);
		int endIndex = str.indexOf(end);
		String substring = str.substring(0, startIndex);
		System.out.println(substring);
		String substring2 = str.substring(endIndex+1);
		System.out.println(substring2);
		str = str.substring(startIndex + pre.length(), endIndex);
		
		
		System.out.println(str);
		
	}
	
	@Test
	public void testReplace(){
		String s = "aaca";
		String replace = s.replace("a", "e");
		System.out.println(replace);
		String replace2 = s.replace('a', 'd');
		System.out.println(replace2);
		String replaceFirst = s.replaceFirst("a", "r");
		System.out.println(replaceFirst);
	}
	
	@Test
	public void strE(){
		String s1 = "123";
		String s2 = "123";
		String s3 = "12"+3;
		String s4="12";
		int i =3;
		String s5="12"+i;
		String s6="3";
		String s7=s4+s6;
		
		System.out.println(s1==s2);
		System.out.println(s1==s3);
		System.out.println(s1==s5);
		System.out.println(s1==s7);
	}
	
	@Test
	public void testName() throws Exception {
		String s= "111";
		System.out.println(s);
		st(s);
		System.out.println(s);
	}
	
	public void st(String s1){
		s1="123";
	}
}
