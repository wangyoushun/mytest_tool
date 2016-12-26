package cn.six.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;


public class ThreadStart implements Runnable {

	private Map<LazySingleton, Integer> map;
	
	
	public ThreadStart(Map<LazySingleton, Integer> map){
		this.map = map;
	}
	
	@Override
	public void run() {
		
		boolean flag = true;
		int i = 0;
		while (flag) {
			LazySingleton instance2 = LazySingleton.getInstance();
			i++;
			if(i % 100 == 0)
				System.out.println(Thread.currentThread().getName()+"--"+instance2);
			if (!map.containsKey(instance2)) {
				map.put(instance2, 0);
				flag = false;
				System.out.println(Thread.currentThread().getName());
				System.out.println(map);
				System.out.println(i);
			}
		}
	}
	
	public static void main(String[] args) {
		LazySingleton instance = LazySingleton.getInstance();
		System.out.println(instance);

		Map<LazySingleton, Integer> map = new HashMap<LazySingleton, Integer>();
		map.put(instance, 1);
		
		Thread thread = new Thread(new ThreadStart(map));
		Thread thread2 = new Thread(new ThreadStart(map));
		Thread thread3 = new Thread(new ThreadStart(map));
		Thread thread4 = new Thread(new ThreadStart(map));
		Thread thread5 = new Thread(new ThreadStart(map));
		Thread thread6 = new Thread(new ThreadStart(map));
		thread.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
	}

}
