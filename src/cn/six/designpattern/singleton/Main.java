package cn.six.designpattern.singleton;

/**
 * 
 * @author 有顺
 *
 */
public class Main {

	public static void main(String[] args) {
		// 恶汉单例
//		ehSingleton();

		unsafeLazy();

		threadSafeSig();

		dcheckSafeLazy();
		
		innerSingleton();
		
		
	}

	/**
	 * 内部类实现
	 */
	public static void innerSingleton() {
		InnerLazyLoader instance = InnerLazyLoader.getInstance();
		InnerLazyLoader instance2 = InnerLazyLoader.getInstance();
		System.out.println(instance==instance2);
	}

	/**
	 * 双检查线程安全懒汉
	 */
	public static void dcheckSafeLazy() {
		DoubleCheckSafeSig instance = DoubleCheckSafeSig.getInstance();
		DoubleCheckSafeSig instance2 = DoubleCheckSafeSig.getInstance();
		System.out.println(instance==instance2);
	}

	/**
	 * 线程安全懒汉
	 */
	public static void threadSafeSig() {
		ThreadSafeSig instance = ThreadSafeSig.getInstance();
		ThreadSafeSig instance2 = ThreadSafeSig.getInstance();
		System.out.println(instance == instance2);
	}

	/**
	 * 线程不安全懒汉
	 */
	public static void unsafeLazy() {
		LazySingleton instance = LazySingleton.getInstance();
		LazySingleton instance2 = LazySingleton.getInstance();
		System.out.println(instance == instance2);
	}

	/**
	 * 恶汉
	 */
//	public static void ehSingleton() {
//		Singleton instance = Singleton.getInstance();
//		Singleton instance2 = Singleton.getInstance();
//		System.out.println(instance == instance2);
//	}
}
