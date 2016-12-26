package cn.six.designpattern.singleton;

/**
 * 线程安全 懒汉 双检查
 * 
 * @author 有顺
 * 
 */
public class DoubleCheckSafeSig {

	private volatile static DoubleCheckSafeSig ins = null;

	private DoubleCheckSafeSig() {

	}

	public static DoubleCheckSafeSig getInstance() {
		if (ins == null) {
			synchronized (DoubleCheckSafeSig.class) {
				if (ins == null) {
					ins = new DoubleCheckSafeSig();
				}
			}
		}
		return ins;
	}

}
