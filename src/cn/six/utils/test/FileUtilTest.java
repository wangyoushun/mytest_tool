package cn.six.utils.test;


import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cn.six.utils.FileTool;

public class FileUtilTest {
	String path = "D:\\";


	@Test
	public void testSqlToEntity() throws Exception {
		path += "order_detail.txt";
		String sqlStrToEntity = FileTool.sqlStrToEntity(path, true);
		System.out.println(sqlStrToEntity);
		
	}
	
	
	
	@Test
	public void fileCopyTest() throws Exception {
		String path="D:\\tmp";
		FileTool.copyFile(new File(path),"D:/tmp02");
	}
	
	//将list 写入文件中
	@Test
	public void fileUtilsTest() throws Exception {
		List<String> asList = Arrays.asList(new String[]{"1sf","2a","3师傅"});
		boolean writeAppend = FileTool.writeAppend(new File("d://1.txt"), asList);
		System.out.println(writeAppend);
	}
}
