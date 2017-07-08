package cn.six.test;

import java.net.URL;

import cn.six.utils.PropertiesUtil;

/**
 * 2017年6月7日
 * @author iwantfly
 * 类路径测试
 */
public class ClassPathTest {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		URL url1 = PropertiesUtil.class.getResource("/");
		URL url2 = PropertiesUtil.class.getClassLoader().getSystemResource("/");
		URL url3 = PropertiesUtil.class.getResource("");
		URL url4 = PropertiesUtil.class.getClassLoader().getSystemResource("");
		System.out.println(url1);
		System.out.println(url2);
		System.out.println(url3);
		System.out.println(url4);
	}
}
