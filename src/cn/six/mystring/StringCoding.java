package cn.six.mystring;

import java.util.Arrays;

public class StringCoding {

	public static void main(String[] args) {

		/*
		 * String str = "BarackObama"; boolean checkDifferent =
		 * checkDifferent(str); System.out.println(checkDifferent);
		 */

	/*	String str = "This is nowcoder";
		System.out.println(str);
		String reverseString = reverseString(str);
		System.out.println(reverseString);
*/
		String stringA = "This is nowcoder , i";
		String stringB = "isi  This , nowcoder";
		boolean checkSam = checkSam(stringA, stringB);
		System.out.println(checkSam);
	}

	/**
	 * 题目描述
	 * 
	 * 给定两个字符串，请编写程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。这里规定大小写为不同字符，且考虑字符串重点空格。
	 * 给定一个string stringA和一个string
	 * stringB，请返回一个bool，代表两串是否重新排列后可相同。保证两串的长度都小于等于5000。 测试样例：
	 * "This is nowcoder","is This nowcoder" 返回：true
	 * "Here you are","Are you here" 返回：false
	 */
	 public static boolean checkSam(String stringA, String stringB) {
	        // write code here
		 char[] charArrayA = stringA.toCharArray();
		 char[] charArrayB = stringB.toCharArray();
		 Arrays.sort(charArrayA);
		 Arrays.sort(charArrayB);
		 
		 return new String(charArrayA).equals(new String(charArrayB));
	    }

	/**
	 * 题目描述
	 * 
	 * 请实现一个算法，在不使用额外数据结构和储存空间的情况下，翻转一个给定的字符串(可以使用单个过程变量)。 给定一个string
	 * iniString，请返回一个string，为翻转后的字符串。保证字符串的长度小于等于5000。 测试样例： "This is nowcoder"
	 * 返回："redocwon si sihT"
	 */
	public static String reverseString(String iniString) {
		// write code here
		int length = iniString.length();
		String str = "";
		char lastIndexOf;

		for (int i = 0; i < length; i++) {
			lastIndexOf = iniString.charAt(length - i - 1);
			str += lastIndexOf;
		}
		return str;
	}

	/**
	 * 检查是否有重复字符
	 * 
	 * @param iniString
	 * @return
	 */
	public static boolean checkDifferent(String iniString) {
		// write code here
		char[] charArray = iniString.toCharArray();
		for (int i = 0, length = charArray.length; i < length; i++) {
			for (int j = i + 1; j < length - 1; j++) {
				if (charArray[i] == charArray[j]) {
					return false;
				}
			}
		}
		return true;
	}

}
