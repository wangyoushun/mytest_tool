package cn.six.mybeanutils;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

public class TestBeanUtils {

	/**
	 * Simple——简单类型，如Stirng、Int……  
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception{
		TestBean1 bean1 = new TestBean1();
		bean1.setBeanName("test1");
		bean1.setVerNo(1);
		
		String property = BeanUtils.getProperty(bean1, "aaa");
		System.out.println(property);
		System.out.println(bean1);
		BeanUtils.setProperty(bean1, "verNo", 2);
		System.out.println(bean1);
		
	}
	
	/**
	 *对于Map类型，则需要以“属性名（key值）”的形式 
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception{
		TestBean1 bean1 = new TestBean1();
		TestBean1 bean2 = new TestBean1();
		bean1.setBeanName("test1");
		bean1.setVerNo(1);
		
		Map<String,String> describe = BeanUtils.describe(bean1);
		System.out.println(describe);
		
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("beanName", "test2");
		BeanUtils.populate(bean1, map);
		System.out.println(bean1);
		
	}
	
	public static void main(String[] args) throws Exception {
		Student student = new Student();
		student.setAge(1);
		
		String property = BeanUtils.getProperty(student, "age");
		System.out.println(property);
		
	}
}

