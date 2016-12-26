package cn.six.designpattern.abstractFactory;

/**
 * 抽象工厂模式
 * 
 * @author 有顺
 *　假设一个子系统需要一些产品对象，而这些产品又属于一个以上的产品等级结构。
 *那么为了将消费这些产品对象的责任和创建这些产品对象的责任分割开来，
 *可以引进抽象工厂模式。这样的话，消费产品的一方不需要直接参与产品的创建工作
 *，而只需要向一个公用的工厂接口请求所需要的产品。
 */
public class Main {

	public static void main(String[] args) {
		AmdFactory amdFactory = new AmdFactory();
		ICpu createCpu = amdFactory.createCpu();
		createCpu.pCpu();
		IMainBorad createMBoard = amdFactory.createMBoard();
		createMBoard.pMainBorad();
		
		InterFactory interFactory = new InterFactory();
		ICpu createCpu2 = interFactory.createCpu();
		createCpu2.pCpu();
		IMainBorad createMBoard2 = interFactory.createMBoard();
		createMBoard2.pMainBorad();
	}
}
