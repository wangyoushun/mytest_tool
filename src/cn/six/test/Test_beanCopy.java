package cn.six.test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 对象属性拷贝
 * @author 有顺
 *
 */
public class Test_beanCopy {

	public static void main(String[] args) throws IllegalAccessException,
			InvocationTargetException {
		beanCopy1 b1 = new beanCopy1();
		b1.setId(1);
		b1.setName("b1");

		System.out.println("b1:" + b1);

		/*
		 * // java.lang.ClassCastException: cn.six.test.beanCopy1 cannot be cast
		 * to cn.six.test.beanCopy2 Object obj = b1; beanCopy2 b2 = (beanCopy2)
		 * obj; System.out.println("b2:"+b2);
		 */
		beanCopy2 b2 = new beanCopy2();
		BeanUtils.copyProperties(b2, b1); //对象属性拷贝  bean必须被public修饰 否则无法拷贝
		System.out.println("b2:" + b2);

	}
}
