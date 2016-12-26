package cn.six.test;

import org.junit.Test;

public class TestThrow {

	@Test
	public void test2(){
		Bean bean = new Bean();
		bean.setId(1L);
	}
	
	public static void main(String[] args) {
		boolean testThrow;
		/*try {
			testThrow = testThrow();
			System.out.println("true");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("false");
		}
		System.out.println("end");*/
		boolean testThrow2 = testThrow();
		System.out.println(testThrow2);
		System.out.println("testthrow2");

	}
	
	public static boolean testThrow(){
		boolean flag = false;
		
		try {
			System.out.println("test");
			int i=1/0;
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("0000");
		}
		System.out.println("----------");
		return flag;
	}
}
