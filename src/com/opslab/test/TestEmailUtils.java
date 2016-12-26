package com.opslab.test;

import org.junit.Test;

import com.opslab.EmailUtil;

public class TestEmailUtils {

	/**
	 * 测试发送邮件
	 */
	@Test
	public void TestDoSendHtmlEmail(){
		EmailUtil emailUtil = new EmailUtil(true);
		emailUtil.doSendHtmlEmail("1111", "test email utils", "2436093367@qq.com");
	}
}
