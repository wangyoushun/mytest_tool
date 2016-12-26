package cn.six.test.commonlang;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class TestStringUtils {

	@SuppressWarnings("deprecation")
	@Test
	public void test1(){
		String trimToEmpty = StringUtils.trimToEmpty("  sdf df");
		System.out.println(trimToEmpty);
		
		String random = RandomStringUtils.randomAlphabetic(100);
		System.out.println(random);
		
		int maximum = NumberUtils.maximum(1, 2, 3);
		System.out.println(maximum);
		
		int [] array = {1,2,3,4,5};
	}
}
