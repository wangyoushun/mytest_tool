package cn.six.test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class TimeTaskDemo {
 
	public static void main(String[] args) {
		TimeTaskDemo timeTaskDemo = new TimeTaskDemo();
		//timeTaskDemo.test();
		timeTaskDemo.test1();
	}
	
	@Test
	public void test1(){
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("dddddddddd");
				
			}
		}, 1000);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public  void test(){
		String str="2015/8/1 18:46:00";
		Date date = new Date(str);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("-----");
			}
		}, date);
	}
}
