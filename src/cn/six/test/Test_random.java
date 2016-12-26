package cn.six.test;

import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

public class Test_random {

	@Test
	public void test1(){
		Random random = new Random();
	/*	for(int i=0; i<100; i++){
			int nextInt = random.nextInt();
			System.out.println(nextInt);
		}*/
		
		String string = UUID.randomUUID().toString();
		System.out.println(string);
		
	}
}
