package cn.six.designpattern.singleton;

/**
 * 线程安全 懒汉
 * @author 有顺
 *
 */
public class ThreadSafeSig {

	private static ThreadSafeSig ins = null;

	private ThreadSafeSig() {

	}

	public static ThreadSafeSig getInstance() {
		synchronized (ThreadSafeSig.class) {
			if (ins == null) {
				ins = new ThreadSafeSig();
			}
			return ins;

		}
	}

}
