package cn.six.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadMap {

	public static ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<String, String>();// 所有辅料
	// private static Map<String, String> cache = new HashMap<String,
	// String>();// 所有辅料
	public static Map<String, String> map2 = new HashMap<String, String>();// 所有辅料

	public  void proess() {

		System.out.println(Thread.currentThread().getName() + "---" + System.currentTimeMillis());
		String key = cache.get("key");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//1  无线程安全问题
//		String putIfAbsent = cache.putIfAbsent("key", Thread.currentThread().getName());
//		System.out.println(putIfAbsent);
		
		//2  有 线程安全问题
		if (key == null) {
			cache.put("key", Thread.currentThread().getName());
			map2.put(Thread.currentThread().getName(), Thread.currentThread().getName());
			System.out.println("####" + Thread.currentThread().getName() + "---" + System.currentTimeMillis());
			if (map2.size() > 1) {
				System.out.println(cache);
				System.out.println(map2.size());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				throw new RuntimeException();
			}
		}

	}

}
