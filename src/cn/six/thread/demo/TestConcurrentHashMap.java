package cn.six.thread.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestConcurrentHashMap {

	public static  void main(String[] args) throws InterruptedException, ExecutionException {
		ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<String, String>();
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "111";
			}
		};
		FutureTask<String> futureTask = new FutureTask<String>(callable);
		futureTask.run();
		String string = futureTask.get();
		System.out.println(string);
	}
}
