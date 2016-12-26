package cn.six.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestForeach {

	@Test
	public void test(){
		List<String> list = new ArrayList<String>();
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 5000000; i++) {
			list.add(i+"");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("插入数据耗时："+(endTime-startTime));
		
	//	forIndex(list);
		forIndex2(list);
		
	//	forEach(list);
		
	/*	
		for (String string : list) {
			System.out.println("---");
		}*/
	}

	public void forIndex(List<String> list) {
		long startTime;
		long endTime;
		int j=0;
		List<Integer> list2 = new ArrayList<Integer>();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			list2.add(i);
			if(i%10==0)
				list2.add(1111);
			j++;
		}
		 endTime = System.currentTimeMillis();
		 System.out.println(j+"for1索引遍历："+(endTime-startTime));
	}
	
	public void forIndex2(List<String> list) {
		long startTime;
		long endTime;
		int j=0;
		List<Integer> list2 = new ArrayList<Integer>();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			list2.add(i);
			if(j%10==0)
				list2.add(1111);
			j++;
		}
		 endTime = System.currentTimeMillis();
		 System.out.println(j+"for2索引遍历："+(endTime-startTime));
	}
	
	public void forEach(List<String> list) {
		long startTime;
		long endTime;
		int j=0;
		startTime = System.currentTimeMillis();
		List<Integer> list2 = new ArrayList<Integer>();
		for (String string : list) {
			j++;
			list2.add(j);
		}
		 endTime = System.currentTimeMillis();
		 System.out.println(j+"foreach："+(endTime-startTime));
	}
}
