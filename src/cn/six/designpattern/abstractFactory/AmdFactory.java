package cn.six.designpattern.abstractFactory;

public class AmdFactory implements AbstractFactory{

	@Override
	public ICpu createCpu() {
		return new AmdCpu();
	}

	@Override
	public IMainBorad createMBoard() {
		return new AmdMBoard();
	}

}
