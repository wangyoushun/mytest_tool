package cn.six.mycollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ListTest {

	@Test
	public void  test01(){
		List<String> aList = new ArrayList<String>(15);
		aList=null;
		for (String string : aList) {
			
		}
	}
	
	@Test
	public <T> void  testListType(){
		List<?> aList = new ArrayList<String>(15);
		Object object = aList.get(0);
	}
	
	@Test
	public void  testListExC(){
		List<String> aList = new ArrayList<String>(15);
		for(int i=0; i<10; i++){
			aList.add(i+"");
		}
		System.out.println(aList.size());
		
		aList.add("111");
		System.out.println(aList.size());
	}
	
	/**
	 * list 转 数组
	* @Title: listToArray 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@Test
	public void listToArray(){
		List<String> list1 = new ArrayList<String>();
		list1.add("111");
		list1.add("222");
		list1.add("333");
		
		Object[] array = list1.toArray(); //转为对象数组
		PArray(array);
		
		String[] array2 = list1.toArray(new String[0]);  //转为指定类型数组
		PArray(array2);
		
		String [] ars = new String[list1.size()];
		String[] array3 = list1.toArray(ars);  //转为指定类数组
		PArray(array3);
		PArray(ars);
		
		Collections.shuffle(list1); //随机打乱数组顺序
		System.out.println(list1);
		Collections.sort(list1);
		
		
	}
	
	public void PArray(Object[] array){
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * 检索集合
	 */
	@Test
	public void indexList(){
		List<String> list1 = new ArrayList<String>();
		list1.add("111");
		list1.add("222");
		list1.add("333");
		
		System.out.println("1--"+list1);
		for(int i=0; i<list1.size(); i++){
			System.out.println(list1.get(i));
		}
		System.out.println("2--"+list1);
		
		boolean contains = list1.contains("111");
		System.out.println("是否包含："+contains);
		
		int indexOf = list1.indexOf("111");
		System.out.println("元素位置:"+indexOf);
		
		Collections.copy(list1, list1);
	}
	
	//集合之间相互转换
	@Test
	public void test3() {
		//集合之间相互转换
		List<String> list1 = new ArrayList<String>();
		list1.add("111");
		list1.add("222");
		list1.add("333");
		list1.add("444");
		list1.add("111");
		System.out.println("list1:"+list1);
		
		Set<String> hashSet = new HashSet<>(list1);
		System.out.println("set:"+hashSet);
	}
	
	@Test
	public void test2(){
		//list 集合拷贝
		List<String> aList = new ArrayList<String>(10);
		for(int i=0; i<10; i++){
			aList.add(i+"");
		}
		System.out.println("aList:"+aList);
		List<String> bList = new ArrayList<String>(10);
		bList.addAll(aList);
		aList.remove(0);
		System.out.println("bList:"+bList);
		System.out.println("aList:"+aList);
		
	}


	
	@Test
	public void testList(){
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("11");
		arrayList.add("22");
		arrayList.add("131");
		
		Object[] array = arrayList.toArray();
	}
}
