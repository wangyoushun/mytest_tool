package cn.six.designpattern.proxy.jtproxy;

public class PorxySubject implements Subject {

	private RealSubject realsub;

	public PorxySubject(RealSubject realsub) {
		super();
		this.realsub = realsub;
	}

	@Override
	public void sysop() {
		System.out.println("-代理前--");
		realsub.sysop();
		System.out.println("--代理后--");
	}

}
