package cn.six.test;

import java.lang.reflect.Method;

public class Test_final {

	
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
