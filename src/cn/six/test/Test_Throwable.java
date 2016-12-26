package cn.six.test;

/**
 * 使用throwable 获取栈信息
 * @author 有顺
 *
 */
public class Test_Throwable {

	public static void m1(){
		System.out.println(Foo.m());
	}
	
	public static void m2(){
		System.out.println(Foo.m());
	}
	
	public static void main(String[] args) {
		m1();
		System.out.println("----------------");
		m2();
	}
	
}

class Foo{
	public static String m(){
		Throwable throwable = new Throwable();
		StackTraceElement[] stackTrace = throwable.getStackTrace();
		for (StackTraceElement stackTraceElement : stackTrace) {
			System.out.println("****"+stackTraceElement);
			if(stackTraceElement.getMethodName().equals("m1")){  //获取调用方法名称
				return "m1";
			}
		}
		return "m2";
	}
}
