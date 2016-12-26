package cn.six.designpattern.singleton;

/**
 * 利用内部类实现单例
 * @author 有顺
 *
 */
public class InnerLazyLoader {

	private InnerLazyLoader() {

	}

	private static class LazyHoder {
		private static final InnerLazyLoader lazySingleton = new InnerLazyLoader();
	}
	
	public static InnerLazyLoader getInstance(){
		return InnerLazyLoader.LazyHoder.lazySingleton;
	}
}
