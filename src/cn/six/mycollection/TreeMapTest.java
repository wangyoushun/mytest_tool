package cn.six.mycollection;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTest {

	@Test
	public void test1(){
		Map<Integer, String> treeMap = new TreeMap<Integer, String>();
		treeMap.put(1,"aa");
		treeMap.put(2,"b");
		treeMap.put(4, "d");
		treeMap.put(3, "c");
		treeMap.put(5, "e");
		
		
		System.out.println(treeMap);
	}
}
