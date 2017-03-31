package cn.six.test;


import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.junit.Test;

//测试 解码编码
public class EncodeTest {
	String str = "i am 君山";

	@Test
	public void testName() throws Exception {
		byte[] bytes = str.getBytes();
		toHex(bytes);
		String string = new String(bytes, "UTF-8");
		System.out.println(string);
		byte[] bytes2 = str.getBytes("GB2312");
		toHex(bytes2);
		byte[] bytes3 = str.getBytes("GBK");
		toHex(bytes3);
		byte[] bytes4 = str.getBytes("UTF-8");
		toHex(bytes4);
	}

	@Test
	public void test02() throws Exception {
		Charset charset = Charset.forName("UTF-8");
		ByteBuffer encode = charset.encode(str);
		byte[] array = encode.array();
		toHex(array);
		CharBuffer decode = charset.decode(encode);
		String string = decode.toString();
		System.out.println(string);
	}

	// 转16进制
	public void toHex(byte[] bytes) {
		String s = "";
		for (byte b : bytes) {
			s += Integer.toHexString(b) + ", ";
		}
		System.out.println(s);
	}

	// jvm编码类型
	@Test
	public void testJvmCode() throws Exception {
		System.out.println(System.getProperty("file.encoding"));
		System.out.println(Charset.defaultCharset());
	}
}
