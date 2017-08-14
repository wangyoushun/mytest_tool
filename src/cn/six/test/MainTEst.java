package cn.six.test;

public class MainT {

	private int a = 2;
	private String s = "1";
	
	public static void main(String[] args) {
//		A a = new B();
//		a.tt();
		MainT mainT = new MainT();
		mainT.a();
		mainT.b();
	}
	
	public void a(){
		a=5;
		s="2";
	}
	public void b(){
		System.out.println(a);
		System.out.println(s);
	}
}
