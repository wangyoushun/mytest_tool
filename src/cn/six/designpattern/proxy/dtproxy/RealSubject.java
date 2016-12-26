package cn.six.designpattern.proxy.dtproxy;

public class RealSubject implements Subject{

	@Override
	public void sysop() {
		System.out.println("==this is realsub");
	}

}
