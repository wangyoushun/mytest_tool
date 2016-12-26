package cn.six.designpattern.abstractFactory;

public class InterFactory implements AbstractFactory{

	@Override
	public ICpu createCpu() {
		return new InterCpu();
	}

	@Override
	public IMainBorad createMBoard() {
		return new InterMBoard();
	}

}
