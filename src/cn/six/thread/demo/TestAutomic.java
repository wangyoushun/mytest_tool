package cn.six.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAutomic {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		MyRunable t1 = new MyRunable(100, "001");
		MyRunable t2 = new MyRunable(200, "002");
		MyRunable t3 = new MyRunable(300, "003");
		MyRunable t4 = new MyRunable(400, "004");
		MyRunable t5 = new MyRunable(500, "005");
		MyRunable t6 = new MyRunable(600, "006");
		MyRunable t7 = new MyRunable(700, "007");
		
		executorService.execute(t1);
		executorService.execute(t2);
		executorService.execute(t3);
		executorService.execute(t4);
		executorService.execute(t5);
		executorService.execute(t6);
		executorService.execute(t7);
		
		executorService.shutdown();
	}
}

class MyRunable implements Runnable {

	private volatile static int count = 1000;
	//private static AtomicInteger count = new AtomicInteger(1000);  //原子量
	private int x;
	private String name;

	public MyRunable(int x, String name) {
		super();
		this.x = x;
		this.name = name;
	
	}

	@Override
	public void run() {
		synchronized (TestAutomic.class) {
			count = count+x;
		//	int addAndGet = count.addAndGet(x);
			System.out.println(name + "执行"+ x+ "余额"+ count);
		}
	}
}
