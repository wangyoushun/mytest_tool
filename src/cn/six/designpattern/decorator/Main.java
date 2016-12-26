package cn.six.designpattern.decorator;

/**
 * 装饰者模式
 * @author 有顺
 * 
 *在不改变原来类的情况下，进行扩展。
　　动态的给对象增加一个业务功能，就功能来说，比生成子类更方便。
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("未装饰的dao");
		IGenericReposity g = new GenericReposityImpl();
		g.save();
		g.update();
		
		System.out.println("装饰之后的dao");
		IGenericReposity log = new LogReposity(g);
		log.save();
		log.update();
	}
}
