package cn.six.utils;

import java.util.Arrays;

/**
 * 
* @ClassName: ArrayUtil 
* @Description: 数组工具类 
* @author iwantfly
* @date 2017年6月22日 下午11:19:21 
*
 */
public class ArrayUtil {

	/**
	* @Title: reverseArray 
	* @Description: 数组反转 
	* @param int[]
	* @return void   
	 */
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
}
