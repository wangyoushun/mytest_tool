package cn.six.thread;


public class ThreadMapCache {

	public static void main(String[] args) {
		final ThreadMap threadMap = new ThreadMap();
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {
					threadMap.proess();
				}
			}.start();
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1-"+threadMap.cache);
		System.out.println("2-"+threadMap.map2);
	}
}
