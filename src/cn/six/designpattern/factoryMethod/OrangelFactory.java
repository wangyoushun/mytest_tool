package cn.six.designpattern.factoryMethod;

public class OrangelFactory implements IFactoryM{

	@Override
	public Fruit factory() {
		return new Orange();
	}

}
