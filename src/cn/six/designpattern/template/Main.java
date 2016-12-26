package cn.six.designpattern.template;

/**
 * 模版方法模式
 * 
 * 父类声明抽象方法，子类做具体实现
 * 父类引用指向子类实现， 调用具体子类的重写方法
 * 
 * @author 有顺
 *
 */
public class Main {
	public static void main(String[] args) {
		AbstractC workA = new WorkA();
		AbstractC workB = new WorkB();
		workA.run();
		workB.run();
	}
}
