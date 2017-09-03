package cn.six.test;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class Test_final {

	@Test
	public void testName() throws Exception {
		Date date = new Date(1525397220000L);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
	}
	
	public final void  test2(){
		System.out.println("test2");
	}
	
	public void test1(){
		System.out.println("test1");
	}
	
	public static void main(String[] args) throws Exception {
		Test_final test_final = new Test_final();
		test_final.test2();
		test_final.test1();
		
		Class<? extends Test_final> class1 = test_final.getClass();
		Method method = class1.getMethod("test2");
		method.invoke(test_final);
		
		
	}
}
