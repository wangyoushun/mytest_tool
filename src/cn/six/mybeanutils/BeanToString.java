package cn.six.mybeanutils;

import java.lang.reflect.Field;
import java.util.Date;

public class BeanToString {

	public static void main(String[] args) throws Exception {
		Student student = new Student("123", 12, new Date());
		String beantoString = beantoString(student);
		System.out.println(beantoString);
	}
	
	public static String beantoString(Object o) throws Exception{
		String str = "[";
		
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
		//	System.out.println(field.getType()+"--"+field.getName());
			field.setAccessible(true); //设置访问private权限
			System.out.println(field.get(o));
			str += field.getName()+"="+field.get(o)+", ";
		}
		str = str.substring(0, str.lastIndexOf(","));
		str += "]";
		return str;
	}
}
