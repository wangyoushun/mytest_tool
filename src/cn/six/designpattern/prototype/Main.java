package cn.six.designpattern.prototype;

import java.io.IOException;

/**
 * 原型模式
 * @author 有顺
 *
 */
public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
		GodRindB godRindB = new GodRindB(100, 10);
		Monkey monkey = new Monkey("wukong", 10000, godRindB);
		System.out.println(monkey);
		Monkey clone = (Monkey) monkey.clone();
	//	System.out.println(clone);
		System.out.println("monkey==copyMonkey?"+(clone==monkey));
		System.out.println("monkey god==copyMg?"+(monkey.getGodrin()==clone.getGodrin()));
		
		Monkey deepClone = (Monkey) monkey.deepClone();
		//System.out.println(deepClone);
	//	System.out.println(deepClone==monkey);
		System.out.println("monkey==deepClone?"+(deepClone==monkey));
		System.out.println("monkey god==deepClone god?"+(monkey.getGodrin()==deepClone.getGodrin()));
		
	}
}
