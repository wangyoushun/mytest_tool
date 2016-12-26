package cn.six.thread.rw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

/**
 * 模拟 多线程解析数据入库
 * 
 * @author 有顺
 * 
 */
public class Thread04 {
	public static void main(String[] args) throws Exception {
		String filePath = "D://bigText.txt";
		String out = "D://bigText2.txt";
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		BufferedWriter bw = new BufferedWriter(new FileWriter(out));

		Resource2 r = new Resource2(queue, br, bw);

		Thread t1 = new Thread(new ReadData2(r));
		Thread t2 = new Thread(new Wirte2(r));
		t1.start();
		t2.start();

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
		for (int i = 0; i < 10000; i++) {
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
}

class ReadData2 implements Runnable {
	private Resource2 resource2;

	public ReadData2(Resource2 resource2) {
		super();
		this.resource2 = resource2;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		resource2.read();
		long end = System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName()+"-time: r"+(end-start));
	}
}

class Wirte2 implements Runnable {
	private Resource2 resource2;

	public Wirte2(Resource2 resource2) {
		super();
		this.resource2 = resource2;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		resource2.wirte();
		long end = System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName()+"-time: w"+(end-start));
	}
}

class Resource2 {
	private ConcurrentLinkedQueue<String> queue;
	private BufferedReader br;
	private BufferedWriter bw;
	private boolean flag = false;
	private boolean over = false;

	public Resource2(ConcurrentLinkedQueue<String> queue, BufferedReader br,
			BufferedWriter bw) {
		super();
		this.queue = queue;
		this.br = br;
		this.bw = bw;
	}

	public synchronized void read() {
		String readLine = null;
		int count=0;
		while (true) {
			try {
				
				if(flag){
					this.wait();
				}
				
				if((readLine = br.readLine()) != null) {
					String substring = readLine
							.substring(readLine.indexOf("-") + 1);
					int parseInt = Integer.parseInt(substring);
					if (parseInt!=0 && parseInt%2==0){
						queue.add(substring);
						System.out.println("r--"+substring);
						count++;
					}
					
				}
				
				if(count>10){
					count=0;
					flag =true;
					this.notify();	
				}
				
				
				if (readLine == null) {
					br.close();
					over = true;
					this.notify();
				//	System.out.println("r-o");
				//	System.out.println(queue);
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void wirte() {
		while (true) {
			try {
				if(!flag && !over){
					this.wait();
				}
				
				if (!queue.isEmpty()) {
					String poll = queue.poll();
				//	System.out.println(poll);
					bw.write(poll);
					bw.newLine();

					System.out.println("w--"+poll);
				}
				
				
				flag=false;
				this.notify();
				
				if(queue.size()<1){
					//System.out.println("w-o");
				//	System.out.println(queue);
					bw.close();
					break;
				}
					

			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

}
