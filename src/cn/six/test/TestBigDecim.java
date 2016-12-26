package cn.six.test;

import java.math.BigInteger;

import org.junit.Test;

public class TestBigDecim {

	@Test
	public void test(){
		BigInteger a = new BigInteger("111111111111111111111111111111");
		BigInteger b = new BigInteger("222222222222222222222222222222");
		System.out.println(b);
		a=a.add(b);
		System.out.println(a);
	}
	
	@Test
	public void test1(){
		String a = "0.01";
		String b = "0.09";
		double parseDouble = Double.parseDouble(a);
		double parseDouble2 = Double.parseDouble(b);
		System.out.println(parseDouble+parseDouble2);
		
		double q = 0.05;
		double w = 0.05;
		System.out.println(q+w);
		
	}
}
