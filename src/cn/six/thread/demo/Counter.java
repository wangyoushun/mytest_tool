package cn.six.thread.demo;

public class Counter {
	private volatile int value = 0;
	
	public synchronized int getValue(){
		return this.value;
	}
	
	public synchronized int increment(){
		return ++value;
	}
}
