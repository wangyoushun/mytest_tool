package cn.six.thread.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class ThreadDemo1 implements Runnable {

	private Counter counter;

	public ThreadDemo1(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (counter) {
				
				int value = counter.getValue();
				System.out.println("get:" + value);
			}
			// p(value);
		}
	}

	private synchronized void p(int value) {
		System.out.println("get" + value);
	}

	public static void main(String[] args) {
		Counter counter = new Counter();
		Thread thread = new Thread(new ThreadDemo1(counter));
		Thread thread2 = new Thread(new Thread2(counter));
		thread.start();
		thread2.start();
		
		Collections.synchronizedList(new ArrayList<String>());
	}

}

class Thread2 implements Runnable {

	private Counter counter;

	public Thread2(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (counter) {
				int increment = counter.increment();
				System.out.println("increment=" + increment);
			}
			// p(increment);
		}
	}

	private synchronized void p(int value) {
		System.out.println("increment" + value);
		new Vector<>();
	}

}
