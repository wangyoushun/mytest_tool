package cn.six.algorithm.sort;

import java.util.Arrays;

/**
 * 优化后的冒泡排序
 * 
 * @ClassName: BubbleSortNew
 * @Description: TODO
 * @author iwantfly
 * @date 2017年3月9日 下午3:08:49
 * 
 */
public class BubbleSortNew {
	public static void sort(int[] arr) {
		int temp;// 定义一个临时变量
		for (int i = 0; i < arr.length - 1; i++) {// 冒泡趟数，n-1趟
			temp = 0;
			System.out.println("i=" + i);
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j + 1] < arr[j]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			if (temp == 0) {
				break;// 若果没有发生交换，则退出循环
			}
		}
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 1, 6, 2, 2, 5, 8, 34, 56, 12, 24 };
		BubbleSortNew.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
