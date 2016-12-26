package cn.six.mydate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class DateTest {

	@Test
	//获得当月的最大天数
	public void test1() throws Exception{
		String date = "201502";
		  SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMM");
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(sFormat.parse(date));
			  int num2 = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			  System.out.println(num2);
	}
}
