package cn.six.thread;


/**
 * 获取线程返回值   第一种方法   通过get方法和join
 * @author 有顺
 *
 */
public class ThreadBackV01 {

	public static void main(String[] args) throws Exception {
		testThreadBack();
	}

	private static void testThreadBack() throws InterruptedException {
		Testrun testrun = new Testrun(2);
		Thread thread = new Thread(testrun);
		Testrun testrun2 = new Testrun(10);
		Thread thread2 = new Thread(testrun2);
		thread.start();
	//	thread.join();
		thread2.start();
		thread2.join(); //保障前面2个线程在主进程之前结束
		System.out.println("jsljflsfjk");
		System.out.println(testrun.getNum());
		System.out.println(testrun2.getNum());
	}
	
	
}

class Testrun implements Runnable{
	private int num;
	
	public Testrun(){
		
	}
	
	public Testrun(int num){
		this.num = num;
	}
	
	int a = 0;
	@Override
	public void run() {

		while(true){
			num++;
			System.out.println(Thread.currentThread().getName()+"--"+a++);
			if(a>10)
				break;
		}
	}
	
	public int getNum(){
		return num;
	}
	
}
