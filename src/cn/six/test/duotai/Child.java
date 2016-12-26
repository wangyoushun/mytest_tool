package cn.six.test.duotai;

/**
 * 测试多态
 * 
 * @author 有顺
 * 
 */
public class Child extends Parent {
	public int age = 1;

	{
		System.out.println("c---代码块 1");
	}

	public Child() {
		System.out.println("c---构造方法");

	}

	public void sout() {
		System.out.println("child");
	}

	public static void main(String[] args) {
		new Child();
	}
}
