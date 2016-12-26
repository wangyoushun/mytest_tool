package cn.six.thread.cyclicBarrier;

public class MainTask implements Runnable{

	private long start;
	
	public MainTask(long start) {
		super();
		this.start = start;
	}

	@Override
	public void run() {
		System.out.println("main task 开始执行------------");
		System.out.println(System.currentTimeMillis()-start);
	}

}
