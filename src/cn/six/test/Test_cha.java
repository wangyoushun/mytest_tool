package cn.six.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Test_cha {

	@Test
	public void test02() throws Exception {
		String key = "key";
		int hashCode = key.hashCode();
		int length = 30;
		int index = hashCode % length;
		System.out.println(index);
		
		
	}
	
	@Test
	public void testDouble(){
		double a = 1.10;
		int b = 2;
		double c = b-a;
		System.out.println(c);
		System.out.println(0.05+0.05);
		System.out.println(1+0.1);
		System.out.println(1-0.1);
		System.out.println(1.1+0.9);
		System.out.println(3-2.1);
		System.out.println(10-9.1);
		System.out.println(0.01+0.09);
		System.out.println(1/10);
	} 
	
	/**
	 * 泛型  ？   类型通配符
	 * 问号是一个类型通配符。它读作“问号”。List<?> 是任何泛型 List 的父类型
	 * 可以从中检索元素，但是不能添加元素
	 * 
	 */
	@Test
	public void test04(){
		List<String> arrayList2 = new ArrayList<String>();
		arrayList2.add("111");
		List<?> list = arrayList2;
		System.out.println(list);
	}
	
	/**
	 * list<Object> 强制转为 List<实体>
	 */
	@Test
	public void test03(){
		List<Demo> arrayList2 = new ArrayList<Demo>();
		List<Object> arrayList = new ArrayList<Object>();
		for (Object object : arrayList) {
			arrayList2.add((Demo) object);
		}
	}
	
	@Test
	public void test(){
		int [] a={1,2,3,4,5,6,7,8,9,10};
		int j=1;
		int t=2;
		for (int i = 0; i < a.length; i++) {
		
			if(i==j){
				System.out.println();
				//j++;
				j=i+t;
				t++;
			}
			System.out.print(a[i]);
			
		}
	}
	
	@Test
	public void test2(){
		byte i = 2;
		prt(i);
	}
	
	
	public void prt(int i){
		System.out.println(i);
	}
	public void prt(byte i){
		System.out.println("byte");
	}
	public void prt(String i){
		System.out.println("----");
	}
	
}

class Demo{
	int i;
}
