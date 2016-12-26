package cn.six.test;

import java.math.BigDecimal;

import org.junit.Test;

public class Test_bigDeci {

	@Test
	public void test1(){
		BigDecimal b1 = new BigDecimal("1.12");
		BigDecimal b2 = new BigDecimal("2.0");
		BigDecimal multiply = b1.multiply(b2);
		System.out.println(multiply.toString());
		System.out.println(b1);
	}
}
