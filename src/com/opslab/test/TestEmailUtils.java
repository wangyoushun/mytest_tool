package com.opslab.test;


import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.opslab.EmailUtil;
import com.sun.mail.util.PropUtil;

public class TestEmailUtils {

	/**
	 * 测试发送邮件
	 */
	@Test
	public void TestDoSendHtmlEmail(){
		EmailUtil emailUtil = new EmailUtil(true);
//		emailUtil.doSendHtmlEmail("1111", "test email utils", "2436093367@qq.com");
		emailUtil.doSendHtmlEmail("测试发送文件", "56 ", "2436093367@qq.com");
	}
	
	@Test
	public void testName() throws Exception {
		
		
	}
}
