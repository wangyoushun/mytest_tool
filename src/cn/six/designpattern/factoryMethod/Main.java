package cn.six.designpattern.factoryMethod;

/**
 * 工厂方法模式
 * 核心是一个抽象工厂类，具体工厂继承抽象工厂
 * @author 有顺
 *
 */
public class Main {

	private static IFactoryM fm;

	public static void main(String[] args) {
		fm = new AppleFactory();
		fm.factory().eat();
		
		fm = new OrangelFactory();
		fm.factory().eat();
	}
}
