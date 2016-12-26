package cn.six.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class Test_generic {

	public static void main(String[] args) {
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void test01(){
		List<Integer> list01 = new ArrayList<Integer>();
		list01.add(1);
	
		//?  只读，只能读不能写
		List<?> list02 = list01;
		System.out.println(list02.get(0));
		
		list01 = (List<Integer>) list02;
		System.out.println(list02);
	}
	
	@Test
	public void test02(){
		Integer [] a = new Integer[2];
		Number [] b = new Number[2];
		Number [] c=a;
		a=(Integer[]) b;
	}
	
	
}
