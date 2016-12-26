package cn.six.test.duotai;

public class Parent {
	public int age = 10;

	{
		System.out.println("p---代码块 1");
	}

	public Parent() {
		System.out.println("p---构造方法");
		sout();
	}

	{
		System.out.println("p---代码块 2");
	}

	public void sout() {
		System.out.println("parent");
	}
}
