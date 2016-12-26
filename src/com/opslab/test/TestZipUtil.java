package com.opslab.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.opslab.ZIPUtil;

/**
 * 
 */
public class TestZipUtil {

	//
	@Test
	public void testName() throws Exception {
		String url = "D://疯狂Java_突破程序员基本功的16课.pdf";
		String dest = "D://疯狂Java_突破程序员基本功的16课.zip";
		
		ZIPUtil.deCompress(new File(url), dest);
	}
	
	//
	@Test
	public void testName2() throws Exception {
		String url = "D://疯狂Java_突破程序员基本功的16课.zip";
		String dest = "D://ffff";
		
		ZIPUtil.unCompress(new File(url), dest);
	}
}
