package cn.six.utils;

import java.util.Arrays;

public class ArrayUtil {

	public static void reverseArray(int[] validData){
		for(int i = 0; i < validData.length / 2; i++)
		{
		    int temp = validData[i];
		    validData[i] = validData[validData.length - i - 1];
		    validData[validData.length - i - 1] = temp;
		}
	}
	
	/**
	 * 数组扩容
	 * @param datas
	 * @param newLen
	 * @return
	 */
	public static <T> T[] expandCapacity(T[] datas, int newLen){
		//长度不为负值
		newLen = newLen<0 ? 0:newLen;
		return Arrays.copyOf(datas, newLen); //生成一个新的数组并拷贝原值
	}
	
	
	public static void main(String[] args) {
		int [] a={1,2,3,4,5,6};
		reverseArray(a);
	/*	for (int i : a) {
			System.out.println(i);
		}*/
		String string = Arrays.toString(a);
		System.out.println(string);
	
	}
	
	
	
}
