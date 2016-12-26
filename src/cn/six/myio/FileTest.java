package cn.six.myio;

import java.io.File;

import org.junit.Test;

public class FileTest {
	//创建文件夹
	@Test
	public void testCreateFiles(){
		File file = new File("d:/testfile");
		if(!file.exists()){
			file.mkdirs(); //创建多级目录
		}
		
		file.delete();
	}
	
	//创建文件
	@Test
	public void testCreateFile() throws Exception{
		File file = new File("d:/ttttt.txt");
		System.out.println(file);
		file.createNewFile();
	}

}
