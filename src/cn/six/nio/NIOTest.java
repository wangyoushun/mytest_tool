package cn.six.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class NIOTest {

	@Test
	public void testdup() {
		ByteBuffer bfb = ByteBuffer.allocate(15);
		ByteBuffer bfc = bfb.duplicate();
		System.out.println(bfb.capacity() + ",  " + bfb.limit() + ",  "
				+ bfb.position());
		System.out.println(bfc.capacity() + ",  " + bfc.limit() + ",  "
				+ bfc.position());
		for (int i = 0; i < 10; i++) {
			bfb.put((byte) i);
		}
		System.out.println(bfb.capacity() + ",  " + bfb.limit() + ",  "
				+ bfb.position());
		System.out.println(bfc.capacity() + ",  " + bfc.limit() + ",  "
				+ bfc.position());
		for (int i = 0; i < 5; i++) {
			bfc.put((byte) i);
		}
		System.out.println(bfb.capacity() + ",  " + bfb.limit() + ",  "
				+ bfb.position());
		System.out.println(bfc.capacity() + ",  " + bfc.limit() + ",  "
				+ bfc.position());
		bfc.flip();
		System.out.println(bfb.capacity() + ",  " + bfb.limit() + ",  "
				+ bfb.position());
		System.out.println(bfc.capacity() + ",  " + bfc.limit() + ",  "
				+ bfc.position());
		bfc.put((byte) 100);
		System.out.println(bfb.get(0));
		System.out.println(bfc.get(0));
		// System.out.println(bfb.capacity()+",  "+bfb.limit()+",  "+bfb.position());
		// System.out.println(bfc.capacity()+",  "+bfc.limit()+",  "+bfc.position());
	}

	/**
	 * nio capacity limit position 三个参数
	 */
	@Test
	public void testNioPar() {
		ByteBuffer bf = ByteBuffer.allocate(15);
		System.out.println("1---"+bf.capacity() + ",  " + bf.limit() + ",  "
				+ bf.position());
		for (int i = 0; i < 10; i++) {
			bf.put((byte) i);
		}
		System.out.println("2----"+bf.capacity() + ",  " + bf.limit() + ",  "
				+ bf.position());
		bf.flip();
		System.out.println("3---"+bf.capacity() + ",  " + bf.limit() + ",  "
				+ bf.position());
		for (int i = 0; i < 5; i++) {
			bf.get();
		}
		System.out.println("4----"+bf.capacity() + ",  " + bf.limit() + ",  "
				+ bf.position());
		bf.flip();
		System.out.println(bf.capacity() + ",  " + bf.limit() + ",  "
				+ bf.position());
		for (int i = 0; i < 2; i++) {
			bf.get();
		}
		System.out.println(bf.capacity() + ",  " + bf.limit() + ",  "
				+ bf.position());
		bf.compact();
		System.out.println(bf.capacity() + ",  " + bf.limit() + ",  "
				+ bf.position());
	}

	/**
	 * 使用nio进行文件复制
	 * 
	 * @throws Exception
	 */
	@Test
	public void nioCopyFile() throws Exception {
		long startTime = System.currentTimeMillis();
		FileInputStream in = new FileInputStream(
				"d://exp_pef_pnr_pax_info_d_20151217.txt");
		FileOutputStream out = new FileOutputStream("d://tttt22.txt");

		FileChannel read = in.getChannel();
		FileChannel write = out.getChannel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		while (true) {
			byteBuffer.clear();
			int flag = read.read(byteBuffer);
			if (flag == -1) {
				break;
			}

			byteBuffer.flip();
			write.write(byteBuffer);
		}
		read.close();
		write.close();
		long endTime = System.currentTimeMillis();
		System.out.println("nio cost time ; " + (endTime - startTime));
	}
}
