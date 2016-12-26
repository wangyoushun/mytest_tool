package cn.six.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SubTask implements Runnable{
	private String name;
	private CyclicBarrier cb;

	public SubTask(String name, CyclicBarrier cb) {
		this.name = name;
		this.cb = cb;
	}

	@Override
	public void run() {
		System.out.println(name+"开始---");
		for(int i=0; i<10000; i++);
		System.out.println(name+"结束--");
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
