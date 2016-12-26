package cn.six.designpattern.template;

public abstract class AbstractC {
	public void run(){
		start();
		
		doWork();
		
		end();
		
	}

	public abstract void doWork();

	/**
	 * 
	 */
	public void end() {
		System.out.println("end...");
	}

	/**
	 * 
	 */
	public void start() {
		System.out.println("first, start---");
	}
}
