package cn.six.jsoup.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BlockQueueTest {

//	private  BlockingQueue<String> queue = new LinkedBlockingQueue<>();
	private  BlockingQueue<String> queue =  new ArrayBlockingQueue<>(20);
	
	

	
	public static void main(String[] args) {
		BlockQueueTest blockQueueTest = new BlockQueueTest();
//		blockQueueTest.main();
		blockQueueTest.test01();
	}
	
	public void test01(){
		ExecutorService jobThreadPool = Executors
				.newFixedThreadPool( 4 );

	    for(int i=0; i<15; i++){
	    	jobThreadPool.execute(new ThreadA(queue, i+""));
	    }
	    
	    new Thread(new ThreadB(queue)).start();
	    jobThreadPool.shutdown();
		
//		for (String strulr : pageList) {
//			jobThreadPool.execute(new JobThread(queue, strulr));
//		}
	}
	
	public  void main() {
		
		for(int i=0;i<10; i++){
			new Thread(new ThreadA(queue, i+"")).start();
		
		}
		for(int i=0; i<12; i++){
			new Thread(new ThreadB(queue)).start();
		}
		
//		
//		new Thread(new ThreadC()).start();

		
	}
}

class ThreadA implements Runnable{

	private BlockingQueue<String> queue;
	private String str;
	public ThreadA(BlockingQueue<String> queue,String str) {
		super();
		this.queue = queue;
		this.str=str;
	}

	@Override
	public void run() {
		try {
		
			for(int i=0; i<100; i++){
				System.out.println(Thread.currentThread().getName()+"-"+str+";;"+queue.size());
				queue.put(Thread.currentThread().getName()+"-"+str+"-"+i);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class ThreadB implements Runnable{

	private BlockingQueue<String> queue;
	public ThreadB(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true){
			
		
		try {
			String str = queue.take();
			System.out.println(Thread.currentThread().getName()+"  get ---"+str+";;"+queue.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}

class ThreadC implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("11");
		}
	}
	
}
