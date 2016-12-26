package cn.six.myio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

public class InputStreamTest {

	@SuppressWarnings("deprecation")
	@Test
	//输入流读文件
	public void testIos() throws Exception{
		String url = "223 - 副本.xlsx";
		String url2 = "223 - 鍓湰.xlsx";
		
		String encode = URLEncoder.encode(url2);
		System.out.println(encode);
		String string = new String(url2.getBytes("iso8859-1"),"utf-8");
		System.out.println(string);
		String decode = URLDecoder.decode(encode);
		System.out.println(decode);
		
	/*	InputStream fis = new FileInputStream(new File("D:/myftp/chartrevenueFile/223 - 副本.xlsx"));
		byte [] b = new byte[1024];
		
		int len = 0;
		while((len=fis.read(b))!=-1){
			System.out.println(new String(b, 0, len));
		}
		fis.close();*/
	}
}
