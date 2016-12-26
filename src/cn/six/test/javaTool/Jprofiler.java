package cn.six.test.javaTool;

public class Jprofiler extends Thread {
	public static void main(String[] args) throws InterruptedException {
		Jprofiler t = new Jprofiler();
		for (int i = 1; i < 10000; i++) {
			t.new HelloWorld();
			t.sleep(100); // 休眠100毫秒
		}
	}

	class HelloWorld {
		public HelloWorld() {
			System.out.println("Hello Jayzee!");
		}
	}
}
