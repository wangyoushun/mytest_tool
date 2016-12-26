package cn.six.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassTest01 {

	private int a = 1;
	public int b=2;
	
	public ClassTest01(){
		System.out.println("public 无参 构造器");
	}
	
	private ClassTest01(int a){
		this.a = a;
		System.out.println("private 有参构造器");
	}
	
	public void info(){
		System.out.println("无参方法");
	}
	
	public void info(String name){
		System.out.println("有参方法");
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		 Class<ClassTest01> clazz = ClassTest01.class;
		 Constructor[] constructors = clazz.getConstructors();
		 System.out.println("public 构造器如下");
		 for (Constructor c : constructors) {
			System.out.println(c);
		}
		 
		 Constructor<?>[] constructors2 = clazz.getDeclaredConstructors();
		 System.out.println("所有构造器如下");
		 for (Constructor<?> c : constructors2) {
			System.out.println(c);
		}
		 
		 Method[] methods = clazz.getMethods();
		 System.out.println("public 方法如下");
		 for (Method m : methods) {
			System.out.println(m);
		}
		 
		 Method method = clazz.getMethod("info", String.class);
		 System.out.println("info   int 方法");
		 System.out.println(method);
		 
		 Field[] fields = clazz.getDeclaredFields();
		 System.out.println("所有字段");
		 for (Field field : fields) {
			System.out.println(field);
		}
	}
}
