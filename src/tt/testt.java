package tt;

import java.io.IOException;

import org.junit.Test;

class Resource {
	String name;
	String sex;
	boolean flag = false;
}

class input implements Runnable {
	private Resource r;

	input(Resource r) {
		this.r = r;
	}

	public void run() {
		int x = 0;

		while (true) {
			synchronized (r)// 枷锁 该资源是唯一的，所以选择该资源
			{
				// System.out.println(r.flag);
				if (r.flag)
					try {
						System.out.println("----");
						r.wait();

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (x == 0) {
					r.name = "张三";
					r.sex = "男";
				} else {
					r.name = "李四";
					r.sex = "女";
				}
				System.out.println("生产：" + r.name + "--" + r.sex);
				x = (x + 1) % 2; // 循环输出 张三，李四
				r.flag = true;
				r.notify(); // 唤醒r对象所在的线程
			}
		}

	}
}

class output implements Runnable {
	private Resource r;

	output(Resource r) {
		this.r = r;
	}

	public void run() {
		while (true) {
			synchronized (r)// 枷锁，该资源是唯一的，所以选择该资源
			{

				if (!r.flag)
					try {
						r.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				System.out.println("out" + r.name + ".." + r.sex);
				r.flag = false;
				r.notify();
			}
		}
	}
}

public class testt {

	public static void main(String args[]) {

		Resource r = new Resource();
		input in = new input(r);
		output out = new output(r);
		Thread tin = new Thread(in);
		Thread tout = new Thread(out);
		tin.start();
		tout.start();
		// 错误原因：当输入的时候只赋值了姓名，性别还没有赋值就被另一个线程输出
		// 解决方法：同步代码块
	}
	
	@Test
	public void testExplorer(){
		try {
			Runtime.getRuntime().exec("explorer D:\\desk");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	
	
	
	
}
