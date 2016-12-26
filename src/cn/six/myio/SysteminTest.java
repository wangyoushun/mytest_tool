package cn.six.myio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class SysteminTest {

	@Test
	public void test1() throws IOException{
		InputStream in = System.in;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		String readLine =null;
		while((readLine = bufferedReader.readLine())!=null){
			System.out.println(readLine);
		}
		
		System.out.println(readLine);
	}
	
	@Test
	public void test() throws IOException{
		InputStream in = System.in;
		int read = in.read();
		System.out.println(read);
	}
}
