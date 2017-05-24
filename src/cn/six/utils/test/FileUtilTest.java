package cn.six.utils.test;


import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cn.six.utils.FileUtil;

public class FileUtilTest {
	
	@Test
	public void fileCopyTest() throws Exception {
		String path="D:\\tmp";
		FileUtil.copyFile(new File(path),"D:/tmp02");
	}
	
	//将list 写入文件中
	@Test
	public void fileUtilsTest() throws Exception {
		List<String> asList = Arrays.asList(new String[]{"1sf","2a","3师傅"});
		boolean writeAppend = FileUtil.writeAppend(new File("d://1.txt"), asList);
		System.out.println(writeAppend);
	}
}
