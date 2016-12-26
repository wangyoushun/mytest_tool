package cn.six.thread.demo;

public class ThreadDemo2 {

	public static void main(String[] args) {
		getCpuNum();
		Thread thread = new Thread(new Thread3());
		thread.start();
	}

	/**
	 * 获取cpu数量
	 */
	public static int getCpuNum() {
		int a = Runtime.getRuntime().availableProcessors();
		System.out.println(a);
		return a;
	}
}

class Thread3 implements Runnable{

	private volatile boolean flag = true;
	
	@Override
	public void run() {
		int i=0;
		while(!Thread.currentThread().isInterrupted()){
			i++;
			System.out.println(i++);
			if(i>100){
				Thread.currentThread().interrupt();
				System.out.println("over");
			//	flag = false;
			}
		}
	}
	
}
