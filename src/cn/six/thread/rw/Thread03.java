package cn.six.thread.rw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * 模拟 多线程解析数据入库
 * 
 * @author 有顺
 * 
 */
public class Thread03 {
	
	//private static final Log LOG = LogFactory.getLog(Thread03.class);   
	private static final Logger logger = Logger.getLogger(Thread03.class);
	
	
	public static void main(String[] args) throws Exception {
		
		String filePath = "D://bigText.txt";
		String out = "D://bigText2.txt";
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		BufferedWriter bw = new BufferedWriter(new FileWriter(out));
		Thread t1 = new Thread(new ReadData(queue, br));
		Thread t2 = new Thread(new Wirte(queue, bw));
		Thread t3 = new Thread(new Wirte(queue, bw));
		t1.start();
		t2.start();
	//	t3.start();
		// t1.join();
		// System.out.println(queue);

	}

	/**
	 * 创建一个大文本
	 * 
	 * @throws Exception
	 */
	@Test
	public void createText() throws Exception {

		String filePath = "D://bigText.txt";
		FileWriter fileWriter = new FileWriter(filePath);
		BufferedWriter bw = new BufferedWriter(fileWriter);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 100000; i++) {
			list.add("this is date at-" + i);
		}
		long start = System.currentTimeMillis();
		for (String string : list) {
			bw.write(string);
			bw.newLine();
		}
		bw.close();
		long end = System.currentTimeMillis();
		System.out.println(list.size() + "数据写入文本--耗时：" + (end - start));
	}
	
	@Test
	public void testLog(){
		logger.info("222");
	}
}

class ReadData implements Runnable {

	private static final Logger logger = Logger.getLogger(ReadData.class);
	private ConcurrentLinkedQueue<String> queue;
	private BufferedReader br;

	public ReadData(ConcurrentLinkedQueue<String> queue, BufferedReader br) {
		super();
		this.queue = queue;
		this.br = br;
	}

	@Override
	public void run() {
		String readLine = null;
		while (true) {
			synchronized (queue) {
				try {
					if ((readLine = br.readLine()) != null) {
						queue.add(readLine);
						System.out.println("r:" + readLine);
						logger.info("r:" + readLine);
					}
					if (readLine == null) {
						br.close();
						queue.add("this is date at-100000");
						// queue = null;
						break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

class Wirte implements Runnable {
	private static final Logger logger = Logger.getLogger(Wirte.class);
	private ConcurrentLinkedQueue<String> queue;
	private BufferedWriter bw;

	public Wirte(ConcurrentLinkedQueue<String> queue, BufferedWriter bw) {
		super();
		this.queue = queue;
		this.bw = bw;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (queue) {
				if (!queue.isEmpty()) {
					try {
						String poll = queue.poll();
						String substring = poll
								.substring(poll.indexOf("-") + 1);
						int parseInt = Integer.parseInt(substring);
						if (parseInt % 2 != 0) {
							continue;
						}
						String str = Thread.currentThread().getName() + "w:" + poll;
						bw.write(str);
						System.out.println(str);
						logger.info(str);
						bw.newLine();
						if (parseInt == 100000) {
							bw.close();
							break;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}
