package cn.six.designpattern.singleton;

/**
 * 懒汉   有线程安全问题
 * @author 有顺
 *
 */
public class LazySingleton {
	private static LazySingleton sig_instance=null;
	
	private LazySingleton(){
		
	}
	
	public static LazySingleton getInstance(){
		if(sig_instance==null){
			sig_instance = new LazySingleton();
		}
		return sig_instance;
	}
}
