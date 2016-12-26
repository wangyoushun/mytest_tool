package com.opslab.test;

import java.io.IOException;

import org.junit.Test;

import com.opslab.PropertiesUtil;

public class TestPropertiesUtils {

	/**
	 * 提供一些常用的属性文件相关的方法
	 * @throws IOException 
	 * 
	 */
	@Test
	public void test_GetValueByKey() throws IOException {
		//测试获取相对路径
		String string = this.getClass().getResource("").getFile();
		String string1 = this.getClass().getResource("/").getFile();
		System.out.println(string);
		System.out.println(string1);
		
		String path = string1.substring(1) + "conf/ftp.properties";
		
		//测试属性文件  单个 key
		String getValueByKey = PropertiesUtil.GetValueByKey(
				path, "FTPTest.host");
		System.out.println(getValueByKey);
		
		//测试系统属性
		String javaVersion = PropertiesUtil.key("java.version");
		System.out.println(javaVersion);
		
		String getAllProperties = PropertiesUtil.GetAllProperties(path);
		System.out.println(getAllProperties);
		String[] split = getAllProperties.split("<>");
		for (String ss : split) {
			System.out.println(ss);
		}
		
	}
}
