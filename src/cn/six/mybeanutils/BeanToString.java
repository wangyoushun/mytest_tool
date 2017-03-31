package cn.six.mybeanutils;

import java.lang.reflect.Field;

import com.google.gson.Gson;

/**
 * 实体对象转为json，字符串
 * 
 * @date 2017年3月23日
 */
public class BeanToString {

	/**
	 * bean convert string
	 * 
	 * @date 2017年3月23日
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String beanToString(Object o) throws Exception {
		String str = "[";
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true); // 设置访问private权限
			str += field.getName() + "=" + field.get(o) + ", ";
		}
		str = str.substring(0, str.lastIndexOf(","));
		str += "]";
		return str;
	}

	/**
	 * 实体对象转为json串
	 * 
	 * @date 2017年3月23日
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String beanToJson(Object o) throws Exception {
		Gson gson = new Gson();
		return gson.toJson(o);
	}
	
	
}
