package cn.six.designpattern.factoryMethod;

public class AppleFactory implements IFactoryM{

	@Override
	public Fruit factory() {
		return new Apple();
	}

}
