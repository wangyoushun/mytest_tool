package cn.six.test;

public class MainTEst {

	private int a = 2;
	private String s = "1";
	
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
	}

	/**
	 * 
	 */
	public static void test01() {
		//		A a = new B();
		//		a.tt();
				MainTEst mainT = new MainTEst();
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
