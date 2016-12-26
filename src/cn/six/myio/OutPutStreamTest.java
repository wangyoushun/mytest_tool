package cn.six.myio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

public class OutPutStreamTest {

	//输出到文件
	@Test
	public void testOut() throws IOException{
		OutputStream fos = new FileOutputStream(new File("d:/tttt.txt"),true); //在文件末尾追加
		fos.write("中国\n".getBytes());
		fos.close();
	}
	
	@Test
	public void test(){
		char s='总';
		System.out.println(s);
	}
}
