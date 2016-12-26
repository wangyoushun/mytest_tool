package cn.six.mycollection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import cn.six.utils.ArrayUtil;

public class ArrayTest {

	@Test
	public void testAdd(){
		
	}
	
	
	/**
	 * 测试数组扩容
	* @Title: arrayExPandC 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@Test
	public void arrayExPandC() {
		String[] sr = {"1","2","3"};
		
		System.out.println(sr.length);
		String[] expandCapacity = ArrayUtil.expandCapacity(sr, 4);
		System.out.println(expandCapacity.length);
	}


	/**
	 * 数组集合转化， 数组去重
	 */
	@Test
	public void arrayToCollection() {
		String str = "12345/1 12345/1";
		String[] split = str.split(" ");
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}

		System.out.println("----------");
		List<String> asList = Arrays.asList(split);
		HashSet<String> hashSet = new HashSet<String>(asList);
		String[] array = hashSet.toArray(new String[0]);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * 数组排序 和 字符串输出
	 */
	@Test
	public void test1() {
		int[] a = new int[10];
		for (int i = 0; i < 10; i++) {
			a[i] = 10 - i;
		}
		System.out.println("排序前:" + Arrays.toString(a));
		int[] b = a.clone();
		Arrays.sort(b);
		System.out.println("排序后:clone" + Arrays.toString(b));
		System.out.println("排序后:" + Arrays.toString(a));
	}

}
