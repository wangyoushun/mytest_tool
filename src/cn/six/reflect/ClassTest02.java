package cn.six.reflect;

import java.lang.reflect.Constructor;

public class ClassTest02 {

	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("cn.six.reflect.B1");
		B1 b = (B1) clazz.newInstance();
		b.info();
		
		Constructor<?> constructor = clazz.getConstructor(String.class);
		B1 b2 = (B1)constructor.newInstance("111");
		b2.info();
	}
}

class B1{
	private String name;
	public B1(String name){
		this.name = name;
	}
	
	public B1(){
		
	}
	
	public void info(){
		System.out.println("B1----"+name);
	}
}
