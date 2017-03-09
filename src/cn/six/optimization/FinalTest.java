package cn.six.optimization;

/**
 * 测试final修饰方法， jvm会做优化， 对性能没有显著提升， 不推荐作为一种优化手段
 * @author 有顺
 *
 */
public class FinalTest {
	public static void main(String[] args) {
		final long N = 1000000L;
		int a = 5;
		int b = 17;
		int c = 0;

		long start = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			c = max(5, 17);
		}
		long end = System.currentTimeMillis();
		System.out.println("普通方法 c=" + c + "spend time " + (end - start));

		start = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			c = max2(5, 17);
		}
		end = System.currentTimeMillis();
		System.out.println("final修饰方法 c=" + c + "spend time " + (end - start));

		start = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			c = a > b ? a : b;
		}
		end = System.currentTimeMillis();
		System.out.println("直接代码 c=" + c + "spend time " + (end - start));

		
	}

	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	public final static int max2(int a, int b) {
		return a > b ? a : b;
	}
}
