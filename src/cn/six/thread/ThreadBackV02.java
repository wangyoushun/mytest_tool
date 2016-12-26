package cn.six.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable 接口实现线程返回值
 * @author 有顺
 *
 */
public class ThreadBackV02 {

	public static void main(String[] args) throws Exception, ExecutionException {
		// 创建一个线程池
		ExecutorService exs = Executors.newCachedThreadPool();
		// 创建两个有返回值的任务
		CallThread call01 = new CallThread(2);
		CallThread call02 = new CallThread(10);
		
		Future<Integer> future = exs.submit(call01);
		Future<Integer> future2 = exs.submit(call02);
		
		System.out.println(future.get());
		System.out.println(future2.get());
		
		   // 关闭线程池
		exs.shutdown();
	}
}

class CallThread implements Callable<Integer> {
	private int num;

	public CallThread() {

	}

	public CallThread(int num) {
		this.num = num;
	}

	@Override
	public Integer call() throws Exception {
		int a = 0;
		while (true) {
			num++;
			System.out.println(Thread.currentThread().getName() + "--" + a++);
			if (a > 10)
				break;
		}
		return num;
	}

}