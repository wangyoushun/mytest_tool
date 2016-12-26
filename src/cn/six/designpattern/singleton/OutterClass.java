package cn.six.designpattern.singleton;

public class OutterClass {

	static{
		System.out.println("out");
	}
	
	private class InnerClass{
		private static final int R = 1;
		
	}
	
	public static void main(String[] args) {
		System.out.println("main"+OutterClass.InnerClass.R);
	}
}
