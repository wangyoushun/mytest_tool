package com.opslab.test;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.opslab.FilePathUtil;
import com.opslab.FileUtil;

public class TestFileUtils {

	@Test
	public void testListFile(){
		String commandPath = FilePathUtil.commandPath("C:/Windows/addins/FXSEXT.ecf");
		System.out.println(commandPath);
	}
	
	/**
	 * 创建多级目录
	 */
	@Test
	public void testCreateFiles(){
		boolean createFiles = FileUtil.createFiles("c://111//222//333//4.txt");
		System.out.println(createFiles);
	}
	
	/**
	 * 基于 nio 的大文件拷贝
	 */
	@Test
	public void testCopy(){
		String resourcePath="e://table20160511.dmp";
		String targetPath="c://table20160511.dmp";
		boolean copy = FileUtil.copy(resourcePath, targetPath);
		System.out.println(copy);
	}
}
