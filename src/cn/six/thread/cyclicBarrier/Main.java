package cn.six.thread.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 障碍器， 让子任务先执行完再执行主任务
 * @author 有顺
 *
 */
public class Main {
	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(7, new MainTask(1));
		new Thread(new SubTask("001", cb)).start();
		new Thread(new SubTask("002", cb)).start();
		new Thread(new SubTask("003", cb)).start();
		new Thread(new SubTask("004", cb)).start();
		new Thread(new SubTask("005", cb)).start();
		new Thread(new SubTask("006", cb)).start();
		new Thread(new SubTask("007", cb)).start();
	}
}
