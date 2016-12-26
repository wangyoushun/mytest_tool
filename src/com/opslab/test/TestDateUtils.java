package com.opslab.test;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import com.opslab.DateUtil;

public class TestDateUtils {

	@Test
	public void testDateTime() throws Exception {
		String dateTime = DateUtil.DateTime();
		System.out.println(dateTime);

		Date date = DateUtil.Date("2016-06-12");
		Date day = DateUtil.day(date, -1);
		String date2 = DateUtil.Date(day);
		System.out.println(date2);

	}
}
