package cn.six.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        int temp;//定义一个临时变量
        for(int i=0;i<arr.length-1;i++){//冒泡趟数
        	System.out.println("i="+i);
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j+1]<arr[j]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int arr[] = new int[]{1,6,2,2,5,8,34,56,12,24};
        BubbleSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}