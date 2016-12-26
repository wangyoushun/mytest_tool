package com.opslab.test;

import org.junit.Test;

import com.opslab.StringUtil;

public class TestStringUtils {

	 /**
     * 获取字符串的编码
     */
	@Test
	public void test_cpDetector(){
		String s = "sdsdf";
		String cpDetector = StringUtil.cpDetector(s);
		System.out.println(cpDetector);
	}
	
	@Test
	public void test_replaceBlank(){
		String str="hsdfj38493_kdjfl ksl"
				+ "sdjfl ";
		String replaceBlank = StringUtil.replaceBlank(str);
		System.out.println(replaceBlank);
	}
}
