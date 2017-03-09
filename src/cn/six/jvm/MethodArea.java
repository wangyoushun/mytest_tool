package cn.six.jvm;

import org.junit.Test;

public class MethodArea {

	@Test
	public void testConstant() {

		String str2 = new StringBuilder("javsdfdfsdf").append("a").toString();
		System.out.println(str2.intern() == str2);

		String str1 = new StringBuilder("ja").append("av").toString();
		System.out.println(str1.intern() == str1);
	
		String str3 = new StringBuilder("MethodAre").append("a.class").toString();
		System.out.println(str3.intern() == str3);

	}
}
