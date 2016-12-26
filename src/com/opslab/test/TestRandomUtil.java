package com.opslab.test;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import com.opslab.RandomUtil;

public class TestRandomUtil {
	
	@Test
	public void test_integer() {
		int integer = RandomUtil.integer(0, 100);
		System.out.println(integer);
		
		String lowerString = RandomUtil.LowerString(12);
		System.out.println(lowerString);
		
		String upperString = RandomUtil.UpperString(12);
		System.out.println(upperString);
		
		String uuid = RandomUtil.uuid();
		System.out.println(uuid);
	}
	
	/**
	 *  org.apache.commons.lang.RandomStringUtils
	 */
	@Test
	public void test_integer2() {
//		int nextInt = RandomUtils.nextInt(100);
//		System.out.println(nextInt);
		
//		String random = RandomStringUtils.random(10);
//		System.out.println(random);
		
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(10);
		System.out.println(randomAlphabetic);
		
		String randomNumeric = RandomStringUtils.randomNumeric(10);
		System.out.println(randomNumeric);
	}

}
