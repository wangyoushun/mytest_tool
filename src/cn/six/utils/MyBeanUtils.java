package cn.six.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * @author 有顺
 * 
 */
public class MyBeanUtils {

	/**
	 * 对象以字符串形式输出
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String beantoString(Object o) {
		String str = "[";
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field field : fields) {
				// System.out.println(field.getType()+"--"+field.getName());
				field.setAccessible(true); // 设置访问private权限

				String string = field.get(o) + "";
				if (string.contains("@")) {
					// System.out.println(string);
					str += beantoString(field.get(o)) + ", ";
				} else {
					// System.out.println(field.get(o));
					str += field.getName() + "=" + field.get(o) + ", ";
				}
			}
			str = str.substring(0, str.lastIndexOf(","));
			str += "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/*
	 * public static String bean2String(Object o){ String str = "["; }
	 */

	public static void copyProperties(Object to, Object from) throws Exception {
		Method[] toMethods = to.getClass().getMethods();
		Method[] fromMethods = from.getClass().getMethods();

		for (Method method1 : toMethods) {
			if ("set".equals(method1.getName().substring(0, 3))) {
				String toName = method1.getName().substring(3);
				for (Method method2 : fromMethods) {
					if ("get".equals(method2.getName().substring(0, 3))
							&& toName.equals(method2.getName().substring(3))) {
						method1.invoke(to, method2.invoke(from));
					}
				}
			}
		}
	}

	/**
	 * 相同类型bean属性拷贝
	 * 
	 * @param to
	 * @param from
	 * @throws IntrospectionException
	 */
	public static Object copyBean2Bean(Object to, Object from) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(to.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor p : propertyDescriptors) {
				// System.out.println(propertyDescriptor);
				Method readMethod = p.getReadMethod();
				Method writeMethod = p.getWriteMethod();
				if (readMethod != null && writeMethod != null) {
					try {
						Object result = readMethod.invoke(from);
						writeMethod.invoke(to, result);
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return to;
	}

}
