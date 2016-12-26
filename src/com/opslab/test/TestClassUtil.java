package com.opslab.test;

import java.net.URL;

import org.junit.Test;

import com.opslab.ChinesUtil;
import com.opslab.ClassUtil;
import com.opslab.ftp.FTPFactory;

public class TestClassUtil {

	@Test
	public void test(){
		String[] field = ClassUtil.getField("cn.six.test.Bean", true);
		for (String string : field) {
			System.out.println(string);
		}
		
		String pingYin = ChinesUtil.getPingYin("你 好 ");
		System.out.println(pingYin);
		
		URL location = TestClassUtil.class.getProtectionDomain().getCodeSource().getLocation();
		System.out.println(location.getPath());
	}
}
