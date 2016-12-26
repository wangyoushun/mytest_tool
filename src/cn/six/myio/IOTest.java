package cn.six.myio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class IOTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		String filestr = "d://1.txt";
		File file = new File(filestr);

		InputStream in = new FileInputStream(file);
		int b1;
		byte[] bys = new byte[2048];
		ByteArrayOutputStream bo = new ByteArrayOutputStream(20480);
		while ((b1 = in.read(bys)) != -1) {
			//System.out.println(b1);
			bo.write(bys,0,b1);
		}
		byte[] byteArray = bo.toByteArray();
		System.out.println(byteArray);
		
		ByteArrayInputStream bin = new ByteArrayInputStream(byteArray);
		FileOutputStream fileOutputStream = new FileOutputStream(new File("d://2.txt"));
		
		while ((b1 = bin.read(bys)) != -1) {
			//System.out.println(b1);
			fileOutputStream.write(bys,0,b1);
		}
		
		
		fileOutputStream.close();
		in.close();
		bo.close();

	}

	@Test
	public void test1() throws Exception {
		String filestr = "d://1.txt";
		File file = new File(filestr);
		if (!file.exists()) {
			System.out.println("no file, create");
			file.createNewFile();
		}
		String content = "1,2,3,4\n, 5";
		OutputStream out = new FileOutputStream(file);
		out.write(content.getBytes());
		out.close();
	}
}
