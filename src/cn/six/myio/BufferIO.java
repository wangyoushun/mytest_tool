package cn.six.myio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class BufferIO {

	@Test
	//缓冲流拷贝文件
	public void testIO() throws Exception{
		long startTiime = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader("d://exp_pef_pnr_pax_info_d_20151217.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("d://tttt1.txt"));
		String str=null;
		while((str=reader.readLine())!=null){
//			System.out.println(str);
			writer.write(str);
			writer.newLine();
			writer.flush();
		}
		reader.close();
		writer.close();
		long endTime = System.currentTimeMillis();
		System.out.println("cost time : "+(endTime-startTiime));
	}
	
	@Test
	public void testBufferReader() throws Exception{
		
		BufferedReader reader = new BufferedReader(new FileReader("d://tttt.txt"));
		String str=null;
		while((str=reader.readLine())!=null){
			System.out.println(str);
		}
		reader.close();
	}
	
	@Test
	public void testBufferWriter() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("d://tttt1.txt"));
		writer.write("12dfsdf");
		writer.close();
	}
}
