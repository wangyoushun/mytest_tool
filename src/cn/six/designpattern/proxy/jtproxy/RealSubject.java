package cn.six.designpattern.proxy.jtproxy;

public class RealSubject implements Subject{

	@Override
	public void sysop() {
		System.out.println("==this is realsub");
	}

}
