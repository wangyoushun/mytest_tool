package cn.six.test;


public class Test_static {

	static{
		Bean bean = new Bean();
		bean.setName("111");
		System.out.println(bean);
	}
	public static void main(String[] args) {
		System.out.println("11111111111111111111111");
		ClassLoader classLoader = Test_static.class.getClassLoader();
		System.out.println(classLoader);
		Test_static test_static = new Test_static();
		ClassLoader classLoader2 = test_static.getClass().getClassLoader();
		System.out.println(classLoader2);
		ClassLoader classLoader3 = Test_static.class.getClass().getClassLoader();//核心类的类加载器无法获取
		System.out.println(classLoader3);
	}
}
